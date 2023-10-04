import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 참고 : 플로이드워셜로도 가능함, 그게 정석인듯 
 * 키 순서를 정확하게 알 수 있는 사람은 키 비교를 N-1회 당한 사람이다
 * 그리고 키를 비교당한다는 것은 한 정점에서 BFS순회를 했을 때 도달하는 정점들이 있는 경우이다
 * 
 * 결과적으로 모든 정점에 대해 BFS를 수행하고, 정점을 방문할때마다 그 정점 + 시작점에 대해 카운트를 기록하고, 모든 BFS를 수행 한 후 카운트가 N-1인 경우 -> 키순서를 정확하게 알 수 있는 사람이라고 할 수 있다
 */
public class Solution_SWEA_1004_키순서 {
	
	static void bfs(int start, int[][] adjMatrix, int[] vertexCompareCnt, int N) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 1; i < N+1; i++) {
				if (!visited[i] && adjMatrix[cur][i] != 0) {
					visited[i] = true;
					q.offer(i);
					vertexCompareCnt[start]++;
					vertexCompareCnt[i]++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			int[][] adjMatrix = new int[N+1][N+1];
			int[] vertexCompareCnt = new int [N+1];
			int sum = 0;
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				adjMatrix[start][end] = 1;
			}
			
			for (int i = 1; i < N+1; i++) {
				bfs(i, adjMatrix, vertexCompareCnt, N);
			}
			
			for (int i : vertexCompareCnt) {
				if (i == N-1) {
					sum++;
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}


//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
///*
// * 위상정렬을 하는데, 모든 경우의 수를 출력하도록 한다(진입차수가 0인 것들, 분기점이 여러개인 경우 그 모든 분기점에 대한 결과)
// * 그리고 모든 결과들을 비교했을때 순서에 변화가 없는것 <- 답
// * 
// */
//public class Solution_SWEA_1004_키순서 {
//	
//	static List<int[]> list = new ArrayList<>();
//	
//	static void topologicalOrder(int N, int[][] adjMatrix) {
//		//각 노드에 대한 진입차수 만들기
//		//진입차수가 0인 것을 스타트로 시작, 우선순위가 동등한 경우는 각 경우의 수에 대해 다 출력해야함
//		int[] inDegree = new int[N+1];
//		
//		for (int j = 1; j <= N; j++) {
//			for (int i = 1; i <= N; i++) {
//				if (adjMatrix[i][j] != 0) {
//					inDegree[j]++;
//				}
//			}
//		}
//		
//		boolean[] visited = new boolean[N+1];
//		Queue<Integer> q = new ArrayDeque<>();
//		for (int i = 1; i < inDegree.length; i++) {
//			if (inDegree[i] == 0) {
//				q.offer(i);
//				visited[i] = true;
//			}
//			
//		}
//		
//		List<Integer> result = new ArrayList<>();
//		
//		while (!q.isEmpty()) {
//			
//			int cur = q.poll();
//			result.add(cur);
//			for (int i = 1; i < adjMatrix.length; i++) {
//				//값이 1이고, 방문치 않았으면 inDegree를 감소시킨다, indegree가 0이면 큐에 넣고 visted를 트루
//				if (!visited[i] && inDegree[i] != 0 && adjMatrix[cur][i] != 0) {
//					inDegree[i]--;
//					if (inDegree[i] == 0) {
//						q.offer(i);
//						visited[i] = true;
//					}
//				}
//			}
//		}
//		
//		System.out.println(result);
//		System.out.println(Arrays.toString(inDegree));
//	}
//	
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		int T = Integer.parseInt(br.readLine());
//		int N = Integer.parseInt(br.readLine());
//		int M = Integer.parseInt(br.readLine());
//		
//		int[][] adjMatrix = new int[N+1][N+1];
//		
//		for (int i = 0; i < M; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//			int start = Integer.parseInt(st.nextToken());
//			int end = Integer.parseInt(st.nextToken());
//			adjMatrix[start][end] = 1;
//		}
//		
//		for (int i = 1; i < adjMatrix.length; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}
//		System.out.println();
//		topologicalOrder(N, adjMatrix);
//		
//	}
//}
