import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * dfs타면 되지 않나? ㅇ
 */
public class Main_BJ_9095_123더하기 {
	static int cnt;
	static void dfs(int N) {
		if (N == 0) {
			cnt++;
			return;
		}
		
		if (N-1 >= 0) {
			dfs(N-1);
			if(N-2 >= 0) {
				dfs(N-2);
				if (N-3 >= 0) {
					dfs(N-3);
				}
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			cnt = 0;
			int N = Integer.parseInt(br.readLine());
			dfs(N);
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

}
