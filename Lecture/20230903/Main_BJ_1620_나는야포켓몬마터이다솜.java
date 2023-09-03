import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BJ_1620_나는야포켓몬마터이다솜 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] sArr = new String[N];
		HashMap<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < N; i++) {
			sArr[i] = br.readLine();
			hm.put(sArr[i], i+1);
		}
		
		
		
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			char f = s.charAt(0);
			if ( f == '1' ||f == '2' ||f == '3' ||f == '4' ||f == '5' ||f == '6' ||f == '7' ||f == '8' || f=='9') {
				sb.append(sArr[Integer.parseInt(s)-1]).append("\n");
//				System.out.println(sArr[Integer.parseInt(s)-1]);
			}
			else {
				sb.append(hm.get(s)).append("\n");
//				System.out.println(hm.get(s));
			}
		}
		System.out.println(sb);
	}
}
