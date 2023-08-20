import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_17135_캐슬디펜스 {
	// 일단은 궁수 임의 배치 조합을 배출한다
	// 배출한 조합에 따라 적을 처리하고, 카운팅하여 처리
	// 가까운 적이 여럿일경우 좌측부터 공격하니 좌측부터 검색

	static int N;
	static int M;
	static int D;
	static int[][] Enemy;
	static int[] bowManCol = new int[3];
	static int result = 0;
	static List<int[]> list;

	//궁수 배치 조합 도출
	static void recursive(int cnt, int start) {
		if (cnt == bowManCol.length) {
			result = Math.max(result, getKillCount());//도출된 궁수배치에서의 킬카운트와 현재 결과값을 비교하여 결과를 저장
			return;
		}

		for (int i = start; i < M; i++) {
			bowManCol[cnt] = i;
			recursive(cnt + 1, i + 1);
		}
	}

	static int getKillCount() {
		int[][] tempEnemy = new int[N][M];
		for (int i = 0; i < tempEnemy.length; i++) {
			tempEnemy[i] = Enemy[i].clone();
		}
		int count = 0;
		for (int n = 0; n < tempEnemy.length; n++) {
			list = new ArrayList<int[]>();
			for (int c = 0; c < bowManCol.length; c++) {
				int targetX = N;
				int targetY = M;
				int distance = Integer.MAX_VALUE;
				for (int i = tempEnemy.length-1; i > -1; i--) {//아래부터 검색해야 하기 때문에
					for (int j = 0; j < tempEnemy[i].length; j++) {
						// 타겟 검색, 거리가 가장 작은 경우로, 거리가 같다면 j값이 적은 것으로 선택
						int tempDistance = Math.abs(N - i) + Math.abs(bowManCol[c] - j);
						if (tempEnemy[i][j] == 1 && tempDistance <= D) {//적이 있고, 사정거리 안이라면
							if (tempDistance < distance) {//기존 결과보다 더 가깝다면
								distance = tempDistance;
								targetX = i;
								targetY = j;
							} else if (tempDistance == distance && j < targetY) {//거리가 같다면 왼쪽에 있는 것을 선택
								targetX = i;
								targetY = j;
							}
						}
					}
				}
				if (targetX != N && targetY != M) {// 타겟을 찾았다면 공격 리스트에 추가, 찾지 못했다면 아무짓도 하지 않음
					list.add(new int[] { targetX, targetY });
				}
			}
			// 기록된 xy들의 값을 0으로 만듬, 0으로 만드는데 성공하면 카운터 증가. 같은 적을 공격 할 경우 카운트를 두번 올리면 안되기 때문
			for (int i = 0; i < list.size(); i++) {
				int[] tempXY = list.get(i);
				if (tempEnemy[tempXY[0]][tempXY[1]] != 0) {
					tempEnemy[tempXY[0]][tempXY[1]] = 0;
					count++;
				}
			}
			// 행렬 아래로 전체 이동
			for (int i = tempEnemy.length-1; i > 0; i--) {
				tempEnemy[i] = tempEnemy[i-1].clone();
			}
			tempEnemy[0] = new int[M];
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		Enemy = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				Enemy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		recursive(0, 0);
		System.out.println(result);
	}

}
