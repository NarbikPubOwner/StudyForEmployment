import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 최소 힙의 삽입 연산 -> 힙의 끝에 값 X를 넣는다. 이후 부모노드와 자신의 값을 비교하고 자신이 클 경우 부모노드와 자신의 위치를 바꾼다. 이것을 루트노드에 도달하거나 바뀌지 않을때까지 반복한다
 * 자바에서는 우선순위 큐로 구현되어있다. 우선순위 큐 자체가 배열, 연결리스트, 힙으로 구현되는 것이기 때문에, 그리고 힙으로 구현되는 것이 가장 빠르다
 */
public class Main_BJ_1927_최소힙 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if (value == 0) {
				if (!q.isEmpty()) sb.append(q.poll()).append("\n");
				else sb.append(0).append("\n");
			}
			else q.offer(value);
		}
		System.out.println(sb);
		
	}

}
