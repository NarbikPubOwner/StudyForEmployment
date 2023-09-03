import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs탐색 한다
 * 점프 카운트를 따로 기억한다
 * 
 * 핵심 : 말로써 뛴 후 1칸씩 가는 것과 1칸씩 진행 후 말로써 뛰는 것은 서로 다르게 봐야 한다. 
 * 예를 들자면, 총 2회의 점프로 3,4에 도달한 경우와 총 1회의 점프로 3,4에 도달하려는 경우를 고려하자면
 * 만약 visited를 3차원배열로 하지 않는다면 2회의 점프로 3,4에 도달한 경우 때문에 1회의 점프로 3,4에 도달하는 경우의 수가 사라져 버린다
 * 도착횟수는 둘째치고 경우 자체가 사라져버리기 때문에 도착 가능성 자체가 달라질 수 있다
 * 즉 몇번 점프했냐에 따라 서로 완전히 다른 것으로 간주해야 하기 때문에, 점프 횟수에 따른 visited배열의 운용이 필요해진다
 */
public class Main_BJ_1600_말이되고픈원숭이 {

	static int[][] arr;
	static int W;
	static int H;
	static int[] knightDx = new int[] {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] knightDy = new int[] {-2, -1,  1,  2, 2, 1, -1, -2};
	static int[] dx = new int[] {-1,0,1,0};
	static int[] dy = new int[] {0,1,0,-1};
	static int[][][] visited;
	static int maxJumpCnt;
	static int result = -1;
	
	static void bfs() {

		//i,j,moveCount,jumpCnt
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,0,0,0});
		visited[0][0][0]++;
		
		while (!queue.isEmpty()) {
			int[] curArr = queue.poll();
			if (curArr[0] == H-1 && curArr[1] == W-1) {
				result = curArr[2];
				break;
			}
			int nx = 0;
			int ny = 0;
			for (int i = 0; i < 4; i++) {
				nx = curArr[0]+dx[i];
				ny = curArr[1]+dy[i];
				if (nx<0 || nx>=H || ny<0 || ny>=W || visited[nx][ny][curArr[3]] != 0 || arr[nx][ny] == 1) continue;
				visited[nx][ny][curArr[3]]++;
				queue.offer(new int[] {nx,ny,curArr[2]+1, curArr[3]});
			}
			if (curArr[3] < maxJumpCnt) {
				for (int i = 0; i < 8; i++) {
					nx = curArr[0]+knightDx[i];
					ny = curArr[1]+knightDy[i];
					if (nx<0 || nx>=H || ny<0 || ny>=W || visited[nx][ny][curArr[3]+1] != 0 || arr[nx][ny] == 1 ) continue;
					visited[nx][ny][curArr[3]+1]++;
					queue.offer(new int[] {nx,ny,curArr[2]+1, curArr[3]+1});
				}
			}
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		maxJumpCnt = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		W = stoi(st.nextToken());
		H = stoi(st.nextToken());
		arr = new int[H][W];
		visited = new int [H][W][maxJumpCnt+1];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < W; j++) {
				arr[i][j] = stoi(st.nextToken());
			}
		}
		
		bfs();
		System.out.println(result);
		
	}

	static int stoi(String s) {
		return Integer.parseInt(s); 
	}
}
