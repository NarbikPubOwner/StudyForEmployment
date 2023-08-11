import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_16935_배열돌리기3 {

	static void spin1(int[][] arr, int N, int M) {
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M; j++) {
				int tempValue = arr[i][j];
				arr[i][j] = arr[N - i - 1][j];
				arr[N - i - 1][j] = tempValue;
			}
		}
	}

	static void spin2(int[][] arr, int N, int M) {
		for (int j = 0; j < M / 2; j++) {
			for (int i = 0; i < N; i++) {
				int tempValue = arr[i][j];
				arr[i][j] = arr[i][M - j - 1];
				arr[i][M - j - 1] = tempValue;
			}
		}
	}

	static int[][] spin3(int[][] arr, int N, int M) {

		int[][] tempArr = new int[M][N];
		int skinConut = 0;
		int CN = N;
		int CM = M;
		while (CN >= 1 && CM >= 1) {
//			CN = N - skinConut;
//			CM = M - skinConut;

			for (int j = skinConut; j < CM; j++) {
				tempArr[j][CN - 1] = arr[skinConut][j];
			}

			int tempJ = CN - 1;
			for (int i = skinConut; i < CN; i++) {
				tempArr[CM - 1][tempJ] = arr[i][CM - 1];
				tempJ--;
			}

			int tempI = CM - 1;
			for (int j = CM - 1; j >= skinConut; j--) {
				tempArr[tempI][skinConut] = arr[CN - 1][j];
				tempI--;
			}

			tempJ = skinConut;
			for (int i = CN - 1; i >= skinConut; i--) {
				tempArr[skinConut][tempJ] = arr[i][skinConut];
				tempJ += 1;
			}
			skinConut++;
			CN = N - skinConut;
			CM = M - skinConut;
		}
		return tempArr;
	}

	static int[][] spin4(int[][] arr, int N, int M) {
		int[][] tempArr = new int[M][N];
		int skinCount = 0;
		int CN = N;
		int CM = M;
		while (CN >= 1 && CM >= 1) {
//			CN = N - skinCount;
//			CM = M - skinCount;
			int tempJ = skinCount;
			for (int j = CM - 1; j >= skinCount; j--) {
				tempArr[tempJ][skinCount] = arr[skinCount][j];
				tempJ++;
			}

			int tempI = skinCount;
			for (int i = skinCount; i < CN; i++) {
				tempArr[CM - 1][i] = arr[i][skinCount];
				tempJ++;
			}

			tempI = CM - 1;
			for (int j = skinCount; j < CM; j++) {
				tempArr[tempI][CN - 1] = arr[CN - 1][j];
				tempI--;
			}

			tempJ = CN - 1;
			for (int i = CN - 1; i >= skinCount; i--) {
				tempArr[skinCount][tempJ] = arr[i][CM - 1];
				tempJ--;
				;
			}
			skinCount++;
			CN = N - skinCount;
			CM = M - skinCount;
		}

		return tempArr;
	}
	
	static int[][] spin5(int[][] arr, int N, int M) {
		int[][] tempArr = new int[N][M];
		
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				tempArr[i][M/2+j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < N/2; i++) {
			for (int j = M/2; j < M; j++) {
				tempArr[N/2+i][j] = arr[i][j];
			}
		}

		for (int i = N/2; i < N; i++) {
			for (int j = M/2; j < M; j++) {
				tempArr[i][j - M/2] = arr[i][j];
			}
		}
		
		for (int i = N/2; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				tempArr[i-N/2][j] = arr[i][j];
			}
		}

		return tempArr;
	}
	
	static int[][] spin6(int[][] arr, int N, int M) {
		int[][] tempArr = new int[N][M];
		
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				tempArr[i+N/2][j] = arr[i][j];
			}
		}
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				tempArr[i][j + M/2] = arr[i][j];
			}
		}

		for (int i = N/2; i < N; i++) {
			for (int j = M/2; j < M; j++) {
				tempArr[i-N/2][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < N/2; i++) {
			for (int j = M/2; j < M; j++) {
				tempArr[i][j-M/2] = arr[i][j];
			}
		}
		return tempArr;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] orgArr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				orgArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");

		while (st.hasMoreTokens()) {
			int rotateSort = Integer.parseInt(st.nextToken());
			if (rotateSort == 1) spin1(orgArr, orgArr.length, orgArr[0].length);
			else if (rotateSort == 2) spin2(orgArr, orgArr.length, orgArr[0].length);
			else if (rotateSort == 3) orgArr = spin3(orgArr, orgArr.length, orgArr[0].length);
			else if (rotateSort == 4) orgArr = spin4(orgArr, orgArr.length, orgArr[0].length);
			else if (rotateSort == 5) orgArr = spin5(orgArr, orgArr.length, orgArr[0].length);
			else if (rotateSort == 6) orgArr = spin6(orgArr, orgArr.length, orgArr[0].length);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < orgArr.length; i++) {
			for (int j = 0; j < orgArr[i].length; j++) {
				sb.append(orgArr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}



	
}
