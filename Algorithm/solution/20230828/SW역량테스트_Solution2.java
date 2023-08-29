import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW역량테스트_Solution2 {

	// 좌측 하단, 우측 하단은 인접으로 치지 않는다
	// 즉 6방 탐색

	static int[][] arr;
	static int[] output;
	static int W;
	static int H;

	static int[] dx = new int[] { -1, -1, -1, 0, 0, 1 };
	static int[] dy = new int[] { -1, 0, 1, -1, 1, 0 };

	static int[] dx2 = new int[] { 0, -1, 0, 1, 1, 1 };
	static int[] dy2 = new int[] { -1, 0, 1, -1, 0, 1 };

	static long result;

	// 중복 걸러야 함
	static boolean[][] isVisited;

	static void dfs(int cnt, int i, int j) {

		if (cnt == 4) {
			// 값 출력
			int sum = 0;
			for (int k = 0; k < 4; k++) {
				sum += output[k];
			}
			System.out.println(Arrays.toString(output));
			if (result < sum * sum) {
				result = sum * sum;
				System.out.println(Arrays.toString(output));
			}

			return;
		}

		// w가 짝수냐 홀수에 따라 인접이 달라진다?
		if (j == 0 || j % 2 == 0) {
			for (int k = 0; k < 6; k++) {
				int nx = i + dx[k];
				int ny = j + dy[k];
				if (nx > -1 && nx < H && ny > -1 && ny < W && !isVisited[nx][ny]){
					isVisited[nx][ny] = true;
					output[cnt] = arr[nx][ny];
					dfs(cnt + 1, nx, ny);
					dfs(cnt + 1, i, j);
					isVisited[nx][ny] = false;
				}
			}
		} else {
			for (int k = 0; k < 6; k++) {
				int nx = i + dx2[k];
				int ny = j + dy2[k];
				if (nx > -1 && nx < H && ny > -1 && ny < W && !isVisited[nx][ny]) {
					isVisited[nx][ny] = true;
					output[cnt] = arr[nx][ny];
					dfs(cnt + 1, nx, ny);
					dfs(cnt + 1, i, j);
					isVisited[nx][ny] = false;
				}
				
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			arr = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = 0;
			output = new int[4];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
//					output = new int[4];
					isVisited = new boolean[H][W];
					output[0] = arr[i][j];
					isVisited[i][j] = true;
					dfs(1, i, j);
				
				}
			}
			System.out.println();
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);

	}
}

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class Solution2 {
//	
//	//좌측 하단, 우측 하단은 인접으로 치지 않는다
//	//즉 6방 탐색
//	
//	static int[][] arr;
//	static int[] output;
//	static int W;
//	static int H;
//	
//	static int[] dx = new int [] {-1,-1,-1,0,0,1};
//	static int[] dy = new int [] {-1,0,1,-1,1,0};
//	
//	static int[] dx2 = new int [] {0,-1,0,1,1,1};
//	static int[] dy2 = new int[] {-1,0,1,-1,0,1};
//	
//	static long result;
//	
//	//중복 걸러야 함
//	static boolean[][] isVisited;
//	static void dfs (int cnt, int i, int j) {
//		
//		if (cnt == 4) {
//			//값 출력
//			int sum = 0;
//			for (int k = 0; k < 4; k++) {
//				sum += output[k];
//			}
//			System.out.println(Arrays.toString(output));
//			if (result < sum*sum) {
//				result = sum*sum;
////				System.out.println(Arrays.toString(output));
//			}
//			
//			return;
//		}
//		if (i < 0 || i > H-1 || j < 0 || j > W-1) return;
//		
//		if (!isVisited[i][j]) {
//			isVisited[i][j] = true;
//			output[cnt] = arr[i][j];
//			
//			//w가 짝수냐 홀수에 따라 인접이 달라진다?
//			if (j == 0 || j%2 == 0) {
//				for (int k = 0; k < 6; k++) {
//					dfs(cnt+1, i+dx[k], j+dy[k]);
//				}
//			}
//			else {
//				for (int k = 0; k < 6; k++) {
//					dfs(cnt+1, i+dx2[k], j+dy2[k]);
//				}
//			}
//			isVisited[i][j] = false;
//		}
//		
//		
//	}
//	
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		int T = Integer.parseInt(br.readLine());
//		for (int t = 1; t <= T; t++) {
//			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//			W = Integer.parseInt(st.nextToken());
//			H = Integer.parseInt(st.nextToken());
//			arr = new int[H][W];
//			isVisited = new boolean[H][W];
//			
//			for (int i = 0; i < H; i++) {
//				st = new StringTokenizer(br.readLine()," ");
//				for (int j = 0; j < W; j++) {
//					arr[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//			output = new int[4];
//			result = 0;
//			for (int i = 0; i < H; i++) {
//				for (int j = 0; j < W; j++) {
//					dfs(0,i,j);
//				}
//			}
//			sb.append("#").append(t).append(" ").append(result).append("\n");
//		}
//		System.out.println(sb);
//		
//	}
//}

