import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://ikjo.tistory.com/357
//	https://jih3508.tistory.com/50
public class Main {
	static String[] board;
	static int N, M, K;

	static int MakeBoard(char color) {
		
		//돌면서 짝수 번째는 해당 컬러로
		//홀수번째는 다른 컬러로
			//그러면서 동시에 색깔이 바뀌는 경우 value를 증가
			//그리고 누적합 배열을 따로 만들어 value가 구해질때마다 누적합 계산 -> board[i+1][j+1] = board[i+1][j] + board[i][j+1] - board[i][j] + value
		int cnt = Integer.MAX_VALUE;
		int[][] dp = new int[N+1][M+1];
		int value = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				
				if ((n+m)%2 == 0) {
					if (board[n].charAt(m) == color) value = 1;
					else value = 0;
				}
				else {
					if (board[n].charAt(m) != color) value = 1;
					else value = 0;
				}
				
				dp[n+1][m+1] = value + dp[n+1][m] + dp[n][m+1] - dp[n][m];
			}
		}
		
		
		for (int n = 1; n <= N - K +1; n++) {
			for (int m = 1; m <= M - K +1; m++) {
				cnt = Math.min(cnt, dp[n+K-1][m+K-1] - dp[n+K-1][m-1] - dp[n-1][m+K-1] + dp[n-1][m-1]);
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new String[N];

		for (int i = 0; i < N; i++)
			board[i] = br.readLine();
		
		sb.append(Math.min(MakeBoard('B'), MakeBoard('W')));
		System.out.println(sb);
		//1번째부터 흑으로 칠하는 경우
		//2번째부터 백으로 칠하는 경우
		
		//로직이 같고, 대신 대상 컬러가 다르다
		//이 두가지 중 의 최소값을 낸다

	}
}
