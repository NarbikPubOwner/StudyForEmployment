import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class RCS {
	int r;
	int c;
	int s;

	public RCS(int r, int c, int s) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
	}

	@Override
	public String toString() {
		return "R : " + r + " C : " + c + " S : " + s;
	}

}

public class Main_BJ_17406_배열돌리기4 {
	static int[][] arr;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		RCS[] input = new RCS[R];
		RCS[] output = new RCS[R];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			input[i] = new RCS(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		boolean[] isSelected = new boolean[R];
		perm(0, 0, input, output, isSelected, R);
		System.out.println(result);
		// 배열이 주어졌을때 행의 최소값 출력
	}

	// 주어진 값에 따라 배열 회전
	static int[][] rotate(int[][] srcArr, int r, int c, int s) {
		// 배열의 껍질 돌리기에 스킨위치를 지정하는 방식으로 수정
		int i = 0;
		int j = 0;
		int[][] tempArr = new int[arr.length][arr[0].length];
		for (int k = 0; k < tempArr.length; k++) {
			tempArr[k] = srcArr[k].clone();
		}
		int startN = r - s - 1;
		int startM = c - s - 1;
		int endN = r + s - 1;
		int endM = c + s - 1;

		while (endN >= 1 && endM >= 1) {
			i = startN;
			j = startM;
			// 위 오른쪽
			for (j = startM; j < endM; j++) {
				tempArr[i][j + 1] = srcArr[i][j];
			}
			// 오른쪽 아래
			for (i = startN; i < endN; i++) {
				tempArr[i + 1][j] = srcArr[i][j];
			}
			// 아래 왼쪽
			for (j = endM; j > startM; j--) {
				tempArr[i][j - 1] = srcArr[i][j];
			}
			// 왼쪽 위
			for (i = endN; i > startN; i--) {
				tempArr[i - 1][j] = srcArr[i][j];
			}

			startN++;
			startM++;
			endN--;
			endM--;
		}
		return tempArr;
	}

	static void setSum(int[][] srcArr) {
		for (int i = 0; i < srcArr.length; i++) {
			int sum = 0;
			for (int j = 0; j < srcArr[0].length; j++) {
				sum += srcArr[i][j];
			}
			if (sum < result) {
				result = sum;
			}
		}
	}

	// 배열 회전 조합값 출력
	static void perm(int cnt, int k, RCS[] input, RCS[] output, boolean[] isSelected, int R) {
		if (cnt == R) {
			// 이 부분안에 배열 변환을 넣고 결과값 처리하면 됨
			// RCS를 따라 배열 회전 후 출력
			int[][] srcArr = new int[arr.length][arr[0].length];
			for (int i = 0; i < srcArr.length; i++) {
				srcArr[i] = arr[i].clone();
			}
			for (RCS rcs : output) {
				srcArr = rotate(srcArr, rcs.r, rcs.c, rcs.s);
			}

			setSum(srcArr);

			return;
		}

		for (int i = 0; i < R; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			output[k] = input[i];
			perm(cnt + 1, k + 1, input, output, isSelected, R);
			isSelected[i] = false;
		}
	}
}
