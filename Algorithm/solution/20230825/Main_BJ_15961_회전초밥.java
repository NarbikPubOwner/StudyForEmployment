import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 슬라이딩 윈도우
 * 구현
 */
public class Main_BJ_15961_회전초밥 {

	static int[] isVisited;
	static int[] input;
	static int N, k, d, c;

	static int sliding() {

		int total = 0;
		int max = 0;
		/*
		 * k개의 연속된 횟수만큼 미리 값을 처리합니다
		 */
		for (int i = 0; i < k; i++) {
			if (isVisited[input[i]] == 0)//방문한 적이 없다면 횟수를 증가시킵니다
				total++;
			isVisited[input[i]]++;//이전에 얼만큼 해당 초밥을 방문했는지에 대해서 기록합니다
		}
		/*
		 * 값을 받아올 때 쿠폰에 해당하는 초밥을 먹지 않았다면 값을 1 증가시켜 넣음
		 */
		if (max <= total) {
			if (isVisited[c] == 0)
				max = total + 1;
			else
				max = total;
		}
		for (int i = 1; i < N; i++) {

			//이전 초밥 --
			isVisited[input[i - 1]]--;
			if (isVisited[input[i - 1]] == 0)
				total--;

			//다음 초밥 ++
			if (isVisited[input[(i + k - 1) % N]] == 0) {
				total++;
			}
			isVisited[input[(i + k - 1) % N]]++;

			/*
			 * 값을 받아올 때 쿠폰에 해당하는 초밥을 먹지 않았다면 값을 1 증가시켜 넣음
			 */
			if (max <= total) {
				if (isVisited[c] == 0)
					max = total + 1;
				else
					max = total;
			}

		}
		return max;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());// 회전 초밥 벨트에 놓인 접시의 수
		d = Integer.parseInt(st.nextToken());// 초밥의 가짓 수
		k = Integer.parseInt(st.nextToken());// 연속해서 먹을 수 있는 접시의 수 (쿠폰으로 먹을 경우는 카운트하지 않는다, 그리고 초밥으로 먹더라도 먹은 종류에 추가한다)
		c = Integer.parseInt(st.nextToken());// 쿠폰 번호

		isVisited = new int[d + 1];

		input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(sliding());

	}

}


