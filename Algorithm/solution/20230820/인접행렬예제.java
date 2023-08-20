import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 인접행렬예제 {

	static void bfs(int[][] adjMatrix, int start, int V) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[V];
		queue.offer(start);
		isVisited[start] = true;

		while (!queue.isEmpty()) {
			int currrent = queue.poll();
			System.out.println(currrent);
			for (int i = 0; i < V; i++) {
				if (adjMatrix[currrent][i] != 0) {
					if (!isVisited[i]) {
						queue.add(i);
						isVisited[i] = true;
					}
				}
			}
		}
	}
	
	static void dfs(int[][] adjMatrix, int start, int V) {
		Stack<Integer> stack = new Stack<>();
		boolean[] isVisited = new boolean[V];
		stack.push(start);
		isVisited[start] = true;

		while (!stack.isEmpty()) {
			int currrent = stack.pop();
			System.out.println(currrent);
			for (int i = 0; i < V; i++) {
				if (adjMatrix[currrent][i] != 0) {
					if (!isVisited[i]) {
						stack.add(i);
						isVisited[i] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		int[][] adjMatrix = new int[V][E];
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[start][to] = adjMatrix[to][start] = 1;
		}

//		for (int i = 0; i < adjMatrix.length; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}
		
		bfs(adjMatrix, 0, V);
		System.out.println();
		dfs(adjMatrix, 0, V);
	}

}
/*
7
8
0 1
0 2
1 3
1 4
2 4
3 5
4 5
5 6
 */