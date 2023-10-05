import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 반복
 * 4개로 자른다
 * 자른애들을 10진수로 변환해서 map에 넣는다
 * 회전 시킨다
 * 
 * 투포인터? 
 * 
 */
public class Solution_SWEA_5658_보물상자비밀번호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answerSB = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int subLength = N/4;
			
//			Set<Integer> set = new HashSet<>();
			TreeSet<Integer> set = new TreeSet<>(Comparator.reverseOrder());
			
			StringBuilder sb = new StringBuilder(br.readLine());
			for (int j = 0; j < 4; j++) {
				int start = j*subLength;
				set.add(Integer.parseInt(sb.substring(start, start + subLength),16));
			}
			for (int i = 1; i < subLength+1; i++) {
				StringBuilder temp = new StringBuilder();
				temp.append(sb.charAt(N-1)).append(sb.substring(0, N-1));
				sb = temp;
				for (int j = 0; j < 4; j++) {
					int start = j*subLength;
					set.add(Integer.parseInt(sb.substring(start, start + subLength),16));
				}
			}
			
			Iterator iter =  set.iterator();
			
			while (iter.hasNext()) {
				K--;
				if (K == 0) {
					answerSB.append("#").append(t).append(" ").append(iter.next()).append("\n");
					break;
				}
				else iter.next();
			}
		}	
		System.out.println(answerSB);
	}
}
