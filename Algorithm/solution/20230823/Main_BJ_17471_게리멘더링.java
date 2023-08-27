import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.org.apache.regexp.internal.recompile;

/*
 * 선거 구역 경우의 수 구하는 방법
 * 1. 먼저 인접 구역들을 연결한다
 * 2. 이후 dfs를 타는데, 각 정점의 구역을 red, blue로 나누는 dfs를 탄다(-1, 1로 표현)
 * 
 * 적합한 방법인지 아닌지의 판별법
 * 1. count!=N (카운트가  N과 같으면 하나의 세력으로 가득 찬 것이기 때문)
 * 2. 순회 이후 count == 0 (0이 되지 않으면 인접하지 않아 중간에 끊긴 경우기 때문)
 */
public class Main_BJ_17471_게리멘더링 {
	static int[] weights;//각 정점의 구역 표시
	static int[][] adjMatrix;
	static int[] populations;//각 정점의 인구수
	static int N;
	static int result = Integer.MAX_VALUE;
	
	//weights에 표시된 구역이 가능한 구역인지 판별
	static boolean valid() {
		int redStart = -1;
		int blueStart = -1;
		int redCount = 0;
		int blueCount = 0;
		for (int i = 0; i < weights.length; i++) {
			if (weights[i] == -1) {
				redStart = i;
				redCount++;
			}
			else if(weights[i] == 1) {
				blueStart = i;
				blueCount++;
			}
		}
		if (redCount == N || blueCount == N) return false;
		
		//start의 인접을 돌면서 해당 인접이 weights상에서 type인지 확인한다, 확인되면 count --
		//bfs
		for (int k = -1; k <= 1; k+=2) {//값은 -1, 1 2가지 이기 때문에 이런식으로 포문 사용
			int type = k;//-1 = red
			Queue<Integer> queue = new ArrayDeque<Integer>();
			boolean[] isVisited = new boolean[N];
			if (type == -1) {
				queue.offer(redStart);
				redCount--;
				isVisited[redStart] = true;
			}
			else {
				queue.offer(blueStart);
				blueCount--;
				isVisited[blueStart] = true;
			}
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (int i = 0; i < N; i++) {
					if (adjMatrix[cur][i] == 1 && !isVisited[i] && weights[i] == type) {
						//다음 방문 정점을 정했으면 해당 위치로 이동 예정이라는 것이기 때문에 count--;
						if (type == -1) redCount--;
						else blueCount--;
						queue.offer(i);
						isVisited[i] = true;
					}
				}
			}
		}
		//count == 0 라는 것은 예상구역들이 전부 제대로 인접해있다는 이야기이기 때문에 true반환
		if (redCount == 0 && blueCount == 0) {
			return true;
		}
		return false;
	}
	//두 구역의 인구합의 차이를 리턴
	static int getDif() {
		int red = 0;
		int blue = 0;
		for (int i = 0; i < N; i++) {
			if (weights[i] == -1) red+=populations[i];
			else blue+=populations[i];
		}
		return Math.abs(red-blue);
	}
	
	static void dfs(int cnt, int start) {
		if (cnt == N) {
			if (valid()) result = Math.min(result, getDif());
			return;
		}
		for (int i = start; i < N; i++) {
			weights[cnt] = -1;
			dfs(cnt+1, i+1);
			weights[cnt] = 1;
			dfs(cnt+1, i+1);
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weights = new int[N];
		adjMatrix = new int[N][N];
		
		populations = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < populations.length; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			for (int t = 0; t < T; t++) {
				int j = Integer.parseInt(st.nextToken())-1;
				adjMatrix[i][j] = 1;
			}
		}
		dfs(0,0);
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else System.out.println(result);
		
	}

}
