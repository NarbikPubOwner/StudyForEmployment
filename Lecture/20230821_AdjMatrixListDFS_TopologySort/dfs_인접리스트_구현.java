import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//무향 그래프 인접 행렬
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
public class dfs_인접리스트_구현 {
	static boolean[] isVisited;
	
	static void recursive_dfs(Node[] adjList, int V, int cur) {
		isVisited[cur] = true;
		System.out.println(cur);
		
		for (Node node = adjList[cur]; node != null; node = node.next){
			if (!isVisited[node.vertex]) {
				recursive_dfs(adjList, V, node.vertex);
				isVisited[node.vertex] = true;
			}
		}
	}
	
	static void stack_dfs(Node[] adjList, int V, int cur) {
		isVisited = new boolean[V];
		
		Stack<Integer> stack = new Stack<>();
		stack.push(cur);
		isVisited[cur] = true;
		
		while (!stack.isEmpty()) {
			
			cur = stack.pop();
			System.out.println(cur);
			
			for (Node node = adjList[cur]; node != null; node = node.next) {
				if (!isVisited[node.vertex]) {
					stack.push(node.vertex);
					isVisited[node.vertex] = true;
				}
			}
		}
		
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		Node [] adjList = new Node[V];
		
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		isVisited = new boolean[V];
		
		recursive_dfs(adjList, V, 0);
		System.out.println();
		stack_dfs(adjList, V, 0);
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