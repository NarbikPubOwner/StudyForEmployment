import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2961_도영이가만든맛있는음식 {
	static int N;
	static int[][] cookIngredient;

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cookIngredient = new int[N][2];
		
		String[] input = null;
		for (int i = 0; i < cookIngredient.length; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < cookIngredient[i].length; j++) {
				cookIngredient[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int result = 0;
		for (int i = 0; i < (1<<N); i++) {
			int sour = 0;
			int bitter = 0;
			for (int j = 0; j < N; j++) {
				if ((i & 1<<j) != 0) {
					if (sour == 0) {
						sour = cookIngredient[j][0];
					}else sour *= cookIngredient[j][0];
					bitter += cookIngredient[j][1];
				}
			}
			if (i == 0) {
				result = Math.abs(cookIngredient[i][0]-cookIngredient[i][1]);
			}
			else if (result > Math.abs(sour-bitter)) {
				result = Math.abs(sour-bitter);
			}
		}
		
		System.out.println(result);
	}
}
