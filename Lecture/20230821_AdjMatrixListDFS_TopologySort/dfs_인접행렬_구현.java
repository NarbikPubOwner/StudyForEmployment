import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//무향 그래프 인접 행렬
public class dfs_인접행렬_구현 {
	static boolean[] isVisited;
	static void recursive_dfs(int[][] adjMatrix, int V, int current) {
		isVisited[current] = true;
		System.out.println(current);
		for (int i = 0; i < V; i++) {
			if (adjMatrix[current][i] != 0 && !isVisited[i]) {
				recursive_dfs(adjMatrix, V, i);
			}
		}
	}
	
	static void stack_dfs(int[][] adjMatrix, int V, int current) {
		isVisited = new boolean[V];
		Stack<Integer> stack = new Stack<>();
		
		stack.push(current);
		isVisited[current] = true;
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			System.out.println(cur);
			for (int i = 0; i < V; i++) {
				if(adjMatrix[cur][i] != 0 && !isVisited[i]) {
					stack.push(i);
					isVisited[i] = true;
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		int [][] adjMatrix = new int[V][V];
		
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = 1;
		}
		isVisited = new boolean[V];
		recursive_dfs(adjMatrix, V, 0);
		System.out.println();
		stack_dfs(adjMatrix, V, 0);
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