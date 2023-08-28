import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.tools.Diagnostic;

import sun.security.util.DisabledAlgorithmConstraints;

public class SD1_Dijkstra {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[][] adjMatrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		boolean[] isVisited = new boolean[N];
		
		int[] minDistance = new int[N];
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		minDistance[start] = 0;
		int min;
		int current;
		
		//정점 개수만큼 반복함, i는 아무 의미 없음
		for (int i = 0; i < N; i++) {
			min = Integer.MAX_VALUE;
			current = -1;
			/*
			 * 첫번째 start수행 시에는 minDistance[start]가 0이고 min은 MaxValue이니 무조건 start부터 수행하게 된다
			 */
			for (int j = 0; j < N; j++) {
				if ( !isVisited[j] && minDistance[j] < min) {
					min = minDistance[j];
					current = j;
				}
			}
			
			if (current == -1) break;//이어지는 곳이 아무곳도 없기 때문에 중단
			isVisited[current] = true;//여기 까지 왔다면, 인접한 곳 중에서 최소인 부분을 걸러내었다, 즉 거기를 무조건적으로 방문한다
			if (current == end) break;//목표지점에 도달했다면 중단
			
			for (int j = 0; j < minDistance.length; j++) {
				if (!isVisited[j] && adjMatrix[current][j] != 0
						&& minDistance[j] > min + adjMatrix[current][j]) {
					minDistance[j] = min+adjMatrix[current][j];
				}
			}
		}
		
		System.out.println(minDistance[end] != Integer.MAX_VALUE ? minDistance[end] : -1);
		
		
		
	}
}
