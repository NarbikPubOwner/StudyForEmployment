import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA사다리타기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] ladder = new int[100][100];
		for (int T = 0; T < 10; T++) {
			br.readLine();
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = -1;
			for (int j = 0; j < 100; j++) {
				int tempJ = j;
				for (int i = 0; i < 100; i++) {
					if (ladder[i][tempJ] == 0) {
						break;
					}
					int dir = 0;
					if (tempJ-1 > -1 && ladder[i][tempJ-1] == 1) {
						dir = 1;
					}
					else if(tempJ+1 < 100 && ladder[i][tempJ+1] == 1){
						dir = 2;
					}
					while (true) {
						if (dir == 1 && tempJ-1 > -1 && ladder[i][tempJ-1] == 1) {
							tempJ-=1;
						}
						else if(dir == 2 && tempJ+1 < 100 && ladder[i][tempJ+1] == 1){
							tempJ+=1;
						}
						else break;
					}
				}
				if (ladder[99][tempJ] == 2) {
					result = j;
					break;
				}
			}
			
			System.out.println("#"+(T+1)+" "+result);
		}
	}

}
