import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

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

public class AdjListBfsSerach {

	static int N;
	static int E;
	static Node[] AdjList;
	
	static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N];
		queue.offer(start);
		isVisited[start] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println((char)(current+65));
			for (Node temp = AdjList[current]; temp != null; temp = temp.next) {
				if (!isVisited[temp.vertex]) {
					queue.offer(temp.vertex);
					isVisited[temp.vertex] = true;
				}
			}
		}
		
//		내가 짠 거 - 같은 코드
//		boolean[] isVisited = new boolean[N];
//		Queue<Integer> q = new ArrayDeque<Integer>();
//		q.offer(0);
//		isVisited[0] = true;
//		while (q.size() != 0) {
//			int t= q.poll();
//			System.out.println(t);
//			Node temp = AdjList[t];
//			while (temp != null) {
//				if (!isVisited[temp.vertex]) {
//					q.offer(temp.vertex);
//					isVisited[temp.vertex] = true;
//					temp = temp.next;
//				}
//				else {
//					temp = temp.next;
//				}
//			}
//		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		AdjList = new Node[N];
		
		StringTokenizer st;
		for (int n = 0; n < E; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			AdjList[from] = new Node(to, AdjList[from]);
			AdjList[to] = new Node(from, AdjList[to]);
		}
		
		bfs(0);
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
