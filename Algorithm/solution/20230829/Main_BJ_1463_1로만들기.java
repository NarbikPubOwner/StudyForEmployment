import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 값 x의 최소연산 횟수(3,2나누기, 1빼기)를 도출해야하는 문제
 * 이는 x연산 이전의 연산횟수 + 1이라고 할 수 있다
 * 즉 1부터 연산횟수를 구하고(1은 시작부터 1이기 때문에 연산횟수 0), 그 다음 2부터는 x/3, x/2, x-1 중에 최소 연산횟수인 것 + 1한 값을 도출하면 된다
 */
public class Main_BJ_1463_1로만들기 {
	
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		dp = new int [x+1];
		dp[1] = 0;
		for (int i = 2; i <= x; i++) {
			int tmp = dp[i-1];
			if (i%3 == 0 && dp[i/3] < tmp) tmp = dp[i/3];
			if(i%2 == 0 && dp[i/2] < tmp) tmp = dp[i/2];
			dp[i] = tmp+1;
		}
		System.out.println(dp[x]);
		
	}
}
