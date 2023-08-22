import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.xml.soap.Node;
/*
 * 초기 사고 흐름
 */
//위상정렬
//indegree, 인접 리스트 or 행렬 필요
//간선이 들어오면 행렬[진출차수][진입차수] = 1로 한다, indegree[진입차수]++
//모든 간선을 처리하면 indegree에서 값이 0인것부터 큐에 넣는다
//큐를 pop 하면서 행렬의 해당 진출차수의 진입차수를 0으로 만들고 indegree의 해당 값을 --한다(큐에서 pop시 표기)
//이때 0이 된다면 큐에 넣는다

/*
 * 위상 정렬이란?
 * 어떤 작업들을 함에 있어 각 작업들에 선행작업이 존재할 때, 어떻게 진행이 가능한지에 대한 표현
 * ex) 1->2(2작업을 위해선 1작업의 선행이 필수), 4->3(3의 작업을 위해선 4작업의 선행이 필수)
 * 라고 한다면 가능한 작업 순서는 1->2->4->3 or 4->3->1->2 or 1->4->3->2 or 4->1->2->3 등의 순서가 가능하다
 * (12, 43에 대한 순서만 정해져 있기 때문. 해당 순서만 지킨다면 1보다 4or3이 먼저 진행되던 말던 상관이 없다)
 */

//인접 리스트 구현을 위한 노드 클래스
class Node2 {
	int vertex;
	Node2 next;
	public Node2(int vertex, Node2 node) {
		super();
		this.vertex = vertex;
		this.next = node;
	}
	@Override
	public String toString() {
		return "Node [vertex=" + vertex + ", node=" + next + "]";
	}
	
}
public class Main_BJ_2252_줄세우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] inDegree = new int[N];
		Node2[] adjList = new Node2[N];
		
		/*
		 * 인접행렬은 메모리 초과가 난다
		 */
//		int[] inDegree = new int[N];
//		int[][] adjMatrix = new int[N][N];
//		
//		
//		for (int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			int advance = Integer.parseInt(st.nextToken())-1;//진출
//			int ingress = Integer.parseInt(st.nextToken())-1;//진입
//			inDegree[ingress]++;
//			adjMatrix[advance][ingress] = 1;
//		}
//		
//		Queue<Integer> queue = new ArrayDeque<Integer>();
//		for (int i = 0; i < N; i++) {
//		 	if(inDegree[i] == 0) {
//		 		queue.offer(i);
//		 	}
//		}
//		while (!queue.isEmpty()) {
//			int cur = queue.poll();
//			System.out.print(cur+1 + " ");
//			for (int i = 0; i < N; i++) {
//				if(adjMatrix[cur][i] == 1) {
//					adjMatrix[cur][i] = 0;
//					inDegree[i]--;
//					if (inDegree[i] == 0) {
//						queue.offer(i);
//					}
//				}
//			}
//		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int advance = Integer.parseInt(st.nextToken())-1;//진출
			int ingress = Integer.parseInt(st.nextToken())-1;//진입
			inDegree[ingress]++;
			adjList[advance] = new Node2(ingress, adjList[advance]);//진출 -> 진입 표기
		}
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 0; i < N; i++) {
		 	if(inDegree[i] == 0) queue.offer(i);//자신에 대한 진입이 없는, 즉 시작이 가능한 노드를 큐에 넣는다.
		}
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			System.out.print(cur+1 + " ");
			for (Node2 node = adjList[cur]; node != null; node = node.next) {//cur에 연결된 모든 노드를 순회
				inDegree[node.vertex]--;//진출 -> 진입시, 진입되는 노드의 차수를 감소시킨다
				if (inDegree[node.vertex] == 0) queue.offer(node.vertex);//그렇게 차수가 0이 되면 해당 진입 노드는 출력이 가능하다
			}
		}
	}
}
