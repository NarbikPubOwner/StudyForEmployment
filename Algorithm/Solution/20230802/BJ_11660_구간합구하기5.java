import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_11660_구간합구하기5 {
	public static void main(String[] args) throws IOException {
		//정방향 부분합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int T = Integer.parseInt(input[1]);
		
		int[][] NMArr = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				NMArr[i][j] = Integer.parseInt(input[j-1]) + NMArr[i][j-1];
			}
		}
		
		//정방향 부분합
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				NMArr[i][j] += NMArr[i][j-1];
//			}
//		}
//		for (int j = 1; j <= N; j++) {
//			for (int i = 1; i <= N; i++) {
//				NMArr[i][j] += NMArr[i-1][j];
//			}
//		}
		
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				if (j == 1) {
//					NMArr[i][j] += NMArr[i-1][N];
//				}
//				NMArr[i][j] += NMArr[i][j-1];
//				
//			}
//		}
//		for (int j = 1; j <= N; j++) {
//			for (int i = 1; i <= N; i++) {
//				NMArr[i][j] += NMArr[i-1][j];
//			}
//		}
		
//		for (int i = 0; i < NMArr.length; i++) {
//			System.out.println(Arrays.toString(NMArr[i]));
//		}
		
		StringBuilder printString = new StringBuilder();
		for (int t = 0; t < T; t++) {
			input = br.readLine().split(" ");
			int x1 = Integer.parseInt(input[0]);
			int y1 = Integer.parseInt(input[1]);
			int x2 = Integer.parseInt(input[2]);
			int y2 = Integer.parseInt(input[3]);
			
			int tmp = 0;
			for(int i=x1;i<=x2;i++) {
				tmp += NMArr[i][y2]-NMArr[i][y1-1];
			}
			printString.append(tmp).append("\n");
			
		}
		System.out.println(printString);
		
	}
}
