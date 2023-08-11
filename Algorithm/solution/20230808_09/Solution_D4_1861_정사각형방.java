import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1861_정사각형방 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = new int[] { -1, 0, 1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 4방탐색 후 자기보다1 크면 이동 cnt++, 그렇지 않으면 다음으로
			int result = 0;
			int startValue = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int tempI = i;
					int tempJ = j;
					int cnt = 1;

					while (true) {
						int idx = 0;
						while (idx < 4) {

							int nx = tempI + dx[idx];
							int ny = tempJ + dy[idx];
							if (nx > -1 && nx < N && ny > -1 && ny < N && ((arr[nx][ny] - arr[tempI][tempJ]) == 1)) {
								tempI = nx;
								tempJ = ny;

								cnt++;
								break;
							}
							idx++;
						}
						if (idx >= 4) {
							break;
						}
					}
					
					if (result == cnt) {
						if (startValue > arr[i][j]) {
							startValue = arr[i][j];
						}
					}
					else if (result < cnt){
						result = cnt;
						startValue = arr[i][j];
					}
				}
			}
			sb.append("#").append(t).append(" ").append(startValue).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

}
