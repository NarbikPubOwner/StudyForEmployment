import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_7576_토마토 {

	/*
	 * 4방 기준 flood fill 문제라고 할 수 있다
	 * bfs를 활용한다
	 * 좀 더 간단하게 하기 위해 일차 시간별 정보를 같이 저장한다
	 * 
	 * 로직
	 * 1인 지점들을 찾아 큐에 넣는다(이미 익은 토마토)
	 * 
	 * 이후 큐가 비워질때까지 아래를 반복한다
	 * 	큐에서 빼낸다 -> 이미 방문한 곳으로 표시한다(한 번 영향을 준 토마토는 다시는 영향을 주지 못하기 때문에) + 해당 깊이를 외곽 cnt에 저장한다
	 * -> 해당 지점의 4방 지점(0인)을 큐에 넣는다(큐에 넣으면서 해당 지점을 1로 만든다)
	 * 
	 * 큐에서 빠져나온 후 다시 한번 토마토 배열을 돌면서, 0인 지점이 있으면 그 곳은 익히는 것이 불가능 한 것이다 -> -1 출력
	 * 아니라면 cnt 출력
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		//토마토 저장 배열
		int[][] arr = new int[N][M];
		/*
		 * 4방 탐색을 편하게 하기 위한 델타배열
		 */
		int[] dx = new int[] { -1, 0, 1, 0 };
		int[] dy = new int[] { 0, 1, 0, -1 };

		//bfs에 사용될 큐 선언
		Queue<int[]> queue = new ArrayDeque<>();
		//토마토 초기 배열 생성
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					queue.offer(new int[] { i, j, 0 });//i,j,깊이
				}
			}
		}

		int cnt = 0;
		
		//큐가 비어있지 않다 -> 아직 익힐 수 있는 토마토가 있다
		while (!queue.isEmpty()) {
			int[] curXYD = queue.poll();
				cnt = curXYD[2];
				
				for (int i = 0; i < 4; i++) {
					int tempX = curXYD[0] + dx[i];
					int tempY = curXYD[1] + dy[i];
					/*
					 * 1. 인덱스를 벗어나는 경우
					 * 2. 이미 방문한 곳(1)
					 * 3. 방문할 수 없는 곳(-1)
					 * 은 처리하지 않는다. 일반적인 dfs의 visited는 arr의 1으로 대체됨
					 */
					if (tempX > -1 && tempX < N && tempY > -1 && tempY < M && arr[tempX][tempY] == 0) {
						queue.add(new int[] { tempX, tempY, curXYD[2] + 1 });//다음에 방문할 곳들 저장
						arr[tempX][tempY] = 1;
					}
				}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 0) {
					i = arr.length;
					j = arr[0].length;
					
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(cnt);

	}

}
