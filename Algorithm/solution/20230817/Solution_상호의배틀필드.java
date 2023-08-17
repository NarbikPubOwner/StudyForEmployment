import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Solution_상호의배틀필드 {

	static int tankX;
	static int tankY;
	static char[][] arr;
	static char tankDir;
	static int H;
	static int W;
	
	static void movement(char command) {
		// 상하좌우 움직임
		if (command == 'U' || command == 'D' || command == 'R' || command == 'L') {
			int tempX = tankX;
			int tempY = tankY;
			char tempTankDir = ' ';
			if (command == 'U') {
				tempX -= 1;
				tempTankDir = '^';
			}
			else if (command == 'D') {
				tempX += 1;
				tempTankDir = 'v';
			}
			else if (command == 'R') {
				tempY += 1;
				tempTankDir = '>';
			}
			else if (command == 'L') {
				tempY -= 1;
				tempTankDir = '<';
			}
			if (tempX > -1 && tempY > -1 && tempX < H && tempY < W) {
				switch (arr[tempX][tempY]) {
				case '.':
					arr[tempX][tempY] = tempTankDir;
					arr[tankX][tankY] = '.';
					tankX = tempX;
					tankY = tempY;
					tankDir = tempTankDir;
					break;
				default:
					arr[tankX][tankY] = tempTankDir;
					tankDir = tempTankDir;
					break;
				}
			}
			else {
				arr[tankX][tankY] = tempTankDir;
				tankDir = tempTankDir;
			}
			
		}
		//슈팅 동작
		else {
			int shootingX = tankX;
			int shootingY = tankY;
			switch (tankDir) {
			case '^':
				while ((shootingX-1) > -1) {
					shootingX -= 1;
					if (arr[shootingX][shootingY] == '*') {
						//벽을 부수고 평지로 바꿈
						arr[shootingX][shootingY] = '.';
						break;
					}
					else if(arr[shootingX][shootingY] == '#') break;
				}
				break;
			case 'v':
				while ((shootingX+1) < H) {
					shootingX += 1;
					if (arr[shootingX][shootingY] == '*') {
						//벽을 부수고 평지로 바꿈
						arr[shootingX][shootingY] = '.';
						break;
					}
					else if(arr[shootingX][shootingY] == '#') break;
				}
				break;
			case '<':
				while ((shootingY-1) > -1) {
					shootingY -= 1;
					if (arr[shootingX][shootingY] == '*') {
						//벽을 부수고 평지로 바꿈
						arr[shootingX][shootingY] = '.';
						break;
					}
					else if(arr[shootingX][shootingY] == '#') break;
				}
				break;
			case '>':
				while ((shootingY+1) < W) {
					shootingY += 1;
					if (arr[shootingX][shootingY] == '*') {
						//벽을 부수고 평지로 바꿈
						arr[shootingX][shootingY] = '.';
						break;
					}
					else if(arr[shootingX][shootingY] == '#') break;
				}
				break;
				
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			arr = new char[H][W];

			for (int h = 0; h < H; h++) {
				arr[h] = br.readLine().toCharArray();
			}

			int N = Integer.parseInt(br.readLine());
			char[] commandArr = br.readLine().toCharArray();

			// 초기 탱크 위치 및 방향 검색
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					if (arr[h][w] == '^' || arr[h][w] == 'v' || arr[h][w] == '<' || arr[h][w] == '>') {
						tankX = h;
						tankY = w;
						tankDir = arr[h][w];
						break;
					}
				}
			}

			for (int n = 0; n < N; n++) {
				movement(commandArr[n]);
			}

			sb.append("#").append(t).append(" ");
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					sb.append(arr[h][w]);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
/*
1
4 6
*.*..*
*.....
..-...
^.*#..
10
SRSSRRUSSR
*/
