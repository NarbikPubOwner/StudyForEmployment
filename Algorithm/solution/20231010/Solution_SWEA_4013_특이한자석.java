import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_SWEA_4013_특이한자석 {
	
	static void rotate(int[][] magnetArr, int magnetNum,int rotationDir, boolean[] isRotated, boolean[] canRotated) {
		if (magnetNum < 0 || magnetNum >= 4  || isRotated[magnetNum] || !canRotated[magnetNum]) return;
		int[] magnet = magnetArr[magnetNum];
		if (rotationDir == 1) {
			int temp = magnet[7];
			for (int i = 7; i > 0; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[0] = temp;
		}
		
		else {
			int temp = magnet[0];
			for (int i = 1; i < 8; i++) {
				magnet[i-1] = magnet[i];
			}
			magnet[7] = temp;
		}
		isRotated[magnetNum] = true;
		rotate(magnetArr, magnetNum+1, rotationDir*-1, isRotated, canRotated);
		rotate(magnetArr, magnetNum-1, rotationDir*-1, isRotated, canRotated);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine());//회전 횟수
			int[][] magnetArr = new int[4][8];
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int[] magnet = new int[8];
				for (int j = 0; j < 8; j++) {
					magnet[j] = (Integer.parseInt(st.nextToken()));
				}
				magnetArr[i] = magnet;
			}
			
			for (int i = 0; i < K; i++) {
				boolean[] isRotated = new boolean[4];
				boolean[] canRotated = new boolean[4];
				
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int magnetNum = Integer.parseInt(st.nextToken())-1;
				int rotationDir = Integer.parseInt(st.nextToken());
				
				canRotated[magnetNum] = true;
				int origin = magnetNum;
				int right = magnetNum + 1;
				while (right < 4) {
					if (magnetArr[origin][2] != magnetArr[right][6]) {
						canRotated[right] = true;
						origin = right;
						right += 1;
					}
					else break;
				}
				origin = magnetNum;
				int left = magnetNum -1;
				while (left > -1) {
					if (magnetArr[origin][6] != magnetArr[left][2]) {
						canRotated[left] = true;
						origin = left;
						left -= 1;
					}
					else break;
				}
				rotate(magnetArr, magnetNum, rotationDir, isRotated, canRotated);
			}
			int result = 0;
			for (int i = 0; i < 4; i++) {
				if (magnetArr[i][0] == 1) {
					result += 1 << i;
				}
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
