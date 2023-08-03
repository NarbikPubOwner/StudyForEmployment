import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class BJ_2023_신기한소수 {
	static int N;//자리수
	static String[] Nums = new String[] {"1","2","3","4","5","6","7","8","9","0"};//수 구성에 쓰일 배열
	static StringBuilder sb = new StringBuilder();
	/*
	 * 소수인지 아닌지 판별하는 함수
	 * 해당 수의 제곱근 이하의 수들로 나누어 떨어지지 않으면 소수이다
	 */
	static boolean isSosu(String num) {
		int intNum = Integer.parseInt(num);
		if (intNum == 1) {
			return false;
		}
		for (int i = 2; i <= (int)(Math.pow(intNum, 0.5)); i++) {
			if ((intNum % i) == 0) {
				return false;
			}
		}
		return true;
	}
	/*
	 * 특정 수의 1의 자리를 지우면서 얻어진 각 수 모두가 소수임을 확인해야 한다
	 * 자릿수가 정해져 있기 때문에 자릿수를 만족하는 수들을 String의 조립으로 얻을 수 있다. 
	 * 중복 순열이다
	 */
	static void recursive(String num) {
		if (num.length() == N) {//넘겨받은 문자열이 자릿수를 채웠다면 재귀를 종료한다
			sb.append(num).append("\n");
			return;
		}
		for (int i = 0; i < Nums.length; i++) {
			if (num.length() == 0 && Nums[i].equals("0")) {
				continue;
			}
			String newNum = num + Nums[i];
			if (isSosu(newNum)) {//현재 얻은 수가 소수라면 다음 수로 진행한다
				recursive(newNum);
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		recursive("");
		System.out.println(sb);
	}
}
