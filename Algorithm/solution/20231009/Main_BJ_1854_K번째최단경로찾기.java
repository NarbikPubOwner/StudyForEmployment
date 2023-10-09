import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다잌스트라 알고리즘 변형을 이용해야 하는 문제
 * 기존 다잌스트라 알고리즘은 방문배열을 통해 정점방문횟수를 줄인다. 하지만 그렇기 때문에 1번째 최단 경로만 구할 수 있다
 * 그렇다면 방문체크를 하지 않는다면? -> 해당 정점을 방문하는 모든 가중치 합을 구할 수 있다 -> 그리고 어차피 가중치가 기존보다 작은 경우에만 추가진행을 하기 때문에 언제가는 멈춘다. 즉 무한루프를 걱정할 필요가 없다
 * 그렇다해도 K번만 돌면 될 것을 N번 돌면 시간 복잡도가 문제가 되기 때문에 K번째의 최단 경로를 구하라 주어진 경우, 거리값을 저장하는 배열의 size가 K보다 작다면 해당 방향으론 진행하지 않도록 한다
 * 
 * 아래의 변경사항에 따라 기존 다익스트라 알고리즘을 변경하면 된다
 * 변경사항
 * 1. 변수 -> 방문배열 삭제, distance 1차원 int배열 -> PriorityQueue<Integer>[N]으로 변경(내림차순 정렬로 사용)
 * 2. 로직
 * 		cur정점의 인접 정점들을 방문할때마다  dis[방문정점]을 확인한다. 이전가중치+현재간선의가중치는 newCost라 한다, 만약 dis[방문정점].size < K라면 묻지도 따지지도 않고 진행
 * 		아닐경우 dis[방문정점].peek이 newCost보다 크다면 진행
 * 		진행 로직 : dis[방문정점].offer(newCost), pq.offer(node.vertex, newCost)
 * 		
 * 		while문이 끝나면 dis에는 각 정점에 대한 최소 거리가 k개 저장된다, 그리고 내림차순 정렬을 했기 때문에 dis[정점].peek을 하면 k번째 최소가중치가 나온다
 * 		
 */
public class Main_BJ_1854_K번째최단경로찾기 {
	/*
	 * 안써도 무방합니다
	 */
	static class edge implements Comparable<edge> {
		int to;
		int w;
		
		public edge(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(edge o) {
			return w-o.w;
		}
	}
	
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}
	
	static void newDijkstra(Node[] adjList, int start, int N, int K) {
		PriorityQueue<edge> pq = new PriorityQueue<>();
		PriorityQueue<Integer>[] dis = new PriorityQueue[N];//해당 방문정점의 k개까지의 최단거리르 저장할 pq배열
		
		for (int i = 0; i < dis.length; i++) {
			dis[i] = new PriorityQueue<>(Collections.reverseOrder());//내림차순 정렬 하도록 설정
		}
		
		/*
		 * 시작 초기화
		 */
		pq.offer(new edge(start, 0));
		dis[0].offer(0);
	
		while (!pq.isEmpty()) {
			edge cur = pq.poll();
			for (Node node = adjList[cur.to]; node != null; node = node.next){
				int newCost = cur.w + node.weight;
				/*
				 *	k보다 사이즈가 작다는 것은, 비교 할 필요도 없이 현재 얻어낸 가중치합이 기존에 존재하는 가중치합 다음의 최솟값이라는 이야기이다
				 */
				if (dis[node.vertex].size() < K) {
					dis[node.vertex].offer(newCost);
					pq.offer(new edge(node.vertex, newCost));
				}
				/*
				 * 만약 그렇지 않은 경우, 기존에 존재하는 가중치합의 첫번째와만 비교해서 삽입을 결정하면 된다
				 */
				else {
					if (newCost < dis[node.vertex].peek()) {
						dis[node.vertex].poll();
						dis[node.vertex].offer(newCost);
						pq.offer(new edge(node.vertex, newCost));
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dis.length; i++) {
			if (dis[i].size() < K) {
				sb.append(-1).append("\n");
			}
			else {
				sb.append(dis[i].poll()).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());//정점 개수
		int M = Integer.parseInt(st.nextToken());//간선개수 - 단방향
		int K = Integer.parseInt(st.nextToken());//몇번째 최단경로를 출력할 것인지에 대한 K
		Node[] adjList = new Node[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			adjList[start] = new Node(end, weight, adjList[start]);
		}
		
		newDijkstra(adjList, 0, N, K);
		
	}
}
