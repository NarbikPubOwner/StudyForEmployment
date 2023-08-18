import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
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

public class Main_BJ_1260_DFS와BFS {
	// 인접 행렬로 구현
	static int V;
	static int E;
	static int start;
	static int[][] adjMatrix;
	static StringBuilder sb;

	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<Integer>();// 큐에 넣는 값은 방문대상을 관리할 값 외에도 관련 다른 값을 넣을 수 있다(가중치)
		boolean[] visited = new boolean[V + 1];

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current).append(" ");

			// 현 정점의 인접정점들 체크하며 대기열에 넣기
			for (int i = 1; i < V + 1; i++) {
				if (adjMatrix[current][i] != 0 && !visited[i]) {// 인접 정점인가 && 방문한 적이 있는가
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
		sb.append("\n");
	}

	static void dfs() {
		Stack<Integer> stack = new Stack<Integer>();// 큐에 넣는 값은 방문대상을 관리할 값 외에도 관련 다른 값을 넣을 수 있다(가중치)
		boolean[] visited = new boolean[V + 1];

		stack.push(start);
		while (!stack.isEmpty()) {

			int current = stack.pop();
			if (!visited[current]) {
				sb.append(current).append(" ");
				visited[current] = true;
			}
			

			List<Integer> temp = new ArrayList<>();
			for (int LinkedNode = 1; LinkedNode <= V; LinkedNode++) {
				if (adjMatrix[current][LinkedNode] != 0 && !visited[LinkedNode]) {
					temp.add(LinkedNode);
				}
			}
			Collections.sort(temp, Collections.reverseOrder());
			for (Integer integer : temp) {
				stack.add(integer);
			}

		}
		sb.append("\n");

//		Stack<Integer> stack = new Stack<Integer>();// 큐에 넣는 값은 방문대상을 관리할 값 외에도 관련 다른 값을 넣을 수 있다(가중치)
//		boolean[] visited = new boolean[V + 1];
//
//		stack.push(start);
//		visited[start] = true;
//		while (!stack.isEmpty()) {
//
//			int current = stack.pop();
//			sb.append(current).append(" ");
//			
//			for (int LinkedNode = 1; LinkedNode <= V; LinkedNode++) {
//				if (adjMatrix[current][LinkedNode] != 0 && !visited[LinkedNode]) {
//					stack.push(LinkedNode);
//					visited[LinkedNode] = true;
//				}
//			}
//
//		}
//		sb.append("\n");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		adjMatrix = new int[V + 1][V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjMatrix[from][to] = adjMatrix[to][from] = 1;
		}

		dfs();
		bfs();

		System.out.println(sb);

	}

}

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//class Node{
//	int vertex;
//	Node next;
//	public Node(int vertex, Node next) {
//		super();
//		this.vertex = vertex;
//		this.next = next;
//	}
//	@Override
//	public String toString() {
//		return "Node [vertex=" + vertex + ", next=" + next + "]";
//	}
//	
//}
//
//public class Main_BJ_1260_DFS와BFS {
//	//인접 행렬로 구현
//	static int V;
//	static int E;
//	static int start;
//	static Node[] adjList;
//	
//	static void bfs() {
//		Queue<Integer> queue = new ArrayDeque<Integer>();
//		boolean[] isVisied = new boolean[V+1];
//		
//		queue.offer(start);
//		isVisied[start] = true;
//		while (!queue.isEmpty()) {
//			int current = queue.poll();
//			System.out.print(current + " ");
//			
//			for (Node temp = adjList[current]; temp != null; temp = temp.next) {
//				if (!isVisied[temp.vertex]) {
//					queue.offer(temp.vertex);
//					isVisied[temp.vertex] = true;
//				}
//			}
//			
//		}
//	}
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		
//		V = Integer.parseInt(st.nextToken());
//		E = Integer.parseInt(st.nextToken());
//		start = Integer.parseInt(st.nextToken());
//		adjList = new Node[V+1];
//		
//		for (int i = 0; i < E; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			int from = Integer.parseInt(st.nextToken());
//			int to = Integer.parseInt(st.nextToken());
//			adjList[from] = new Node(to, adjList[from]);
//			adjList[to] = new Node(from, adjList[to]);
//		}
//		bfs();
//		
//		
//	}
//
//}
