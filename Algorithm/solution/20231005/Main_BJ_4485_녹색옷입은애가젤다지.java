import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import org.omg.PortableInterceptor.DISCARDING;

/*
 * 주어진 배열은 인접행렬이라고 볼 수 있다
 * 그렇기 때문에 0,0 ~ N-1,N-1로 도착하는 최소 가중치 합 -> 다잌스트라 결과 라고 할 수 있다
 * 즉 해당 인접행렬의 다잌스트라 결과를 얻어내여 N-1,N-1의 결과를 도출하면 정답이 된다
 */
public class Main_BJ_4485_녹색옷입은애가젤다지 {
	static int result;
	static int N;
	static int[] dx = new int[] {-1,0,1,0};
	static int[] dy = new int[] {0,1,0,-1};
	
	//우선순위 큐를 이용한 다잌스트라 알고리즘
	/* 0. 시작점+시작점 가중치를 큐에 넣는다(일반적인 그래프에서 시작점은 가중치가 0이라고 하지만, 이 문제는 시작점만 거쳐도 가중치가 들어오기 때문에 가중치를 추가한다)
	 * 1. 만약 큐에서 뽑아낸 원소의 가중치가 현재 위치의 최소거리 배열의 값보다 크다면 넘어간다 
	 * -> 큐에서 뽑아낸 원소의 가중치는 해당 점으로 오면서 저장한 이전 가중치+현재위치 기본 가중치의 최소값인데 이미 그값이 더 크다면 그 이후로도 현재 저장된 값들보다 나을 수가 없기 때문
	 * 2. 한 점에서 뻗어나갈 수 있는 모든 정점들을 검색한다 -> 이 문제는 4방탐색해야하기 때문에 4방 탐색을 한다
	 * 3. 해당 정점에 저장된 최소거리 값 보다 새로운 가중치의 값이 더 적다면 해당 값으로 갱신하고 큐에 추가한다
	 * 4. 이를 계속 진행하면 최소거리 배열의 값은 전부 최소값을 될 것이고, 그렇다면 큐가 더 진행하지 않아 알아서 종료되게 된다
	 */
	static void dijkstra(int[][] distance,int[][] arr) {//시작점이 0,0으로 고정이라 스타트 지점 설정 안함, 아닐경우 스타트 지점을 파라미터로 받으면 됨
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(0,0,arr[0][0]));
		distance[0][0] = arr[0][0];
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int w = cur.weight;
			//이전 정점들을 거쳐서 온 가중치가 현재 저장된 가중치보다 크다면 의미가 없기 떄문에 넘긴다
			if (w > distance[x][y]) continue;
			
			//인접 노드들을 돈다 -> 이 경우엔 4방탐색
			for (int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				//배열 인덱스를 벗어나지 않는다면
				if (nx > -1 && nx < N && ny > -1 && ny < N) {
					int newCost = w+arr[nx][ny];//이전 가중치 + 현재 위치 기본 가중치
					if (newCost < distance[nx][ny] ) {//가 저장된 값보다 작으면
						distance[nx][ny] = newCost;//갱신
						q.offer(new Node(nx, ny, newCost));//다음 진행 정점으로 삼는다
					}
				}
			}
		}
	}
	
	//우선순위 큐를 이용할 경우 큐에 저장될 객체에 값을 비교할 compareTo 함수가 있어야 한다
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int weight;
		
		public Node(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = 0;
		while (true) {
			
			t++;
			result = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			int[][] distance = new int [N][N];
			if (N == 0) {
				break;
			}
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
			
			dijkstra(distance, arr);
			
			sb.append("Problem").append(" ").append(t).append(": ").append(distance[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}
}
