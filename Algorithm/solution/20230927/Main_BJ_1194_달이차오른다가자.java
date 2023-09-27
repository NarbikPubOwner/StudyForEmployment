import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs시 방문처리를 키가 달라질떄마다 새로 생성해야한다
 * 굳이 마스킹으로 해야하나? 상관없지 않나 -> 시간 복잡도가 증가할듯? 아닌가? -> 아니네
 * 
 */


public class Main_BJ_1194_달이차오른다가자 {
	
	static class pos {
		int x;
		int y;
		int len;
		int a;
		int b;
		int c;
		int d;
		int e;
		int f;
		public pos(int x, int y, int len, int a, int b, int c, int d, int e, int f) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.e = e;
			this.f = f;
		}
		
	}
	
	static int startX;
	static int startY;
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static HashMap<Character, Integer> keyList = new HashMap<>();
	static HashMap<Character, Integer> doorList = new HashMap<>();
	
	static int result = Integer.MAX_VALUE;
	
	static void bfs(int N, int M, char[][] maze) {
		Queue<pos> q = new ArrayDeque<>();
		boolean[][][][][][][][] visited = new boolean[2][2][2][2][2][2][N][M];

		q.offer(new pos(startX, startY, 0, 0, 0, 0, 0, 0, 0));
		visited[0][0][0][0][0][0][startX][startY] = true;

		while (!q.isEmpty()) {
			pos tempPos = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = tempPos.x + dx[i];
				int ny = tempPos.y + dy[i];
				int len = tempPos.len;
				int a = tempPos.a;
				int b = tempPos.b;
				int c = tempPos.c;
				int d = tempPos.d;
				int e = tempPos.e;
				int f = tempPos.f;
				if (nx < 0 || nx == N || ny < 0 || ny == M || 
						visited[a][b][c][d][e][f][nx][ny])
					continue;

				char mv = maze[nx][ny];// mazeValue
				
				if (mv == '.') {
				} else if (mv == '#') {
					continue;
				} else if (keyList.containsKey(mv)) {
					//mv를 가지고 있다면
					if (mv == 'a') {
						a = 1;
					}
					else if (mv == 'b'){
						b = 1;
					}
					else if (mv == 'c'){
						c = 1;
					}
					else if (mv == 'd'){
						d = 1;
					}
					else if (mv == 'e'){
						e = 1;
					}
					else if (mv == 'f'){
						f = 1;
					}
				} else if (doorList.containsKey(mv)) {
					if ((mv + 32)  == 'a' && a == 1) {}
					else if ((mv + 32)  == 'b' && b == 1) {}
					else if ((mv + 32)  == 'c' && c == 1) {}
					else if ((mv + 32)  == 'd' && d == 1) {}
					else if ((mv + 32)  == 'e' && e == 1) {}
					else if ((mv + 32)  == 'f' && f == 1) {}
					else continue;
				} else if (mv == '1') {
					result = Math.min(tempPos.len+1,result);
				}
//				System.out.println(mv);
				visited[a][b][c][d][e][f][nx][ny] = true;
				q.offer(new pos(nx, ny, len+1, a, b, c, d, e, f));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 6; i++) {
			keyList.put((char) ('a'+i), 0);
			doorList.put((char) ('A'+i), 0);
		}
		
		char[][] maze = new char[N][M];
		for (int i = 0; i < N; i++) {
			char[] tempChar = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				maze[i][j] = tempChar[j];
				if (maze[i][j] == '0') {
					startX = i;
					startY = j;
					maze[i][j] = '.';
				}
			}
		}
		
		bfs(N,M,maze);
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(result);
		}
		
	}
}
