import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1074_Z {
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int answer = 0;
		while (N > 0) {
			N -= 1;
			int powN = (int) Math.pow(2, N);
			// 1사분면
			if (C < powN && R < powN) {
				
			}
			else if (C < powN && R >= powN) {
				answer += powN*powN*1;
				R -= powN;
			}
			else if (C >= powN && R < powN) {
				answer += powN*powN*2;
				C -= powN;
			}
			else {
				answer += powN*powN*3;
				C -= powN;
				R -= powN;
			}
		}
		System.out.println(answer);

	}

}
