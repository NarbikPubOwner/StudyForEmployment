import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


//절댓값 힙은 -> 우선순위큐와 동일 <- 암기
class Abs implements Comparable<Abs>{
	private int key;
	private int value;
	
	
	
	public int getKey() {
		return key;
	}



	public void setKey(int key) {
		this.key = key;
	}



	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public Abs(int key) {
		super();
		this.key = Math.abs(key);
		this.value = key;
	}

	@Override
	public int compareTo(Abs o) {
		if (key == o.key) {
			return value - o.value;
		}
		return key-o.key;
	}
}

public class Main_BJ_11286_절대값힙 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//절대값 최소 힙
		PriorityQueue<Abs> pq = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < N; i++) {
			int key = Integer.parseInt(br.readLine());
			
			if (key == 0) {
				if (pq.size() != 0) {
					sb.append(pq.poll().getValue()).append("\n");
				}
				else sb.append(0).append("\n");
			}
			else {
				pq.offer(new Abs(key));
			}
		}
		System.out.println(sb);
	}

}
