import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS를 돈다 - 한 정점에 대해 bfs를 돌면 cnt 증가
 * visited는 유지해서 가져간다
 * BFS를 정점 개수만큼 돌면 된다
 */
public class Main_BJ_11724_연결요소의개수 {
	static boolean[] visited;
	static int N;
	static int M;
	static int[][] adjMatrix;
	
	static int bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				cnt++;
				q.offer(i);
				visited[i] = true;
				
				while (!q.isEmpty()) {
					int cur = q.poll();
					for (int j = 0; j < N; j++) {
						if (!visited[j]&&adjMatrix[cur][j] == 1) {
							q.offer(j);
							visited[j] = true;
						}
					}
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			adjMatrix = new int[N][N];
			visited = new boolean[N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int start = Integer.parseInt(st.nextToken())-1;
				int end = Integer.parseInt(st.nextToken())-1;
				adjMatrix[start][end] = 1;
				adjMatrix[end][start] = 1;
			}
			
			System.out.println(bfs());
			
			
			
	}
}
