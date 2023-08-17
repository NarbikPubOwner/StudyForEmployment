import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_최적경로 {
	static int[] companyXY;
	static int[] homeXY;
	static List<int[]> customerXY;
	static boolean[] isSelected;
	static int[] output;
	static int N;
	static int result;
	
	static void getDistance() {
		//시작 : 회사xy  | 중간 : customerxy | 끝 : 집xy
		int curX = companyXY[0];
		int curY = companyXY[1];
		int distance = 0;
		for (int n = 0; n < N; n++) {
			int[] tempXY = customerXY.get(output[n]);
			distance += Math.abs( curX - tempXY[0] ) + Math.abs(curY - tempXY[1]) ;
			curX = tempXY[0];
			curY = tempXY[1];
//			(x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|
		}
		distance += Math.abs( curX - homeXY[0] ) + Math.abs(curY - homeXY[1]);
		result = Math.min(distance, result);
	}
	static void recursive(int cnt) {
		if (cnt == N) {
			//경로 계산
			getDistance();
			return;
		}
		
		for (int n = 0; n < N; n++) {
			if (isSelected[n]) continue;
			output[cnt] = n;
			isSelected[n] = true;
			recursive(cnt+1);
			isSelected[n] = false;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		companyXY = new int[2];
		homeXY = new int[2];
		
		for (int t = 0; t < T; t++) {
			result = Integer.MAX_VALUE;
			N =Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			output = new int[N];
			
			companyXY[0] = Integer.parseInt(st.nextToken());
			companyXY[1] = Integer.parseInt(st.nextToken());
			
			homeXY[0] = Integer.parseInt(st.nextToken());
			homeXY[1] = Integer.parseInt(st.nextToken());
			
			customerXY = new ArrayList<>();
			isSelected = new boolean[N];
			for (int n = 0; n < N; n++) {
				customerXY.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
			}
			
			recursive(0);
			sb.append("#").append((t+1)).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		
		
	}

}
