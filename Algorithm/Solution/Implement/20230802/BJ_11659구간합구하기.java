import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_11659구간합구하기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int[] arr = new int[N+1];
		input = br.readLine().split(" ");
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(input[i-1]);
		}
		
		//숫자 N개에 대한 누적합 구하기
		for (int i = 1; i < arr.length; i++) {
			arr[i] = arr[i] + arr[i-1];
		}
		
		int I=0;
		int J=0;
		for (int k = 0; k < M; k++) {
			int sum = 0;
			input = br.readLine().split(" ");
			I = Integer.parseInt(input[0]);
			J = Integer.parseInt(input[1]);
			
			System.out.println(arr[J] - arr[I-1]);
		}	
	}
}


//5 3
//5 4 3 2 1
//1 3
//2 4
//1 5

//5 3
//5 4 3 2 1
//1 3
//2 4
//5 5