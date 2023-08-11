import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PermutationOnBitTest {//순열 생성, 중복 없음
	static int N;
	static int R;
	static int[] input;
	static int[] numbers;
	static void perm(int cnt, int flag) {
		if (cnt == R) {
//			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			//중복체크
			if ((flag & 1<<i) != 0) continue;
			numbers[cnt] = input[i];
			perm(cnt+1, flag | 1<< i);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		input = new int [N];
		numbers = new int [R];
		
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		long start = System.nanoTime();
		perm(0, 0);
		long end = System.nanoTime();
		System.out.println((end-start)/1_000_000_000.0);
		
		
		
	}

}
