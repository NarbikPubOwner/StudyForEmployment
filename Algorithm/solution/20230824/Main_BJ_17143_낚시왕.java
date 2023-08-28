import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_17143_낚시왕 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int result = 0;
		
		
		List<int[]>[][] arrList = new ArrayList[R][C];
		List<int[]>[][] tempArrList = new ArrayList[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				arrList[i][j] = new ArrayList<>();
				tempArrList[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;//위치 r
			int c = Integer.parseInt(st.nextToken())-1;//위치 c
			int s = Integer.parseInt(st.nextToken());//스피드
			int d = Integer.parseInt(st.nextToken());//위치
			int z = Integer.parseInt(st.nextToken());//크기
			arrList[r][c].add(new int[] {s,d,z});
		}
		
		for (int c = 0; c < C; c++) {
			for (int r = 0; r < R; r++) {
				if (arrList[r][c].size() >= 1) {
					result+=arrList[r][c].get(0)[2];
					arrList[r][c] = new ArrayList<>();
					break;
				}
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					for (int j2 = 0; j2 < arrList[i][j].size(); j2++) {
						int [] arr = arrList[i][j].get(j2);
						int speed = arr[0];
						int dir = arr[1];
						int size = arr[2];
						int targetX = i;
						int targetY = j;
						if (dir == 1) {
							if (i - speed > -1) {
								targetX = i - speed;
							} else {
								int quotient = (speed - i) / (R - 1);
								int rest = (speed - i) % (R - 1);
								if (quotient % 2 != 0) {
									targetX = R - 1 - rest;
									
								} else if (quotient % 2 == 0) {
									targetX = rest;
									dir = 2;
									
								}
							}

						} else if (dir == 2) {// R = 4이고, 위치는 0, 스피드는 8인 경우와 11인 경우를 상정, 8인 경우 r = 2/ d = 2, 11인 경우 r =
												// 1 / d = 1
							if (i + speed < R) {
								targetX = i + speed;
							} else {

								int quotient = (speed + i) / (R - 1);
								int rest = (speed + i) % (R - 1);
								if (quotient % 2 != 0) {
									targetX = R - 1 - rest;
									dir = 1;
								} else if (quotient % 2 == 0) {

									targetX = rest;
								}
							}
						} else if (dir == 3) {
							if (j + speed < C) {
								targetY = j + speed;
							} else {

								int quotient = (speed + j) / (C - 1);
								int rest = (speed + j) % (C - 1);
								if (quotient % 2 != 0) {
									targetY = C - 1 - rest;
									dir = 4;
								} else if (quotient % 2 == 0) {

									targetY = rest;
								}
							}
						} else if (dir == 4) {
							if (j - speed > -1) {
								targetY = j - speed;
							} else {
								int quotient = (speed - j) / (C - 1);
								int rest = (speed - j) % (C - 1);
								if (quotient % 2 != 0) {
									targetY = C - 1 - rest;
									
								} else if (quotient % 2 == 0) {
									targetY = rest;
									dir = 3;
								}
							}
						}
						/*
						 * targetXY 사용으로 변경
						 */
						tempArrList[targetX][targetY].add(new int[] { speed, dir, size });
						// targetXY에 add하는 걸로
					}
					arrList[i][j] = new ArrayList<>();
				}
			}
			//상어 정리 알고리즘
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int[] target = null;
					if (tempArrList[i][j].size() >= 1) {
						target = tempArrList[i][j].get(0);
						for (int k = 0; k < tempArrList[i][j].size()-1; k++) {
							if (target[2] < tempArrList[i][j].get(k+1)[2]) {
								target = tempArrList[i][j].get(k+1);
							}
						}
					}
					if (target != null) {
						arrList[i][j].add(target);
					}
					
					tempArrList[i][j] = new ArrayList<>();
				}
			}
		}
		System.out.println(result);
	}
}

/*
4 6 6
4 5 8 1 4
3 6 2 1 2
2 4 10 2 3
1 1 14 2 5
2 2 9 3 6
3 3 12 4 7

0 5 [2, 1, 2]
1 0 [9, 3, 6]
1 3 [10, 1, 3]
1 4 [8, 1, 4]
2 0 [14, 2, 5]
2 0 [12, 3, 7]
*/
