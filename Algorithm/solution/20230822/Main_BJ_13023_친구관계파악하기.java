import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 연속된 친구관계가 4개 이상인 경우를 찾는 문제
 * 그래프
 * dfs
 * 백트래킹
 */
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
public class Main_BJ_13023_친구관계파악하기 {
	/*
	 * 그래프를 구성한다
	 * 모든 정점을 돌면서 연쇄가 4 이상인 경우 1 반환
	 * 모든 정점을 돌았는데도 연쇄가 4 이상인 경우가 발견되지 않는다면 0 반환
	 * 
	 * 인접리스트로 만들고 양방향
	 */
	static Node[] adjList;
	static boolean[] isVisited;
	static int result = 0;
	static void dfs(int friendCnt, int next) {
		if (friendCnt >= 5) {
			result = 1;
			return;
		}
		
		for (Node node = adjList[next]; node != null; node = node.next) {
			if (!isVisited[node.vertex]) {
				isVisited[node.vertex] = true;
				dfs(friendCnt+1, node.vertex);
				isVisited[node.vertex] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N];
		isVisited = new boolean[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[start] = new Node(to, adjList[start]);
			adjList[to] = new Node(start, adjList[to]);
		}
		for (int i = 0; i < N; i++) {
			if (result != 1) {
				isVisited[i] = true;
				dfs(1, i);
				isVisited[i] = false;
			}
			
		}
		
		System.out.println(result);
		
	}
}
