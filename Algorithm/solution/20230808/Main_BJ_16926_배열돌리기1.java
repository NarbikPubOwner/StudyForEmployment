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
			while (N - skinCount*2 >=1 && M - skinCount*2 >= 1) {//내부 회전으로 들어갈 경우, 그 값이 1보다 작아지면 멈춤
				//껍질 제외 부분부터 시작
				i = skinCount;
				j = skinCount;

				//아래로
				for (i = skinCount; i < N-skinCount; i++) {
					if (i + 1 >= N - skinCount) {//끝에 가게 된 경우
						tempArr[i][j + 1] = orgArr[i][j];//방향 전환
					} else {//일반적인 경우
						tempArr[i+1][j] = orgArr[i][j];
					}
				}
				i--;//for문으로 인해 벗어난 i카운트 조정
				//오른쪽으로
				for (j = 1 + skinCount; j < M-skinCount; j++) {
					if (j + 1 >= M - skinCount) {
						tempArr[i-1][j] = orgArr[i][j];
					} else
						tempArr[i][j+1] = orgArr[i][j];
				}
				j--;
				//위쪽으로
				for (i = N-2 - skinCount; i > -1 + skinCount; i--) {
					if (i - 1 < 0 + skinCount) {
						tempArr[i][j - 1] = orgArr[i][j];
					} else
						tempArr[i-1][j] = orgArr[i][j];
				}
				i++;
				//왼쪽으로
				for (j = M-2 - skinCount; j > -1 + skinCount; j--) {
					if (j - 1 < 0 + skinCount) {
						//마지막의 마지막 종료 시점
					} else
						tempArr[i][j-1] = orgArr[i][j];
				}
				skinCount++;
			}
			//회전된 배열을 원본 배열에 삽입
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
