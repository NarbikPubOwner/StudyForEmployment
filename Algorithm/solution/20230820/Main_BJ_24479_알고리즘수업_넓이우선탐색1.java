import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
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

public class Main_BJ_24479_알고리즘수업_넓이우선탐색1 {
	
	static void bfs(Node[] adjList, int start, int N) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		List<Integer> list;
		int[] isVisited = new int[N];
		
		queue.offer(start);
		int cnt = 1;
		while (!queue.isEmpty()) {
			list = new ArrayList<Integer>();
			int cur = queue.poll();
			isVisited[cur] = cnt++;
			for (Node node = adjList[cur]; node != null; node = node.next) {
				if (isVisited[node.vertex] == 0) {
//					queue.offer(node.vertex);
					list.add(node.vertex);
					isVisited[node.vertex] = -1;
				}
			}
			Collections.sort(list);
			for (int i : list) {
				queue.offer(i);
			}
//			System.out.println();
		}
		
		
		for (int i = 0; i < isVisited.length; i++) {
			System.out.println(isVisited[i]);
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
		
		bfs(adjList, startPoint, N);
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