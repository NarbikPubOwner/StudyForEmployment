import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	int vertex;
	Node Next;
	public Node(int vertex, Node next) {
		super();
		this.vertex = vertex;
		Next = next;
	}
	@Override
	public String toString() {
		return "Node [vertex=" + vertex + ", Next=" + Next + "]";
	}
	
}

public class 인접리스트예제 {

	
	static void bfs(Node[] adjList, int start, int V) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[V];
		queue.offer(start);
		isVisited[start] = true;
		while (!queue.isEmpty()) {
			int currrent = queue.poll();
			System.out.println(currrent);
			
			for (Node temp = adjList[currrent]; temp != null; temp = temp.Next) {
				if (!isVisited[temp.vertex]) {
					queue.offer(temp.vertex);
					isVisited[temp.vertex] = true;
				}
			}
		}
	}
	
	static void dfs(Node[] adjList, int start, int V) {
		Stack<Integer> stack = new Stack<>();
		boolean[] isVisited = new boolean[V];
		stack.push(start);

		while (!stack.isEmpty()) {
			int currrent = stack.pop();
			if (!isVisited[currrent]) {
				System.out.println(currrent);
				isVisited[currrent] = true;
			}
			
			for (Node temp = adjList[currrent]; temp != null; temp = temp.Next) {
				if (!isVisited[temp.vertex]) {
					stack.push(temp.vertex);
				}
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		Node[] adjList = new Node[V];
		
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[start] = new Node(to, adjList[start]);
			adjList[to] = new Node(start, adjList[to]);
			
		}
		
		for (Node node : adjList) {
			System.out.println(node);
		}
		
		bfs(adjList, 0, V);
		System.out.println();
		dfs(adjList, 0, V);
	}

}
/*
4
4
0 1
0 2
0 3
1 3 
*/