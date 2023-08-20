import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

public class Main_BJ_24479_알고리즘수업_깊이우선탐색1 {

	static void dfs(Node[] adjList, int start, int N) {
		Stack<Integer> stack = new Stack<>();
		List<Integer> list;
		int[] isVisied = new int[N];
		stack.push(start);
		int cnt = 1;
		
		while (!stack.isEmpty()) {
			list = new ArrayList<>();
			int cur = stack.pop();
			if (isVisied[cur] == 0) {
				isVisied[cur] = cnt++;
			}
			
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if (isVisied[temp.vertex] == 0) {
					list.add(temp.vertex);
				}
			}
			Collections.sort(list, Collections.reverseOrder());
			for (int i : list) {
				stack.push(i);
			}
		}
		for (int i = 0; i < isVisied.length; i++) {
			System.out.println(isVisied[i]);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int startPoint = Integer.parseInt(st.nextToken())-1;
		
		Node[] adjList = new Node[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken()) -1;
			int to = Integer.parseInt(st.nextToken()) -1;
			adjList[start] = new Node(to, adjList[start]);
			adjList[to] = new Node(start, adjList[to]);
		}
		
		dfs(adjList, startPoint, N);
		
	}
}
/*
5 5 1
1 4
1 2
2 3
2 4
3 4 


6 4 1
2 3
1 4
1 5
4 6

6 7 1
1 6
1 2
2 6
2 3
2 4
3 5
4 5

4 4 1
1 2
1 3
1 4
2 4
*/