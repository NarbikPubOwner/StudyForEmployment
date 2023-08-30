import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기1 {
	static int N;
	static int[][] arr;
	static int count = 0;
	static void dfs(int positionX, int positionY , int dir) {
		if (positionX == N-1 && positionY == N-1 ) {
			count++;
			return;
		}
		//0 가로, 1 세로, 2 대각선
		if (positionX+1 <N && positionY+1 < N
				&& arr[positionX+1][positionY+1] != 1 && arr[positionX+1][positionY] != 1 && arr[positionX][positionY+1] !=1) {
			dfs(positionX+1, positionY+1, 2);
		}
		if (dir == 0) {
			if (positionY+1 < N && arr[positionX][positionY+1] !=1) {
				dfs(positionX, positionY+1, 0);
			}
		}
		else if(dir == 1) {
			if (positionX+1 < N && arr[positionX+1][positionY] !=1) {
				dfs(positionX+1, positionY, 1);
			}
		}
		else if(dir == 2) {
			if (positionY+1 < N && arr[positionX][positionY+1] !=1) {
				dfs(positionX, positionY+1, 0);
			}
			if (positionX+1 < N && arr[positionX+1][positionY] !=1) {
				dfs(positionX+1, positionY, 1);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
//				System.out.println(st.nextToken());
				arr[i][j] = stoi(st.nextToken());
			}
		}
		dfs(0,1,0);
		System.out.println(count);
	}
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
