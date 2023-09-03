import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 주어진 2차원 배열을 도는 것이 필수
 * 그리고 1(배추)를 만나면 bfs든 dfs든 돌아준다. 돌기 시작할 때 카운트++, 그리고 방문한 지점은 visited로 관리
 */
public class Main_BJ_1012_유기농배추 {
	static class temp {
		int x;
		int y;
		public temp(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] dx = new int[] { -1, 0, 1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] arr = new int[M][N];
			boolean[][] visited = new boolean[M][N];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			int cnt = 0;

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && arr[i][j] == 1) {
						Queue<temp> queue = new ArrayDeque<>();
						queue.offer(new temp(i, j));
						visited[i][j] = true;
						cnt++;
						while (!queue.isEmpty()) {
							temp cur = queue.poll();
							for (int l = 0; l < 4; l++) {
								int tempX = cur.x + dx[l];
								int tempY = cur.y + dy[l];
								if (tempX > -1 && tempX < M && tempY > -1 && tempY < N && arr[tempX][tempY] == 1
										&& !visited[tempX][tempY]) {
									visited[tempX][tempY] = true;
									queue.offer(new temp(tempX, tempY));
								}
							}
						}
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
