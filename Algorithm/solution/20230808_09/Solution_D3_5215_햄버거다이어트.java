import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_D3_5215_햄버거다이어트 {

	//nPn조합 - 중복된 수가 들어가면 안되고, 순서의 의미가 없다
	static List<int[]> list;
	static int N;
	static int MaxCalorie;
	static int result;
	static boolean[] isSelected;//Pick된 정보를 저장하는 역할
	static void recursive(int cnt) {
		if (cnt == N) {
			int point = 0;
			int calorie = 0;
			
			for (int i = 0; i < isSelected.length; i++) {
				if (isSelected[i]) {
					point+=list.get(i)[0];
					calorie+=list.get(i)[1];
				}
			}
			
			if (calorie <= MaxCalorie) {
				if (point > result) {
					result = point;
				}
			}
			
			return;
		}
		isSelected[cnt] = true;
		recursive(cnt+1);
		isSelected[cnt] = false;
		recursive(cnt+1);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine()," ");
			
			result = 0;
			N = Integer.parseInt(st.nextToken());
			MaxCalorie = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			isSelected = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int[] temp = new int[] {Integer.parseInt(st.nextToken()),  Integer.parseInt(st.nextToken())};
				list.add(temp);
			}
			
			recursive(0);
			System.out.println("#"+(t+1) + " " + result);
		}

	}

}
