import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_16435_스네이크버그 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		
		int[] fruit = new int[N];
		for (int i = 0; i < fruit.length; i++) {
			fruit[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(fruit);
		
//		System.out.println(Arrays.toString(fruit));
		
		boolean go = false;
		for (int i = 0; i < fruit.length; i++) {
			if ( (fruit[i] - L) <= 0) go = true;
			else go = false;
			
			if (go) {
				L++;
			}
			
		}
		
		System.out.println(L);
		
	}

}
