import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 단순구현같은디
 * 1. 구슬을 날리면 깨트리는 것
 * 2. 깻을 때 연쇄 작용
 * 3. 다 깨고 난 후 벽돌 배열 갱신
 */
public class Solution_SWEA_5656_벽돌깨기 {

	static void destroy(int x, int y, int[][]arr) {
		if (arr[x][y] != 0) {
			//자기자신을 변경
			int range = arr[x][y];
			arr[x][y] = 0;
			//상하좌우 변경, 동시에 destroy dfs를 탄다
			for (int i = 0; i < 4; i++) {
				int nx = x;
				int ny = y;
				for (int j = 0; j < range-1; j++) {
					nx+=dx[i];
					ny+=dy[i];
					if (nx>-1&&nx<H &&ny>-1&&ny<W) {
						destroy(nx, ny, arr);
					}	
				}
			}
		}
	}
	
	static void fill(int[][] arr) {
		//아래에서부터 올라간다. 0이 아닌 수를 만나면 그 수를 맨 아래까지 끌어내린다.
		//0이 아닌 수를 만났을 때 그 아래에 0으로 빈 곳중에서 가장 아래로 옮긴다
		for (int i = arr.length-1; i >= 0; i--) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != 0) {
					int floorX = -1;
					int dx = 1;
					//여기서 내려가면서 확인
					while (i+dx < arr.length) {
						if (arr[i+dx][j] == 0) {
							floorX = i+dx;
							dx++;
						}
						else {
							break;
						}
					}
					if (floorX != -1) {
						arr[floorX][j] = arr[i][j];
						arr[i][j] = 0;
					}
					//맽 밑의 0을 찾아야 한다, 못 찾는다면 아무것도 안함
				}
			}
		}
	}
	
	static void dfs(int N, int[][] arr, int crashCnt) {
		if (N == crashCnt) {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if (arr[i][j] != 0) sum++;
				}
			}
			result = Math.min(result, sum);
			return;
		}
		
		fill(arr);
		
		for (int i = 0; i < W; i++) {
			int nx = 0;
			while (nx < H) {
				if (arr[nx][i] != 0) {
					int[][] tempArr = new int[H][W];
					for (int j = 0; j < H; j++) {
						tempArr[j] = arr[j].clone();
					}
					destroy(nx, i, tempArr);
					dfs(N, tempArr, crashCnt+1);
					break;
				}
				nx++;
			}
		}
	}
	
	static int result;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	static int H;
	static int W;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			result = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] arr = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(N, arr, 0);
			
			sb.append("#").append(t).append(" ");
			if (result == Integer.MAX_VALUE) {
				sb.append(0);
			}
			else {
				sb.append(result);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}



