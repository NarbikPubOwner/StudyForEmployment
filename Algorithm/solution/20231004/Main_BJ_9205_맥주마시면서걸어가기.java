import java.beans.FeatureDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_9205_맥주마시면서걸어가기 {
	static String result;
	static boolean isEnd;
	static boolean canGO( int[] xy1, int[] xy2) {
		double distance = Math.abs(xy1[0]-xy2[0]) + Math.abs(xy1[1]-xy2[1]);
		if (distance/50.0 <= 20) {
			return true;
		}
		return false;
	}
	
	static void bfs(int[] home, int[] festival, List<int[]> cStores) {
		//visited거나, 가는게 불가능하다면 안간다
		Queue<int[]> q = new ArrayDeque<>();
		home[2] = 1;
		q.offer(home);
		
		while (!q.isEmpty()) {
			int cur[] = q.poll();
			if (canGO(cur, festival)) {
				result = "happy";
				break;
			}
			for (int[] temp : cStores) {
				if (temp[2] == 0 && canGO(cur, temp)) {
					temp[2] = 1;
					q.offer(temp);
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			result = "sad";
			isEnd = false;
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int[] home  = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0};
			
			List<int[]> cStores = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				cStores.add(new int[] {x,y,0});
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			int[] festival = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0};
			bfs(home, festival, cStores);
			System.out.println(result);
		}
	}
}



