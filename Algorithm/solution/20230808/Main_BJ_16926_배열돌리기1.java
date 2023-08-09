import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16926_배열돌리기1 {

	public static void main(String[] args) throws IOException {

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
			while (N - skinCount * 2 >= 1 && M - skinCount * 2 >= 1) {// 내부 회전으로 들어갈 경우, 그 값이 1보다 작아지면 멈춤
				// 껍질 제외 부분부터 시작
				i = skinCount;
				j = skinCount;
				// 아래로
				for (i = skinCount; i < N - 1 - skinCount; i++) {
					tempArr[i + 1][j] = orgArr[i][j];
				}
				// 오른쪽으로
				for (j = skinCount; j < M - 1 - skinCount; j++) {
					tempArr[i][j + 1] = orgArr[i][j];
				}
				// 위쪽으로
				for (i = N - 1 - skinCount; i > skinCount; i--) {
					tempArr[i - 1][j] = orgArr[i][j];
				}
				// 왼쪽으로
				for (j = M - 1 - skinCount; j > skinCount; j--) {
					tempArr[i][j - 1] = orgArr[i][j];
				}

				skinCount++;
			}
			// 회전된 배열을 원본 배열에 삽입
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
