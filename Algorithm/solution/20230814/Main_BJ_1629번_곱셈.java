import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1629번_곱셈 {
	
	static long recursive(long x, long n, long c) {
		if (n == 0) return 1;
		if (n == 1) return x%c;
		
		long y = recursive(x, n/2, c);
		if (n%2 ==0) {
			return y*y%c;
		}
		else {
			return (x*y%c)*y%c;
			
		
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		
		System.out.println(recursive(A, B, C)%C);
	}
}
