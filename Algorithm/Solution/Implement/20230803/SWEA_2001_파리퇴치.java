import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_2001_파리퇴치 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 얘도 누적합인가?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());

		String[] input = null;
		for (int t = 0; t < testCase; t++) {
			input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int K = Integer.parseInt(input[1]);

			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			int result = 0;
			for (int i = 0; i <= N-K; i++) {//5-2 -> 3
				for (int j = 0; j <= N-K; j++) {
					
					int tempSum = 0;
					for (int i2 = 0; i2 < K; i2++) {
						for (int j2 = 0; j2 < K; j2++) {
							tempSum += arr[i+i2][j+j2];
						}
					}
					if (result < tempSum) {
						result = tempSum;
					}
				}
			}
			System.out.println("#" + (t+1) + " " + result);
		}
	}
}
