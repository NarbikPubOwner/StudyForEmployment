import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_3109_빵집 {

	static int[] dx = new int[] { -1, 0, 1 };
	static int[][] arr;
	static int R;
	static int C;
	static int count=0;
	static boolean[] isFinal;

	// 재귀 끝내고 나오면 값을 기억했다가 되돌리기
	// 그렇게 해서 최종 출력
	static void dfs(int r, int c, int start) {
		if (c == C) {
			// 끝에 도달
			isFinal[start] = true;
			return;
		}
		if (r < 0 || r >= R || c < 0 || arr[r][c] == 1 || isFinal[start]) {
			// 범위를 벗어낫거나, 장애물을 만나면 멈추기
			return;
		}

		for (int i = 0; i < 3; i++) {
			arr[r][c] = 1;
			dfs(r + dx[i], c + 1, start);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 일단은 첫번째 행의 도달 가능한 모든 경우의 수 출력하기
		// 012 넘어간다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String[] s;
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		isFinal = new boolean[R];
		// . == 0, X = 1, 지나간 곳 = 2;
		for (int i = 0; i < R; i++) {
			s = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				if (s[j].equals("."))
					arr[i][j] = 0;
				else
					arr[i][j] = 1;
			}
		}
		for (int j = 0; j < R; j++) {
			dfs(j, 0, j);
		}
		
		for (int i = 0; i < isFinal.length; i++) {
			if (isFinal[i]) count++;
		}
		System.out.println(count);
	}

}
