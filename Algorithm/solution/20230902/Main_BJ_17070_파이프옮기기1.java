import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = new int[] {0,-1,-1};
		int[] dy = new int[] {-1,0,-1};
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][][] dp = new int[N][N][3];//0 : 가로방향 완성, 1 : 새로방향 완성, 2 : 대각선 방향 완성
		dp[0][1][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if (arr[i][j] != 1) {
					int nx = i - 1;
					int ny = j - 2;
					if ((nx > -1 && ny > -1) || (ny > -1)) {
						dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
					}
					nx = i - 2;
					ny = j - 1;
					if ((nx > -1) || (nx > -1 && ny > -1)) {
						dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
					}
					nx = i - 1;
					ny = j - 1;
					if ((nx > -1 && arr[nx][j] != 1) && (ny > -1 && arr[i][ny] != 1)) {
						if (i - 2 > -1 || j - 2 > -1) {
							dp[i][j][2] += dp[nx][ny][0] + dp[nx][ny][1] + dp[nx][ny][2];
						}
					}
				}
			}
		}
//		for (int k = 0; k < 3; k++) {
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(dp[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
		
	}

}
