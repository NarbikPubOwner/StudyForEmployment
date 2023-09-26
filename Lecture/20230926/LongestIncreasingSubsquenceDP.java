import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 최장증가수열
 */

//입력예시

//6
//3 2 6 4 5 1
public class LongestIncreasingSubsquenceDP {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());//입력받을 수열의 길이
		
		/*
		 * 수열 입력받기
		 */
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		
		for (int i = 1; i < n; i++) {
			//자신의 앞의 값 들 중 자신보다 수가 작으면서 그 값 중 가장 큰 값을 가져온다
			int maxLength = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					maxLength = dp[j]+1;
				}
			}
			dp[i] = maxLength;
		}
		
		System.out.println(Arrays.toString(dp));
	}
}
