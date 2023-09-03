import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1003_피보나치함수 {

	/*
	 * 1. 일단 피보나치 함수를 구하는 재귀함수를 만든다(0과 1시 카운트 올리도록)
	 * 2. 해당 결과를 보고 점화식을 찾아보자
	 */

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] dp = new int[41];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i < 41; i++) {
			dp[i] = dp[i-1]+dp[i-2];
		}
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) sb.append(1).append(" ").append(0).append("\n");
			else sb.append(dp[N-1]).append(" ").append(dp[N]).append("\n");
		}
		System.out.println(sb);
	}

}


//11
//0
//1
//2
//3
//4
//5
//6
//7
//8
//9
//10
