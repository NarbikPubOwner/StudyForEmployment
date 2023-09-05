import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 주어진 그래프를 0번에서 bfs탐색하는 문제
 */
public class Main_BJ_2606_바이러스 {
	
	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		Node[] adjList = new Node[N];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			adjList[start] = new Node(end, adjList[start]);
			adjList[end] = new Node(start, adjList[end]);
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		q.offer(0);
		visited[0] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (Node node = adjList[cur]; node != null; node = node.next) {
				if (!visited[node.vertex]) {
					cnt++;
					visited[node.vertex] = true;
					q.offer(node.vertex);
				}
			}
		}
		System.out.println(cnt);
	}

}
