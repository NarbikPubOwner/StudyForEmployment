import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16926_배열돌리기1 {

	public static void main(String[] args) throws IOException {
		//횟수만큼 돌리지 말고
		//배열에 횟수만큼 인덱스 증감 시키면 될듯
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int RotateCount = Integer.parseInt(st.nextToken());
		
		int[][] orgArr = new int[N][M];
		
		for (int i = 0; i < orgArr.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < orgArr[i].length; j++) {
				orgArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int rc = 0; rc < RotateCount; rc++) {
			int i = 0;
			int j = 0;
			int skinCount = 0;
			int[][] tempArr = new int[N][M];
			while (N - skinCount*2 >=1 && M - skinCount*2 >= 1) {
				i = skinCount;
				j = skinCount;
				for (i = skinCount; i < N-skinCount; i++) {
					if (i + 1 >= N - skinCount) {
						tempArr[i][j + 1] = orgArr[i][j];
					} else {
						tempArr[i+1][j] = orgArr[i][j];
					}
				}
				i--;
				for (j = 1 + skinCount; j < M-skinCount; j++) {
					if (j + 1 >= M - skinCount) {
						tempArr[i-1][j] = orgArr[i][j];
					} else
						tempArr[i][j+1] = orgArr[i][j];
				}
				j--;
				for (i = N-2 - skinCount; i > -1 + skinCount; i--) {
					if (i - 1 < 0 + skinCount) {
						tempArr[i][j - 1] = orgArr[i][j];
					} else
						tempArr[i-1][j] = orgArr[i][j];
				}
				i++;
				for (j = M-2 - skinCount; j > -1 + skinCount; j--) {
					if (j - 1 < 0 + skinCount) {
						
					} else
						tempArr[i][j-1] = orgArr[i][j];
				}
				
				skinCount++;
			}
			for (i = 0; i < N; i++) {
				orgArr[i] = tempArr[i].clone();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(orgArr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
