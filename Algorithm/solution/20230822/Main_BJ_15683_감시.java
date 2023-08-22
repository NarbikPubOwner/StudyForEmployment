import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 전체 순회를 하면서 값을 찾는게 아니라, 변화할 수 있는 값 들만 List에 저장한다(i,j,v) ex) 1,2,3,4 (5는 회전이 의미 없고, 6은 벽이다)
 * 2. List값들에 대해서만 dfs를 한다
 * 3. ij가 RC에 도달하면 해당 배열의 사각지대가 아닌 곳을 판별한다
 */
public class Main_BJ_15683_감시 {
	static int R;
	static int C;
	static int[][] arr;
	static int result = Integer.MAX_VALUE;
	static List<int[]> cctvList = new ArrayList<>();
	
	/*
	 * 조합이기 때문에 start를 가진다
	 */
	static void dfs(int cnt, int start) {
		if (cnt == cctvList.size()) {//모든 변경사항이 이루어 졌으면
			result = Math.min(result, getCount());//미사각지대를 판별한다
			return;
		}
		/*
		 * 각 cctv의 회전의 표현을 배열의 값을 바꾸는 것으로 표현한다
		 * 1 -> 789
		 * 2 -> 10
		 * 3 -> 11 12 13
		 * 4 -> 14 15 16
		 */
		for (int i = start; i < cctvList.size(); i++) {
			int[] cctv = cctvList.get(i);//[i,j,v]
			/*
			 * 회전만큼 dfs탐색
			 */
			switch (cctv[2]) {
			case 1:
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 7;
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 8;
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 9;
				dfs(cnt+1, i+1);
				break;
			case 2:
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 10;
				dfs(cnt+1, i+1);
				break;
			case 3:
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 11;
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 12;
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 13;
				dfs(cnt+1, i+1);
				break;
			case 4:
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 14;
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 15;
				dfs(cnt+1, i+1);
				arr[cctv[0]][cctv[1]] = 16;
				dfs(cnt+1, i+1);
				break;

			default:
				break;
			}
			arr[cctv[0]][cctv[1]] = cctv[2];//dfs탐색이 끝나면 원래 값으로 되돌려준다
		}
	}
	
	static int getCount() {
		int cnt = 0;
		int[][] tempArr = new int[R][C];//원본 배열은 건드리면 안되기 때문에 복사할 배열 생성
		for (int i = 0; i < tempArr.length; i++) {
			tempArr[i] = arr[i].clone();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int tempI = i;
				int tempJ = j;
				/*
				 * cctv값에 따른 사각지대 판별 하드코딩
				 */
				switch (tempArr[i][j]) {
				case 1:
					tempI = i;
					tempJ = j+1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ++;
						}
					break;
				case 2:
					tempI = i;
					tempJ = j+1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ++;
						}
					tempI = i;
					tempJ = j-1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ--;
						}
					break;
				case 3:
					tempI = i-1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI--;
						}
					tempI = i;
					tempJ = j+1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ++;
						}
					break;
				case 4:
					tempI = i-1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI--;
						}
					tempI = i;
					tempJ = j+1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ++;
						}
					tempI = i;
					tempJ = j-1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ--;
						}
					break;
				case 5:
					tempI = i-1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI--;
						}
					tempI = i;
					tempJ = j+1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ++;
						}
					tempI = i+1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI++;
						}
					tempI = i;
					tempJ = j-1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ--;
						}
					break;
				case 7:
					tempI = i+1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI++;
						}
					break;
				case 8:
					tempI = i;
					tempJ = j-1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ--;
						}
					break;
				case 9:
					tempI = i-1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI--;
						}
					break;
				case 10:
					tempI = i+1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI++;
						}
					tempI = i-1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI--;
						}
					break;
				case 11:
					tempI = i;
					tempJ = j+1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ++;
						}
					tempI = i+1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI++;
						}
					break;
				case 12:
					tempI = i;
					tempJ = j-1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ--;
						}
					tempI = i+1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI++;
						}
					break;
				case 13:
					tempI = i;
					tempJ = j-1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ--;
						}
					tempI = i-1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI--;
						}
					break;
				case 14:
					tempI = i-1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI--;
						}
					tempI = i;
					tempJ = j+1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ++;
						}
					tempI = i+1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI++;
						}
					break;
				case 15:
					tempI = i+1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI++;
						}
					tempI = i;
					tempJ = j+1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ++;
						}
					tempI = i;
					tempJ = j-1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ--;
						}
					break;
				case 16:
					tempI = i-1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI--;
						}
					tempI = i+1;
					tempJ = j;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempI++;
						}
					tempI = i;
					tempJ = j-1;
					while (tempI > -1 && tempI < R && tempJ > -1 && tempJ < C && tempArr[tempI][tempJ] != 6) {
							if (tempArr[tempI][tempJ] == 0 ) tempArr[tempI][tempJ] = -1;
							tempJ--;
						}
					break;
				
					
				default:
					break;
				}
			}
		}
		/*
		 * 판별 후에 0인 부분 -> 미 사각지대, 이 부분을 카운트한다
		 */
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (tempArr[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0 && arr[i][j] != 6 && arr[i][j] != 5) {
					cctvList.add(new int[] {i,j,arr[i][j]});
				}
			}
		}
		
		dfs(0, 0);
		System.out.println(result);
	}
}

