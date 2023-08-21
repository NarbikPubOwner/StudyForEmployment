import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 방문했던 곳을 기록해 4분탐색 횟수를 줄이는 것이 핵심
 */
public class Main_BJ_10026_적록색약 {
	static int[] dx = new int[] { -1, 0, 1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };

	/*
	 * 배열을 넣으면 해당 배열의 영역을 구분해준다
	 */
	static int getCount(char arr[][], int N) {
		int cnt = 0;
		for (int n = 0; n < arr.length; n++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[n][j] != 'X') {
					/*
					 * 진입전 초기화
					 */
					Queue<int[]> queue = new ArrayDeque<int[]>();//bfs로 참조할 다음 좌표를 저장할 큐
					queue.offer(new int[] { n, j });
					char target = arr[n][j];
					arr[n][j] = 'X';
					while (!queue.isEmpty()) {
						int[] curXY = queue.poll();
						for (int i = 0; i < 4; i++) {
							int tempX = curXY[0] + dx[i];
							int tempY = curXY[1] + dy[i];
							if (tempX < N && tempX > -1 && tempY < N && tempY > -1 && arr[tempX][tempY] == target) {
								queue.offer(new int[] { tempX, tempY });
								arr[tempX][tempY] = 'X';//이미 지난 곳을 기억하는 방법으로 배열의 값을 X로 수정하는 방법을 선택
							}
						}
					}
					//while이 한 번 끝나면 영역 1개를 생성한 것임
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] arr = new char[N][N];
		char[][] arr2 = new char[N][N];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		//적록색약을 위한 배열 생성
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 'R') arr2[i][j] = 'G';
				else arr2[i][j] = arr[i][j];
			}
		}
		
		
		System.out.println(getCount(arr, N) + " " + getCount(arr2, N));
	}

}
