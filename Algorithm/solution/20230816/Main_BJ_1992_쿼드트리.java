import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1992_쿼드트리 {
	static int N;
	static int[][] arr;
	static int first;
	
	static boolean isFull(int sc, int sr, int maxC, int maxR) {
		first = arr[sc][sr];
		boolean flag = true;
		for (int i = sc; i < maxC; i++) {
			for (int j = sr; j < maxR; j++) {
				if (arr[i][j] != first) {
					sc = maxC;
					sr = maxR;
					flag = false;
				}
			}
		}
		return flag;
	}
	static void dfs(int sc, int sr, int maxC, int maxR, int n) {
		if (sc >= maxC || sr >= maxR) return;
		if (isFull(sc, sr, maxC, maxR)) {
			System.out.print(first);
			return;
		}
		
		//1234분면
		int half = n/2;
		System.out.print("(");
		dfs(sc, sr, maxC-half, maxR-half, half);
		dfs(sc, sr+half, maxC-half, maxR, half);
		dfs(sc+half, sr, maxC, maxR-half, half);
		dfs(sc+half, sr+half, maxC, maxR, half);
		System.out.print(")");
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		String[] s;
		for (int i = 0; i < N; i++) {
			s = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
			}
		}
		dfs(0,0,N,N,N);
		
	}

}
