import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1786_백준 {
	
	static StringBuilder locationSB = new StringBuilder();//답들의 위치를 저장할 스트링빌더
	//부분 일치 테이블 생성
	static int[] makeTable(int N, String pattern) {
		int[] table = new int[N];
		
		int idx = 0;//접두 끝 부분 인덱스
		
		for (int i = 1; i < N; i++) {//i는 pattern의 접미 부분을 나타낸다
			/*
			 * idx가 0보다 크다는 것은 일치하는 접두접미를 찾았다는 이야기이다, 그 상태에서 일치하지 않는 접두접미가 발생한다면
			 * 접두의 idx위치를 조절한다, 만약 계속해서 불일치한다면 0이 될떄까지(매치되는게 없을떄까지)반복한다
			 */
			while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			/*
			 * 일치한다면 접두의 위치 +, 그리고 다음 for문으로 돌아가니 접미의 위치도 자동으로 증가한다
			 */
			if (pattern.charAt(i) == pattern.charAt(idx)) {
				idx++;
				table[i] = idx;//문자열이 i까지 일치할 경우의 부분 일치 길이를 저장한다
			}
		}
		
		return table;
	}
	
	/*
	 * T에 P문자열이 몇개 있는지, 위치가 어디인지 구한다
	 */
	//다른 구현 방식
	static int KMP(String T,String P, int[] table) {
		int n1 = T.length();
		int n2 = P.length();
	
		int count = 0;
	
		int idx = 0;//패턴 idx
		for (int i = 0; i < n1; i++) {
			while (idx > 0 && T.charAt(i) != P.charAt(idx)) {
				idx = table[idx-1];//문자가 일치하지 않는다면 이전 패턴 일치로 idx를 이동, 마지막까지 일치하지 않는다면 0번째까지 이동한다
			}
			if (T.charAt(i) == P.charAt(idx)) {
				if (idx == n2-1) {//일치하는 문자열을 찾았다면
					locationSB.append(i-idx+1).append(" ");
					count++;
					idx = table[idx];//바로 다음 문자와 비교하고 정답이 될 수도 있기 때문에 idx를 유지한다
				}else {
					idx += 1;//정답이 아니고, 문자가 동일하다면 접두의 idx 이동
				}
			}
			
		}
		
		return count;
	}
	//전통적인 구현방식
//	static int KMP(String T,String P, int[] table) {
//		int n1 = T.length();
//		int n2 = P.length();
//		int begin = 0;
//		int matched = 0;
//		int count = 0;
//		while (begin <= n1-n2) {//남은 문자열의 길이가 N2보다 작다면
//			if (matched < n2 && T.charAt(begin + matched) == P.charAt(matched)) {//문자가 매칭 된다면
//				matched++;//매칭 길이 증가
//				if (matched == n2) {//패턴을 찾았다면
//					locationSB.append(begin+1).append(" ");
//					count++;
//				}
//			}
//			else {
//				if (matched == 0) begin++;//매칭된 문자가 없다면 시작점은 1만 늘리면 된다
//				else {
//					begin += matched - table[matched-1];//비일치시 다음 시작점을 접두의 다음 부분으로 이동한다
//					matched = table[matched-1];//매칭된 숫자또한 접두의 길이로 변경한다
//				}
//			}
//		}
//		
//		return count;
//	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		
		int[] table = makeTable(P.length(), P);
		int sum = KMP(T, P, table);
		System.out.println(sum);
		System.out.println(locationSB);
		
	}
}
