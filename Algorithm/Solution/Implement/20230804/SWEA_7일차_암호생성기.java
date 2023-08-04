import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7일차_암호생성기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			Queue<Integer> cycle = new ArrayDeque<>();
			Queue<Integer> q = new ArrayDeque<Integer>();
			while (st.hasMoreTokens()) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			for (int i = 1; i <= 5; i++) {
				cycle.offer(i);
			}
			while (true) {
				int cycleNum = cycle.poll();
				int temp = q.poll() - cycleNum;
				cycle.offer(cycleNum);
				if (temp <= 0 ) q.offer(0);
				else q.offer(temp);
				if (temp <= 0) {
					System.out.print("#" + t + " ");
					while (q.size() != 0) {
						System.out.print(q.poll() + " ");
					}
					System.out.println();
					break;
				}
			}
		}
	}
}
