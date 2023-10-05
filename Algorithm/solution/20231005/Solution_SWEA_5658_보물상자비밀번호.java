import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution_SWEA_5658_보물상자비밀번호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answerSB = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int subLength = N/4;//나눠질 문자열의 길이
			TreeSet<Integer> set = new TreeSet<>(Comparator.reverseOrder());//중복이 없어야 하기 때문에 set 사용 + 정렬일 필요하기 때문에 treeSet + 내림차순 정렬을 위한 리버스오더
			
			StringBuilder sb = new StringBuilder(br.readLine());
			for (int i = 0; i <= subLength; i++) {//잘려질 문자열의 길이 = 회전 횟수이다
				StringBuilder temp = new StringBuilder();
				temp.append(sb.charAt(N-1)).append(sb.substring(0, N-1));//문자열의 끝부분 + 0~N-2를 하면 회전된 문자열을 얻는다
				sb = temp;//회전은 다음에도 반영되어야 하니 원본 문자열에 반입
				/*
				 * 문자열을 4개로 나누는 작업
				 */
				for (int j = 0; j < 4; j++) {
					int start = j*subLength;
					set.add(Integer.parseInt(sb.substring(start, start + subLength),16));//16진수 -> 10진수로 변환해 set에 삽입
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
