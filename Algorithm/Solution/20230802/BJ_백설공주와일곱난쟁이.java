import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_백설공주와일곱난쟁이 {

	
	static void recursive(int[] input, int[] arr, int cnt, int start) {
		if (cnt == arr.length) {
			int sum = 0;
			for (int i : arr) {
				sum += i;
			}
			if (sum == 100) {
				StringBuilder sb = new StringBuilder();
				Arrays.sort(arr);
				for (int i : arr) {
					sb.append(i).append("\n");
				}
				System.out.println(sb);
			}
			return;
		}
		
		for (int i = start; i < input.length; i++) {
			arr[cnt] = input[i];
			recursive(input, arr, cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[7];
		int[] input = new int[9];
		for (int i = 0; i < input.length; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		recursive(input, arr, 0, 0);
	}	
}
