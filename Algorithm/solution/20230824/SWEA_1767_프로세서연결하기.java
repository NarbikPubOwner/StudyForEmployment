import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 4방탐색 dfs로 처리되지 않을까 싶은데
 * 1,2를 만나면 탐색 중단
 * 
 * dfs로 넘길게, 처리된 코어 갯수
 */
public class SWEA_1767_프로세서연결하기 {
	static int T, N;
	static int[][] arr;
	static int[] dx = new int[] { -1, 0, 1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };
	static List<int[]> list;
	static boolean[] isVisited;
	static int min;
	static int maxCore;
	
	static void dfs(int cnt, int connectedCore, int start) {
		if (cnt == list.size()) {
			//코어 갯수가 많은 경우가 생기면 무조건 해당 경우의 전선 수를 min으로 만든다
			if (maxCore < connectedCore) { 
				int line = 0;
				maxCore = connectedCore;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (arr[i][j] == 2) {
							line++;
						}
					}
					
				}
				min = line;
			}
			//코어 갯수가 같다면 전선 개수가 더 적은 걸 선택
			else if( maxCore == connectedCore ) {
				int line = 0;
				maxCore = connectedCore;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (arr[i][j] == 2) {
							line++;
						}
					}
					
				}
				min = Math.min(min, line);
			}
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			// 4방 탐색을 하는데, 탐색 시 해당 방향 쭉 밀어서 1,2가 없으면 해당방향으로 다 2로 수정하고 dfs 들어감, 해당 dfs나온 후 0으로 재변경
			int idx = 0;
			int startX = list.get(i)[0];
			int startY = list.get(i)[1];
			while (idx != 4) {//4방탐색
				int tempX = startX + dx[idx];
				int tempY = startY + dy[idx];
				boolean canGo = true;
				while (tempX > -1 && tempX < N && tempY > -1 && tempY < N) {
					if (arr[tempX][tempY] == 1 || arr[tempX][tempY] == 2) {//1 or 2를 만나면 진행 자체가 불가하도록 설정
						canGo = false;
						break;
					}
					tempX += dx[idx];
					tempY += dy[idx];
				}
				
				if (canGo) {
					if (tempX == -1 || tempX == N || tempY == -1 || tempY == N) {
						int tempX2 = startX + dx[idx];
						int tempY2 = startY + dy[idx];
						while ( !(tempX2 == tempX && tempY2 == tempY) ) {
							arr[tempX2][tempY2] = 2;
							tempX2 += dx[idx];
							tempY2 += dy[idx];
						}
						dfs(cnt + 1, connectedCore + 1, i+1);
						
						tempX2 = startX + dx[idx];
						tempY2 = startY + dy[idx];
						while ( !(tempX2 == tempX && tempY2 == tempY) ) {
							arr[tempX2][tempY2] = 0;
							tempX2 += dx[idx];
							tempY2 += dy[idx];
						}
					}
				}
				
				idx++;
			}
			dfs(cnt + 1, connectedCore, i+1);//해당 코어에서 전선을 뻗치지 않는 경우
		}
	}

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			list = new ArrayList<>();
			int connectedCore = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1) {
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							connectedCore++;
						} else {
							list.add(new int[] { i, j });
						}
							
					}
				}
			}
			isVisited = new boolean[list.size()];
			min = Integer.MAX_VALUE;
			maxCore = 0;
			dfs(0, connectedCore,0);
			
			sb.append("#").append(t+1).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
		

	}

}
