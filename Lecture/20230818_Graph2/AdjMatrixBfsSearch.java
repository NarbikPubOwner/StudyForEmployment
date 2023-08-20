import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

//무향 그래프 인접 행렬
public class AdjMatrixBfsSearch {
	static void bfs(int[][] adjMatrix, int size, int start) {//그래프, 크기, 시작점
		Queue<Integer> queue = new ArrayDeque<Integer>();//큐에 넣는 값은 방문대상을 관리할 값 외에도 관련 다른 값을 넣을 수 있다(가중치)
		boolean[] visited = new boolean[size];
		
		queue.offer(start);
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println((char)(current+65));
			
			//현 정점의 인접정점들 체크하며 대기열에 넣기
			for (int i = 0; i < size; i++) {
				if (adjMatrix[current][i]!= 0 && !visited[i]) {//인접 정점인가 && 방문한 적이 있는가
					queue.offer(i);
					visited[i] = true;
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
		
		bfs(adjMatrix, V, 0);
		
		
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