import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 물이 퍼진 곳에는 고슴도치가 갈 수 없다 -> 물을 먼저 퍼트리고 고슴도치의 bfs를 진행하면 된다
 * 물이 1번 퍼지면 고슴도치가 1번 bfs를 진행할 수 있다 -> bfs 큐에 들어가는 순서는 길이 순서대로 들어가기 때문에(bfs의 특징) bfs의 원소 저장에 움직인 횟수를 추가하고, 그 움직인 횟수가 이전 기준값과 달라진다면 물이 이동해야 하는 시점이라고 판단 할 수 있다
 * 물 또한 1번만 퍼트려야 하는데, 하나의 큐만 사용하면 구현하기 힘들기 때문에 2개의 큐를 사용하여 bfs의 진행과 저장을 분리해서 구현한다
 * 1번 큐의 bfs를 진행하고 거기서 얻어진 다음 진행정보는 2번 큐에 저장한다 -> 이후엔 2번 큐의 bfs를 진행하고 이번엔 1번큐에 저장한다 -> 반복
 * 
 * 주의사항 : 문제에 표기는 되어있지 않지만 물은 시작부터 없을수도 있고, 또 여러개가 있을 수도 있다. 때문에 모든 물의 위치를 시작할 때 큐에 넣어야 한다.
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
		
		int prev = -1;//현재 진행중인 길이 
		int swc = 0;//물 스위치
		Queue<int[]> WQ = new ArrayDeque<>();//물 1번큐
		Queue<int[]> WQ2 = new ArrayDeque<>();//물 2번큐
		
		boolean[][] visited = new boolean[N][M];//고슴도치 방문저장배열
		Queue<int[]> SQ = new ArrayDeque<>();//고슴도치 큐
		
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
		
		SQ.offer(new int[] {startBX, startBY, 0});
	
		
		while (!SQ.isEmpty()) {
			int[] xyz = SQ.poll();
			
			//물 bfs
			if (xyz[2]-1 ==  prev) {//고슴도치의 진행 길이가 달라졌을 경우 물의 bfs를 진행한다
				prev++;
				//물 큐 스위칭
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
						System.out.println(xyz[2]+1);//마지막 지점에 도달하면 정답 출력후 함수를 종료
						System.exit(0);
					}
				}
			}
		}
		System.out.println("KAKTUS");//마지막 지점에 도착하지 못한 경우의 출력
	}
}
