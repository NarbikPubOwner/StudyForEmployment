import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;


//차분히 재귀구조를 생각하면 풀림
//수가 커지면 Long으로도 표현이 안되기 때무넹 BigInteger클래스 사용
public class Main {
	static int count = 0;
	static StringBuilder sb = new StringBuilder(); 
	static void WriteHanoi(int N, int start, int mid, int dest) {
		if (N == 1) {
			count ++;
			sb.append(start+" "+dest+"\n");
			return;
		}
		WriteHanoi(N-1, start, dest, mid);
		count++;
		sb.append(start+" "+dest+"\n");
		//진행
		WriteHanoi(N-1, mid , start, dest);
	}
	
	static void Hanoi(int N, int start, int mid, int dest) {
		BigInteger bigCount = new BigInteger("2");
		System.out.println(bigCount.pow(N).subtract(new BigInteger("1")));
		return;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if (N <= 20) {
			WriteHanoi(N, 1, 2, 3);
			System.out.println(count);
			System.out.println(sb);
		}
		else {
			Hanoi(N, 1, 2, 3);
		}	
	}
}
