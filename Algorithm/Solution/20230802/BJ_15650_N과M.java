import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_15650_N과M {

	static void recursive(int[] arr, int N, int cnt, int start) {
		if (cnt == arr.length) {
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		for (int i = start; i <= N; i++) {
			arr[cnt] = i;
			recursive(arr, N, cnt + 1, i + 1);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);

		int[] arr = new int[M];

		recursive(arr, N, 0, 1);
	}
}
