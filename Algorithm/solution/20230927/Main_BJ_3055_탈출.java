import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 물 - 4방으로 퍼짐 -> 고슴도치, 비버 굴, 돌은 침식 불가 -> 사실상 .만 침식
 * 물의 개수가 정해져 있지 않다 -> 0개, 2개이상 고려해야함
 * 
 * 고슴도치 - 4방중 1택 - 물이 이동 예정인 곳으로 이동하면 안됨 -> 물 먼저 퍼트리기
 * 돌 - 물, 고슴도치 둘 다 이동 불가
 * 
 * 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간 - bfs
 * 
 * 
 */


public class Main_BJ_3055_탈출 {
	static int N;
	static int M;
	static char arr[][];
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		int startBX = 0;
		int startBY = 0;
		
		Queue<int[]> WQ = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			char[] tempCharArr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j] = tempCharArr[j];
				if (arr[i][j] == 'S') {
					startBX = i;
					startBY = j;
				}
				else if(arr[i][j] == '*') {
					WQ.offer(new int[] {i,j});
				}
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> SQ = new ArrayDeque<>();
		
		int swc = 0;
		
		Queue<int[]> WQ2 = new ArrayDeque<>();
		
		SQ.offer(new int[] {startBX, startBY, 0});
		
		int prev = -1;
		
		while (!SQ.isEmpty()) {
			int[] xyz = SQ.poll();
			
			if (xyz[2]-1 ==  prev) {
				prev++;
				//물 bfs1회
				Queue<int[]> targetWq = null;
				Queue<int[]> nextWq = null;
				if (swc == 0) {
					targetWq = WQ;
					nextWq = WQ2;
					swc = 1;
				}
				else {
					targetWq = WQ2;
					nextWq = WQ;
					swc = 0;
				}
				while (!targetWq.isEmpty()) {
					int[] cur = targetWq.poll();
					for (int i = 0; i < 4; i++) {
						int nx = cur[0] + dx[i];
						int ny = cur[1] + dy[i];
						if (nx>-1 && nx<N && ny>-1 && ny<M && arr[nx][ny] == '.') {
							arr[nx][ny] = '*';
							nextWq.offer(new int[] {nx,ny});
						}
					}
				}
			}
			
			//고슴도치 bfs
			for (int i = 0; i < 4; i++) {
				int nx = xyz[0] + dx[i];
				int ny = xyz[1] + dy[i];
				if (nx>-1 && nx<N && ny>-1 && ny<M && !visited[nx][ny]) {
					if (arr[nx][ny] == '.') {
						visited[nx][ny] = true;
						SQ.offer(new int[] {nx,ny,xyz[2]+1});
					}
					else if(arr[nx][ny] == 'D') {
						System.out.println(xyz[2]+1);
						System.exit(0);
					}
				}
			}
		}
		System.out.println("KAKTUS");
		
	}
}
