import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다. -> dfs

public class Main_BJ_1987_알파벳 {

	static int R;
	static int C;
	static char[][] arr;
	
	static boolean[] isVisited = new boolean[26];//방문한 알파벳 기록
	static int result = 0;
	static int[] dx = new int[] {-1,0,1,0};
	static int[] dy = new int[] {0,1,0,-1};
	static void dfs(int cnt, int x, int y) {
		//진행 방향이 막혀있거나 이미 방문한 알파벳이면 중지
		if (x >= R || x < 0 || y >= C || y < 0|| isVisited[(int)arr[x][y]-65] ) {
			if (cnt > result) {
				result = cnt;
			}
			return;
		}
		isVisited[(int)arr[x][y]-65] = true;
		for (int i = 0; i < 4; i++) {
			dfs(cnt+1, x+dx[i], y+dy[i]);//상하좌우 재귀
		}
		isVisited[(int)arr[x][y]-65] = false;//되돌아가면 방문 기록을 되돌림
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		dfs(0,0,0);
		System.out.println(result);
	}

}
