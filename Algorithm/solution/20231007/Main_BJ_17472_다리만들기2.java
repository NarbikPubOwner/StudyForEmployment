import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 요약 : 섬들을 그래프화 한 후(가중치)해당 그래프를 프림 or 크루스칼 하여 mst를 얻어내면 된다
 * 1. 섬들에 번호를 붙여준다 -> arr배열에 bfs를 타서 순서에 따라 수정한다
 * 2. 섬들의 개수에 해당하는 크기의 그래프를 생성한다
 * 3. arr배열을 돌면서 해당 섬들의 다른 섬에 대한 거리를 가중치로 그래프에 삽입한다
 * 4. 그렇게 얻은 그래프를 프림한다
 */
public class Main_BJ_17472_다리만들기2 {
	
	static int[] dx = new int[] {-1,0,1,0};
	static int[] dy = new int[] {0,1,0,-1};
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	static void primMst(int[][] adjMatrix) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int size = adjMatrix.length;
		pq.add(new Node(1, 0));
		boolean[] visisted = new boolean[adjMatrix.length];
		visisted[1] = true;
		
		int[] mst = new int[adjMatrix.length];
		Arrays.fill(mst, Integer.MAX_VALUE);
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			visisted[cur.vertex] = true;
			if (mst[cur.vertex] > cur.weight) {
				mst[cur.vertex] = cur.weight;
			}
			for (int i = 1; i < adjMatrix.length; i++) {
				if (!visisted[i]) {
					pq.offer(new Node(i, adjMatrix[cur.vertex][i]));
				}
			}
		}
		int cnt = 0;
		int weightSum = 0;
		for (int i = 1; i < mst.length; i++) {
			if (mst[i] != Integer.MAX_VALUE) cnt++;
			weightSum += mst[i];
		}
		if (cnt != adjMatrix.length-1) {
			System.out.println(-1);
		}
		else System.out.println(weightSum);
	}
	
	static int bfs(int[][]arr) {
		int N = arr.length;
		int M = arr[0].length;
		int idx = 1;
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && arr[i][j] != 0) {
					Queue<int[]> q = new ArrayDeque<int[]>();
					q.offer(new int[] {i,j});
					visited[i][j] = true;
					arr[i][j] = idx;
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int x = cur[0];
						int y = cur[1];
						for (int k = 0; k < 4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							if (nx>-1 && nx<N && ny>-1 && ny<M && arr[nx][ny] != 0 && !visited[nx][ny]) {
								q.offer(new int[] {nx,ny});
								visited[nx][ny] = true;
								arr[nx][ny] = idx;
							}
						}
					}
					idx++;
				}
			}
		}
		return idx-1;
	}
	
	static void makeGraph(int[][] adjMatrix,int[][] arr) {
		
		int N = arr.length;
		int M = arr[0].length;
		int graphSize = adjMatrix.length;
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 1; i <= graphSize; i++) {
			hm.put(i, 0);
		}
		
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0) {
					int start = arr[i][j];
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {i,j});
					visited[i][j] = true;

					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int x = cur[0];
						int y = cur[1];
						for (int k = 1; k < 3; k++) {
							int distance = 0;
							int nx = x+dx[k];
							int ny = y+dy[k];
							while (nx > -1 && nx < N && ny > -1 && ny < M) {
								int end = arr[nx][ny];
								if (end == 0) distance++;
								else {
									if (distance>=2 && adjMatrix[start][end] > distance) {
										adjMatrix[start][end] =  distance;
										adjMatrix[end][start] =  distance;
									}
									break;
								}
								
								nx += dx[k];
								ny += dy[k];
								
								
							}
						}
						
						if (y+1 < M && !visited[x][y+1] && arr[x][y+1] == start) {
							q.offer(new int[] {x,y+1});
							visited[x][y+1] = true;
						}
						if (x+1 < N && !visited[x+1][y] && arr[x+1][y] == start) {
							q.offer(new int[] {x+1,y});
							visited[x+1][y] = true;
						}
					}
					
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int I = Integer.parseInt(st.nextToken());
		int J = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[I][J];
		
		for (int i = 0; i < I; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < J; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int graphSize = bfs(arr);
		int[][] adjMatrix = new int[graphSize+1][graphSize+1];
		for (int k = 1; k < adjMatrix.length; k++) {
			Arrays.fill(adjMatrix[k], Integer.MAX_VALUE);
		}
		makeGraph(adjMatrix, arr);
		primMst(adjMatrix);
	}
}

