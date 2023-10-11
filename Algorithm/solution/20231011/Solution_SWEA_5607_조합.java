import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//https://codingtalk.tistory.com/94
public class Solution_SWEA_5607_조합 {
	
	static long MOD = 1234567891;
	
	static void getFac(long[] fac) {
		fac[1] = 1;
		for (int i = 1; i < fac.length-1; i++) {
			fac[i+1] = (fac[i]*(i+1))%MOD;
		}
	}
	
	static long powByMod(long value, long exponent) {
		if (exponent == 0) {
			return 1;
		}
		if (exponent == 1) {
			return value;
		}
		if (exponent%2 == 0) {
			long tmp = powByMod(value, exponent/2);
			return (tmp*tmp)%MOD;
		}
		long tmp = powByMod(value, exponent-1);
		return (tmp*value)%MOD;
	}
	
	static long getCombNumOfCase(long[] fac, int N, int R) {
		getFac(fac);
		long bottom = (fac[N-R]*fac[R])%MOD;
		bottom = powByMod(bottom, MOD-2);
		return (fac[N]*bottom)%MOD;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			long[] fac = new long[N+1];
			sb.append("#").append(t).append(" ").append(getCombNumOfCase(fac, N, R)).append("\n");
		}
		System.out.println(sb);
	}
}
