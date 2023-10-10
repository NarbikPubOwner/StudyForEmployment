import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_7465_창용마을무리의개수 {

	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
		
	}
	
	static int getSwarm(Node[] adjList, int N) {
		boolean[] visited = new boolean[N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			cnt++;
			
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(i);
			visited[i] = true;
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				
				for (Node node = adjList[cur]; node != null; node = node.next) {
					if (visited[node.vertex]) continue;
					visited[node.vertex] = true;
					q.offer(node.vertex);
				}
			}
		}
		
		return cnt;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Node[] adjList = new Node[N];
			
			
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int start = Integer.parseInt(st.nextToken())-1;
				int end = Integer.parseInt(st.nextToken())-1;
				adjList[start] = new Node(end, adjList[start]);
				adjList[end] = new Node(start, adjList[end]);
			}
			sb.append("#").append(t).append(" ").append(getSwarm(adjList, N)).append("\n");
		}
		System.out.println(sb);
	}



}
