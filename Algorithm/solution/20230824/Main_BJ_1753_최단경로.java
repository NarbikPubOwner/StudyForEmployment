import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 그냥 다익스트라 알고리즘 구현하면 됨
 */
public class Main_BJ_1753_최단경로 {
	/*
	 * 인접 리스트를 위한 노드 클래스 선언
	 */
	static class Node {
		int vertex;
		Node next;
		int weight;
		public Node(int vertex, Node next, int weight) {
			super();
			this.vertex = vertex;
			this.next = next;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + ", weight=" + weight + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int startPoint = Integer.parseInt(br.readLine())-1;

		Node [] adjList = new Node[V];
		
		/*
		 * 인접 리스트 설정
		 */
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from], weight);
		}
		
		boolean[] isVisited = new boolean[V];
		int[] distance = new int[V];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startPoint] = 0;
		
		int min;
		int current;
		
		for (int i = 0; i < V; i++) {
			min = Integer.MAX_VALUE;
			current = -1;
			
			/*
			 * 인접 정점들 중 가장 작은 가중치합을 가진 정점을 찾는 작업
			 */
			for (int j = 0; j < distance.length; j++) {
				if (!isVisited[j] && min > distance[j]) {
					current = j;
					min = distance[j];
				}
			}
			
			if (current == -1) break;//방문 가능한 인접 정점이 없을 경우 멈춤
			isVisited[current] = true;//방문을 했기 때문에 해당 정점을 방문 처리
			
			/*
			 * 그렇게 찾은 정점의 인접을 돈다, 해당 정점을 통해 방문한 정점의 최종 가중치가 기존 방문보다 작다면 해당 가중치고 세팅한다
			 */
			for (Node node = adjList[current]; node != null; node = node.next) {
				if (!isVisited[node.vertex] && distance[node.vertex] > min + node.weight) {
					distance[node.vertex] = min + node.weight;
				}
			}
			
		}
		/*
		 * 시간 초과가 나는 인접행렬 코드
		 */
//		int[][] adjMatrix = new int[V][V];
//
//		for (int i = 0; i < E; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			adjMatrix[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = Integer
//					.parseInt(st.nextToken());
//		}
//		
//		boolean[] isVisited = new boolean[V];
//		int[] distance = new int[V];
//		Arrays.fill(distance, Integer.MAX_VALUE);
//		distance[startPoint] = 0;
//		
//		int min;
//		int current;
//		
//		for (int i = 0; i < V; i++) {
//			min = Integer.MAX_VALUE;
//			current = -1;
//			
//			for (int j = 0; j < distance.length; j++) {
//				if (!isVisited[j] && min > distance[j]) {
//					current = j;
//					min = distance[j];
//				}
//			}
//			
//			if (current == -1) break;
//			isVisited[current] = true;
//			
//			for (int j = 0; j < distance.length; j++) {
//				if (!isVisited[j] && adjMatrix[current][j] != 0 
//					&& distance[j] > min + adjMatrix[current][j]){
//					distance[j] = min + adjMatrix[current][j];
//				}
//			}
//		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < distance.length; i++) {
			sb.append(distance[i] != Integer.MAX_VALUE ? distance[i] : "INF").append("\n");
		}
		System.out.println(sb);
	}

}
