import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *기본적으로 bfs로 탐색하는 문제
 *하지만 키를 수집 할 때마다 모든 경로를 다시 가봐야 한다 -> 키를 먹을 때마다 새로운 visited배열을 사용해야한다
 *방법1. 키를 수집했는가 아닌가를 인덱스로 가지는 배열로 만든다(총 8차원 배열)
 *방법2. 키를 수집했는가 아닌가, 2가지 경우의 수를 나타내는 것이기 때문에 비트마스킹으로 표현이 가능하다(비트마스킹으로 할 경우 원소의 개수가 32개를 넘지 않아야 한다, 해당 문제에서는 6개의 원소)
 * 
 */


public class Main_BJ_1194_달이차오른다가자 {
	//현재 위치, 이동 횟수, 키 획득 여부를 저장하는 객체
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
	
	//내가 맞닥뜨린 지점이 열쇠나 문에 해당하는지 쉽게 확인하기 위한 해쉬맵
	static HashMap<Character, Integer> keyList = new HashMap<>();
	static HashMap<Character, Integer> doorList = new HashMap<>();
	
	static int result = Integer.MAX_VALUE;
	
	static void bfs(int N, int M, char[][] maze) {
		Queue<pos> q = new ArrayDeque<>();//다음 진행지점을 저장할 큐
		boolean[][][][][][][][] visited = new boolean[2][2][2][2][2][2][N][M];//열쇠획득 여부에 따라 visited배열을 달리하기 위한 8차원 배열 구성

		/*
		 * 이후는 기본적인 bfs를 구현한다
		 */
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
				/*
				 * 벽을 만나거나 키가 없는채로 문을 만난 경우, 더 이어갈 가치가 없기 때문에 continue로 넘긴다
				 */
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
