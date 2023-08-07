package javapractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_암호문1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			StringBuilder sb = new StringBuilder("");
			
			int N = Integer.parseInt(br.readLine());
			LinkedList<String> SLL = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			while (st.hasMoreTokens()) {
				SLL.add(st.nextToken());
				
			}
			
			//분리부터 해 봅시다
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), "I");
			
			while (st.hasMoreTokens()) {
				StringTokenizer st2 = new StringTokenizer(st.nextToken(), " ");
				String[] sArr = new String[st2.countTokens()];
				for (int i = 0; i < sArr.length; i++) {
					sArr[i] = st2.nextToken();
				}
//				System.out.println(Arrays.toString(sArr));
				int targetIdx = Integer.parseInt(sArr[0]);
				int commandLength = Integer.parseInt(sArr[1]);
				for (int i = 0; i < commandLength; i++) {
					SLL.add(targetIdx + i, sArr[i+2]);
				}
				
			}
			
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(SLL.get(i)).append(" ");
			}
			sb.append("\n");
			System.out.print(sb);
		}
			
	}

}
