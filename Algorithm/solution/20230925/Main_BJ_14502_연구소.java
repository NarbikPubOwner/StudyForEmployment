import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * dfs로 벽이 세워질 수 있는 경우를 구합니다
 * bfs로 2를 채우고 0을 세면 됩니다
 * bfs로 2를 채울 때 채우기 전 2의 개수만큼 반복하면 됩니다(미리 해당 2에 대한 인덱스를 큐에 저장합니다)
 */
public class Main {
	
	
	static int N;//배열의 세로크기
	static int M;//베열의 가로크기
	static int[][] arr;//원본 배열
	
	//4분탐색을 위한 배열
	static int dx[] = new int[] {-1,0,1,0};
	static int dy[] = new int[] {0,1,0,-1};
	
	static int result = 0;
	
	//바이러스를 퍼트리는 함수
	static int bfs() {
		int[][] tempArr = new int [N][M];//바이러스가 퍼진 경우를 기록할 배열
		
		/*
		 * 초기 바이러스들의 위치를 저장
		 */
		Queue<int[]> virusList = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempArr[i][j] = arr[i][j];
				if (arr[i][j] == 2) {
					virusList.offer(new int[] {i,j});
				}
			}
		}
		
		/*
		 * 재방문 하지 않아야 하기 때문에 visted를 활용한 bfs
		 */
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		while (!virusList.isEmpty()) {//초기 바이러스만큼 bfs를 반복한다
			int[] xy = virusList.poll();
			q.offer(xy);
			visited[xy[0]][xy[1]] = true;
			while (!q.isEmpty()) {
				xy = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = xy[0] + dx[i];
					int ny = xy[1] + dy[i];
					if (nx > -1 && nx < N && ny > -1 && ny < M && (tempArr[nx][ny] == 0 || tempArr[nx][ny] == 2) && !visited[nx][ny]) {
						q.offer(new int[] {nx,ny});
						tempArr[nx][ny] = 2;
						visited[nx][ny] = true;
					}
				}
			}
		}
		
	
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tempArr[i][j] == 0) {
					sum++;
				}
			}
		}
		
		return sum;
	}
	
	static void dfs(int cnt) {
		if (cnt == 3) {//벽을 3개 세웠다면
			result = Math.max(result, bfs());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					dfs(cnt+1);
					arr[i][j] = 0;
				} 
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(result);
	}
}
