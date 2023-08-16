import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_BJ_6987_월드컵 {
	static int [] team1 = new int[] {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int [] team2 = new int[] {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	static int [][] match = new int[6][3];
	static int [][] result = new int[6][3];
	static int [] answer = new int[4];
	
	static void recursive(int round, int t) {
		if (answer[t] == 1) {
			return;
		}
		if (round == 15) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (match[i][j] != result[i][j]) {
						return;
					}
				}
			}
			answer[t] = 1;
			return;
		}
		
		
		//승
		result[team1[round]][0]++;
		result[team2[round]][2]++;
		recursive(round + 1, t);
		result[team1[round]][0]--;
		result[team2[round]][2]--;
		//무
		result[team1[round]][1]++;
		result[team2[round]][1]++;
		recursive(round + 1, t);
		result[team1[round]][1]--;
		result[team2[round]][1]--;
		//패
		result[team1[round]][2]++;
		result[team2[round]][0]++;
		recursive(round + 1, t);
		result[team1[round]][2]--;
		result[team2[round]][0]--;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 0; t < 4; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					match[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			recursive(0,t);
		}
		for (int i = 0; i < 4; i++) {
			System.out.print(answer[i] + " ");
		}
	}

}
