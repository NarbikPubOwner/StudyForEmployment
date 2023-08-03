import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA농작물수확하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	int T = Integer.parseInt(br.readLine());
	 	
	 	for (int t = 0; t < T; t++) {
	 		int result = 0;
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(Character.toString(s.charAt(j)));
				}
			}
			result += arr[N/2][0];
			int dif = 1;
			int dt = 1;
			while (N/2+dif < N && N/2-dif > -1) {
				for (int i = (N/2-dif); i <= (N/2 + dif); i++) {
					result += arr[i][dt];
				}
				dt++;
				dif++;
			}
			dif -= 2;
			while (dt < N) {
				for (int i = (N/2-dif); i <= (N/2 + dif); i++) {
					result += arr[i][dt];
				}
				dt++;
				dif--;
			}
			System.out.println("#"+(t+1)+" "+result);
		}
	}
}
