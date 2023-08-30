import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 동전자판기_dp {
	

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int money = Integer.parseInt(br.readLine());
		int[] C = new int[money+1];
		
		C[0] = 0;
		C[1] = 1;
		
		for (int i = 1; i <= money; i++) {
		
			C[i] = C[i-1]+1;
			if (i>=4 && C[i] > C[i-4]+1) C[i] = C[i-4]+1;
			if (i>=6 && C[i] > C[i-6]+1) C[i] = C[i-6]+1;
			
//			if (i - 6 >= 0) {
//				C[i] = Math.min(C[i-1], Math.min(C[i-4], C[i-6]))+1;
//			}
//			else if (i - 4 >= 0) {
//				C[i] = Math.min(C[i-1], C[i-4])+1;
//			}
//			else C[i] = C[i-1]+1;
		}
		
		System.out.println(C[money]);
	}
}
