import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//입력 - start - end / 가중치
//C B 10
//A B 3
//B C 80
//C S 10
//B E 10
//S E 20
//S H 5
//H G 5
//G F 5
//F D 5
//D A 5
//2번째 최소경로의 가중치 합을 출력하세요
//다익스트라를 우선순위큐로 구현한다. 여기서 특정 정점에 대한 가중치 합이 N번째로 출력되는 것을 답으로 하면 된다.
//문제 출처 : 네이버웹툰 공대생 너무만화 40화, 비슷한 문제 -> 백준 1854


public class ESTooBadCartoon_40_SooYeonIsSoAmazing {
	
	
	static void dijkstra(int[][] adjMatrix, int start, int end, int k) {
		/*
		 * 
		 */
		PriorityQueue<Node> pq = new PriorityQueue<>();
		PriorityQueue<Integer>[] dis = new PriorityQueue[26];
		for (int i = 0; i < 26; i++) {
			dis[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
		pq.offer(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for (int i = 0; i < 26; i++) {
				int newCost = cur.weight + adjMatrix[cur.vertex][i];
				if (adjMatrix[cur.vertex][i] == 0) continue;
				
				
				if (dis[i].size() < k) {
					dis[i].offer(newCost);
					pq.offer(new Node(i, newCost));
				}
				else if (newCost < dis[i].peek()) {
					dis[i].poll();
					dis[i].offer(newCost);
					pq.offer(new Node(i, newCost));
				}
			}
		}
		System.out.println(dis[end].poll());
	}
	//기본 다익스트라
//	static void dijkstra(int[][] adjMatrix, int start, int end) {
//		/*
//		 * pq에서 정점을 빼내고, 빼낸 정점을 방문처리한다
//		 * 
//		 * 빼낸 점의 인접 정점들을 방문한다(방문배열이 true면 방문하지 않는다)
//		 * 해당 정점의 weight + 이전 정점까지의 weight합 < 해당정점의 distance배열 값 이라면 distance배열 값에 해당 값을 삽입하고 pq에 넣는다
//		 * 
//		 */
//		
//		PriorityQueue<Node> pq = new PriorityQueue<>();
//		int[] distance = new int[26];
//		boolean[] visited = new boolean[26];
//		Arrays.fill(distance, Integer.MAX_VALUE);
//		pq.offer(new Node(start, 0));
//		distance[start] = 0;
//		
//		while (!pq.isEmpty()) {
//			Node cur = pq.poll();
//			if (visited[cur.vertex]) continue;
//			visited[cur.vertex] = true;
//			
//			for (int i = 0; i < 26; i++) {
//				if (visited[i] || adjMatrix[cur.vertex][i] == 0) continue;
//				
//				if (cur.weight + adjMatrix[cur.vertex][i] < distance[i]) {
//					distance[i] = cur.weight + adjMatrix[cur.vertex][i];
//					pq.offer(new Node(i, cur.weight + adjMatrix[cur.vertex][i]));
//				}
//			}
//		}
//		System.out.println(Arrays.toString(distance));
//	}
	
	static class Node implements Comparable<Node> {
		int vertex;
		int weight;
		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int start = st.nextToken().charAt(0)-65;
		int end = st.nextToken().charAt(0)-65;
		int N  = Integer.parseInt(st.nextToken());
		
		int[][] adjMatrix = new int [26][26];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int startV = st.nextToken().charAt(0)-65;
			int endV = st.nextToken().charAt(0)-65;
			int weight = Integer.parseInt(st.nextToken());
			adjMatrix[startV][endV] = weight;
			adjMatrix[endV][startV] = weight;
		}
		dijkstra(adjMatrix, start, end, 2);
		System.out.println();
	}
}
