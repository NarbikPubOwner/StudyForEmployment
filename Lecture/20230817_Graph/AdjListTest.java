import java.util.Arrays;
import java.util.Scanner;

public class AdjMatrixTest {

	static class Node {
		int vertext;// 관계를 맺고 있는 타정점 정보
		Node next; // 연결리스트 유지를 위한 다음 노드 참조

		public Node(int vertext, Node next) {
			super();
			this.vertext = vertext;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [vertext=" + vertext + ", next=" + next + "]";//next를 출력하면, toString이 호출되기 때문에 결과가 제대로 나옴
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();

		Node[] adjList = new Node[V];

		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}

		for (Node node : adjList) {
			//입력의 역순으로 출력됨
			System.out.println(node);
		}
	}
}

/*
7
8
0 1
0 2	
0 5
0 6
4 3
5 3
5 4
6 4
 */