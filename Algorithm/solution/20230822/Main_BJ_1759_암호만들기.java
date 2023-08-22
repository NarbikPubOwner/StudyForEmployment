import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 조합 문제
 * 마지막 출력 전 조건을 만족하는지(모음1개이상, 자음2개이상)확인 후 부합하면 출력
 * 사전순으로 정렬된 채로 나와야하는데, 인풋 자체를 정렬하면 됨
 */
public class Main_BJ_1759_암호만들기 {
	static int L;
	static int C;
	static String[] input;
	static char[] output;
	static StringBuilder sb = new StringBuilder();
	static boolean valid(char[] s) {
		int gatherCnt = 0;
		int consonantCnt = 0;
		for (int i = 0; i < s.length; i++) {
			switch (s[i]) {
			case 'a':
				gatherCnt++;
				break;
			case 'e':
				gatherCnt++;
				break;
			case 'i':
				gatherCnt++;
				break;
			case 'o':
				gatherCnt++;
				break;
			case 'u':
				gatherCnt++;
				break;

			default:
				consonantCnt++;
				break;
			}
		}
		if (gatherCnt >= 1 && consonantCnt >= 2) return true;
		return false;
	}
	
	static void comb(int cnt, int start) {
		if (cnt == L) {
			if (valid(output)) {
				for (char c :  output) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			output[cnt] = input[i].charAt(0);
			comb(cnt+1, i+1);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = br.readLine().split(" ");
		Arrays.sort(input);
		output = new char[L];
		
		comb(0, 0);
		System.out.println(sb);
	}

}
