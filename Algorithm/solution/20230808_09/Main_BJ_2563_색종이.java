import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2563_색종이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//기본적으론 배열을 만들고 범위에 속하면 값 변경
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int R = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] arr = new int[100][100];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int i = x; i < x+10; i++) {
				for (int j = y; j < y+10; j++) {
					if (i <= 100 && j <= 100) {
						arr[i][j] = 1;
					}
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (arr[i][j] == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
