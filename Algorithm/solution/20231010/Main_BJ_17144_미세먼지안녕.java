import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 먼지 확산 : 4방 퍼트리기 구현
 * 공기청정기 밀어내기 : 배열회전 구현
 */
public class Main_BJ_17144_미세먼지안녕 {

	static int C;
	static int R;
	static int time;
	
	static int[][] arr;
	static List<int[]> ac;
	
	static int[] dx = new int[] {-1,0,1,0};
	static int[] dy = new int[] {0,1,0,-1};
	
	static int[] dx2 = new int[] {0,-1,0,1};
	static int[] dy2 = new int[] {1,0,-1,0};
	
	static void dustDiffusion() {
		int[][] copyArr = new int [C][R];
		
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				int value = arr[i][j];
				int spreadValue = value/5;
				if (value == 0 || value == -1) continue;
				for (int k = 0; k < 4; k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					if (nx > -1 && nx < C && ny > -1 && ny < R && arr[nx][ny] != -1) {
						copyArr[nx][ny] += spreadValue;
						value-=spreadValue;
					}
				}
				copyArr[i][j] += value;
			}
		}
		for (int[] is : ac) {
			copyArr[is[0]][is[1]] = -1;
		}
		arr = copyArr;
	}
	
	static void runAC() {
		for (int i = 0; i < 2; i++) {
			int[] is = ac.get(i);
			Queue<Integer> q = new ArrayDeque<Integer>();
			int nx = is[0]+dx2[0];
			int ny = is[1]+dy2[0];
			q.offer(arr[nx][ny]);
			arr[nx][ny] = 0;
			for (int k = 0; k < 4; k++) {
				nx += dx2[k];
				ny += dy2[k];
				while (nx > -1 && nx < C && ny > -1 && ny < R && arr[nx][ny] != -1) {
					q.offer(arr[nx][ny]);
					arr[nx][ny] = q.poll();	
					
					nx += dx2[k];
					ny += dy2[k];
				}
				nx -= dx2[k];
				ny -= dy2[k];
			}
			dx2 = new int[] {0,1,0,-1};
		}
		dx2 = new int[] {0,-1,0,1};
	}
	
	static int getDustVolume() {
		int sum = 0;
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				if (arr[i][j] == -1) continue;
				sum += arr[i][j];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		ac = new ArrayList<>();
		arr = new int [C][R];
		
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < R; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {
					ac.add(new int[] {i,j});
				}
			}
		}
		
		for (int i = 0; i < time; i++) {
			dustDiffusion();
			runAC();
		}
		
		System.out.println(getDustVolume());
	}
}
