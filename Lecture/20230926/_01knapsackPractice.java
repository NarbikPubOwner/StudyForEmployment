import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//입력예시
//4
//10
//5 4 3 6
//10 40 50 30
//정답 : 90
/*
 * 
 */
public class _01knapsackPractice {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] weight = new int[N+1];
		int[] value = new int[K+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 1; i <= N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 1; i <= N; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][K+1];
		for (int i = 0; i < weight.length; i++) {
			dp[i][0] = 0;
		}
		for (int i = 0; i < K; i++) {
			dp[0][i] = 0;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (weight[i] > j) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					dp[i][j] = Math.max(value[i]+dp[i-1][j-weight[i]], dp[i-1][j]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
