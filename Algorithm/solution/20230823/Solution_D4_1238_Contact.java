import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_1238_Contact {

	/*
	 * 
	 * bfs순회
	 * depth에 따라서 값들을 따로 저장해야 한다 -> 큐에 바로 넣지말고 depth에 해당하는 값들 모두를  따로 저장한다(queue is empty가 될 때 까지)
	 * 이후 다시 그 값들을 큐에 넣고, 동시에 해당 리스트의 값은 다른 리스트를 이용하던, 알아서 유지해야한다
	 * 리스트가 is empty가 될 때까지 반복하고 리스트가 is empty라면 더 이상 다른 정점으로 나아갈 수 없다는 것이기 때문에, 이전에 저장해둔 리스트에서 가장 큰 값을 출력하면 된다
	 */
	
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	static void bfs(int startPoint) {
		
		Queue<Integer> queue = new ArrayDeque<Integer>();//처리해야할 값들을 저장
		List<Integer> list = new ArrayList<>();//해당 depth의 인접 값들을 저장
		List<Integer> prevList = new ArrayList<>();//이전 값들을 저장
		boolean[] isVisited = new boolean[101];
		
		queue.offer(startPoint);
		isVisited[startPoint] = true;
		
		while (true) {
			//bfs
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				
				for (int k = 1; k < 101; k++) {
					if (arr[cur][k] == 1 && !isVisited[k]) {
						list.add(k);//큐에 바로 넣지 않는다
						isVisited[k] = true;
					}
				}
			}
			if (list.isEmpty()) {//더 이상 순회할 정점이 없다면
				int result = 0;
				for (Integer i : prevList) {
					result = Math.max(result, i);
				}
				sb.append(result);
				break;
			}
			else {
				prevList = new ArrayList<>();
				for (int is : list) {//다음 순회 정점들을 큐에 삽입함과 동시에 유지한다
					prevList.add(is);
					queue.offer(is);
				}
				list = new ArrayList<>();
			}
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int startPoint = Integer.parseInt(st.nextToken());
			arr = new int[101][101];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N/2; i++) {
				arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			sb.append("#").append(t).append(" ");
			bfs(startPoint);
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}
