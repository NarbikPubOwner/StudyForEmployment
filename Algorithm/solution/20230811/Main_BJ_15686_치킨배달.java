import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_15686_치킨배달 {

	static int N;
	static int M;
	static int[][] arr;
	static int[] changedChickenArrX;
	static int[] changedChickenArrY;
	static List<int[]> homeList;
	static List<int[]> chickenList;
	static int result = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];

		homeList = new ArrayList<>();
		chickenList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) homeList.add(new int[] {i,j});
				else if (arr[i][j] == 2) chickenList.add(new int[] {i,j});
			}
		}
		changedChickenArrX = new int[M];
		changedChickenArrY = new int[M];
		comb(0,0);
		System.out.println(result);
//		System.out.println(getChickenDistance(homeList, chickenList));
	}

	/*
	 * 치킨집의 좌표 모음을 만든다
	 * 
	 * 치킨집의 좌표 개수를 N이고, 필요한 치킨집 개수가 R이라면 nCr을 구한다
	 * 
	 * 구해진 원소값에 따라 배열의 치킨집을 수정하고 그 배열을 넣어 값을 출력한다 -> 아마 시간초과가 날 수 있는데 그렇다면 처음부터 값을 넘기고 끊는 식으로 개선
	 */
	static void comb(int m, int start) {
		if (m == M) {
			for (int i = 0; i < M; i++) {
//				System.out.print( changedChickenArrX[i] + "," + changedChickenArrY[i] + " ");
				result = Math.min(result, getChickenDistance());
			}
//			System.out.println();
			return;
		}
		
		for (int i = start; i < chickenList.size(); i++) {
			changedChickenArrX[m] = chickenList.get(i)[0];
			changedChickenArrY[m] = chickenList.get(i)[1];
			comb(m+1, i+1);
		}
	}
	
	/*
	 * 치킨거리를 찾는 함수
	 */
	static int getChickenDistance() {

		int sumResult = 0;
		for (int i = 0; i < homeList.size(); i++) {
			int result = Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) {
				result = Math.min(
						Math.abs(homeList.get(i)[0] - changedChickenArrX[j]) + Math.abs(homeList.get(i)[1] - changedChickenArrY[j]),
						result);
//				result = Math.min(
//						Math.abs(homeList.get(i)[0] - chickenList.get(j)[0]) + Math.abs(homeList.get(i)[1] - chickenList.get(j)[1]),
//						result);
			}
			sumResult += result;
		}
		return sumResult;
	}
}
