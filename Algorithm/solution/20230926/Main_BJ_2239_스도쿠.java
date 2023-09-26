import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * dfs로 1~9를 채우고 타당성 검증
 * 백트래킹 : 숫자를 넣으면 해당 숫자가 들어간 행, 열, 33의 타당성 검증하고, 타당하다면 dfs를 탄다
 * 기저조건 : i,j가 마지막 행열에 도착했다는 것은, 정상적인 스도쿠가 완성되었다는 것이기 때문에 배열을 출력하고 dfs탐색을 종료한다
 */

public class Main_BJ_2239_스도쿠 {
	static int[][] arr;
	static boolean isFinish = false;
	static int maxCount = 0;
	static StringBuilder sb = new StringBuilder();
	
	//n이 스도쿠에 적절한 수인지 판단
	static boolean valid(int n, int x, int y) {
		
		//행 판별
		for (int i = 0; i < 9; i++) {
			if (arr[x][i] == n) return false;
		}
		//열 판별
		for (int i = 0; i < 9; i++) {
			if (arr[i][y] == n) return false;
		}
		//33판별
		for (int i = (x/3)*3; i < 3*(x/3)+3; i++) {
			for (int j = (y/3)*3; j < 3*(y/3)+3; j++) {
				if (arr[i][j] == n) return false;
			}
		}
		return true;
	}
	
	static void printArr(int[][] arr) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int i, int j) {
		if (j == 9) {//행 인덱스를 벗어나면 다음 열로 탐색을 시작한다
			dfs(i+1, 0);
			return;
		}
		
		if (i == 9) {//열 인덱스를 벗어났다면 모든 스도쿠를 채운 경우이다, 기저조건
			printArr(arr);
			System.exit(0);
		}
		
		if (arr[i][j] == 0) {//스도쿠 값이 0이라면 채워야 하기 때문에
			for (int k = 1; k <= 9; k++) {//1~9선택
				if (valid(k, i, j)) {//해당 숫자가 삽입 가능하다면
					arr[i][j] = k;//삽입
					dfs(i,j+1);//넣은 수를 기반으로 탐색
				}
			}
			arr[i][j] = 0;//탐색을 마치면 다른 dfs에서 정상적으로 arr을 참조할 수 있게 0으로 되돌려준다
			return ;//1~9모든 수가 적절치 않다면 해당 dfs는 버려야 하기 때문에 return을 한다
		}
		dfs(i,j+1);//0이 아니라면 그냥 다음 행의 스도쿠로 이동
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int [9][9];
		for (int i = 0; i < 9; i++) {
			String input = br.readLine();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(Character.toString(input.charAt(j)));

			}
		}
		
		dfs(0,0);
		System.out.println(sb);
		
		
	}

}
