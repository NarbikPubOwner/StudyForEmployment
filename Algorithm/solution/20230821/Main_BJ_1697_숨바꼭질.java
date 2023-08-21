import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1697_숨바꼭질{
	
	static int N;
	static int K;
	
	static boolean valid(int n, boolean[] isVisited) {
		if (n>=0 && n<=100000 && !isVisited[n]) {
			return true;
		}
		return false;
	}
	
	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[100001];
		queue.offer(new int[] {N,0});
		isVisited[N] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if (cur[0] == K) {
				System.out.println(cur[1]);
				return;
			}
			
			if (valid(cur[0]+1, isVisited)) {
				queue.add(new int[] {cur[0]+1,cur[1]+1});
				isVisited[cur[0]+1] = true;
			}
			if (valid(cur[0]-1, isVisited)) {
				queue.add(new int[] {cur[0]-1,cur[1]+1});
				isVisited[cur[0]-1] = true;
			}
			if (valid(cur[0]*2, isVisited)) {
				queue.add(new int[] {cur[0]*2,cur[1]+1});
				isVisited[cur[0]*2] = true;
			}
		}
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		bfs();
		
	}

}
