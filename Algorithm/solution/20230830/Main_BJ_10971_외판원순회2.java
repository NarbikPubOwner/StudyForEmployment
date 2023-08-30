import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_10971_외판원순회2 {

	static int N;
	static boolean[] isVisited;
	static int[][] adjMatrix;
	static int result = Integer.MAX_VALUE;
	

	static boolean allVisited() {
		for (int i = 0; i < N; i++) {
			if (!isVisited[i]) 
				return false;
		}
		return true;
	}
	static void dfs(int now, int cost) {
		if (allVisited()) {//모두 방문한 경우일 때
			if (adjMatrix[now][0] != 0) {//원점으로 다시 돌아올 수 있는지 확인
				result = Math.min(result, cost + adjMatrix[now][0]);//돌아올 수 있다면 값 추가후 최소값 추가
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (adjMatrix[now][i] != 0 && !isVisited[i]) {//현재 정점 기준 아직 방문하지 않았고, 가중치가 존재하는 정점을 방문
				isVisited[i] = true;//방문 표시
				dfs(i, cost + adjMatrix[now][i]);
				isVisited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isVisited = new boolean[N];
		isVisited[0] = true;
		dfs(0, 0);	
		System.out.println(result);
		
	}
}


