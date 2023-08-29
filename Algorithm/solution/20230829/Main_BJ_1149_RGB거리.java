import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1149_RGB거리 {
	
	static int N;
	static int[][] color;
	static int result = Integer.MAX_VALUE;
	static int[] output;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		color = new int[N][3];
		output = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			color[i][0] = Integer.parseInt(st.nextToken());
			color[i][1] = Integer.parseInt(st.nextToken());
			color[i][2] = Integer.parseInt(st.nextToken());
		}
	
		
		int[][] tempColor = new int[N][3];
		tempColor[0][0] = color[0][0];
		tempColor[0][1] = color[0][1];
		tempColor[0][2] = color[0][2];
		
		for (int i = 1; i < N; i++) {
			tempColor[i][0] = Math.min(tempColor[i-1][1], tempColor[i-1][2]) + color[i][0];
			tempColor[i][1] = Math.min(tempColor[i-1][0], tempColor[i-1][2]) + color[i][1];
			tempColor[i][2] = Math.min(tempColor[i-1][0], tempColor[i-1][1]) + color[i][2];
		}
		
		System.out.println(Math.min(tempColor[N-1][0], Math.min(tempColor[N-1][1], tempColor[N-1][2])));
		
	}
}
