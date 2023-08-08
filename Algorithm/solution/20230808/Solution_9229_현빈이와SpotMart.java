import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_현빈이와SpotMart {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = -1;
			for (int i = 0; i < arr.length; i++) {
				
				for (int j = i+1; j < arr.length; j++) {
					if ((arr[i] + arr[j]) <= max) {
						result = Math.max(result, arr[i] + arr[j]);
					}
				}
			}
			StringBuilder sb = new StringBuilder("");
			sb.append("#").append(t+1).append(" ").append(result);
			System.out.println(result);
		}
	}
}
