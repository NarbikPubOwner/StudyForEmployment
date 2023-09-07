import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BJ_11723_집합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			String cal = st.nextToken();
			int x = 0;
			if (st.hasMoreTokens()) {
				x = Integer.parseInt(st.nextToken());
			}
			
//			
			switch (cal) {
			case "add":
				hm.put(x, 1);
				break;
			case "remove":
				hm.remove(x);
				break;
			case "check":
				if (hm.containsKey(x)) {
					sb.append(1);
				}
				else {
					sb.append(0);
				}
				sb.append("\n");
				break;
			case "toggle":
				if (hm.containsKey(x)) {
					hm.remove(x);
				}
				else {
					hm.put(x, 1);
				}
				break;
			case "all":
				hm.clear();
				for (int j = 1; j <= 20; j++) {
					hm.put(j, 1);
				}
				break;
			case "empty":
				hm.clear();
				break;
			default:
				break;
			}
	
		}
		System.out.println(sb);
				
	}

}
