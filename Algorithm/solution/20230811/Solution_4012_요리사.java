import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.print.attribute.standard.RequestingUserName;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane.WindowsPropertyChangeHandler;

public class Solution_4012_요리사 {
	static int N;
	static int[] output;
	static int[] otherOutput;
	static int[][] arrN;
	static int result;

	static void combSmall(int m, int start, int M) {
		if (m == M) {
			return;
		}

	}

	static void comb(int cnt, int start) {
		if (cnt >= N / 2) {
			int tempI = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = true;
				for (int j = 0; j < N / 2; j++) {
					if (output[j] == i) {
						flag = false;
						break;
					}
				}
				if (flag) {
					otherOutput[tempI] = i;
					tempI++;
				}
			}
			int sum = 0;
			for (int j = 0; j < output.length; j++) {
				for (int j2 = 0; j2 < output.length; j2++) {
					if (j == j2)
						continue;
					sum += arrN[output[j]][output[j2]];
				}
			}

			int sum2 = 0;
			for (int j = 0; j < otherOutput.length; j++) {
				for (int j2 = 0; j2 < otherOutput.length; j2++) {
					if (j == j2)
						continue;
					sum2 += arrN[otherOutput[j]][otherOutput[j2]];
				}
			}

//			System.out.println(Arrays.toString(output));
//			System.out.println(Arrays.toString(otherOutput));
			result = Math.min(result, Math.abs(sum - sum2));

//			System.out.println();
			return;
		}

		for (int i = start; i < N; i++) {
			output[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			output = new int[N / 2];
			otherOutput = new int[N / 2];
			arrN = new int[N][N];
			result = Integer.MAX_VALUE;
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arrN[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			comb(0, 0);
			System.out.println("#" + (t+1) + " " + result);

		}
	}

}
