import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 사람의 총 개수 = 옷의 총 개수
 * 양 옆에 같은 옷을 입으면 안되며, 원형으로 모이기 때문에 첫 부분과 끝 부분은 같으면 안된다
 * 경우의 수를 그려보자 : 최대치가 20, 그리고 옷의 개수가 10개인 원소가 있다고 한다면, 딱 겹치지 않게 원형으로 세울 수 있다, 여기에서 11개가 되는 경우로 가게 되면 unhappy한 상태가 되어진다
 * 즉 N(총 옷의 개수)/2 보다 큰 경우에는 unhappy라고 할 수 있다
 * 이와 별개로 총 옷의 개수가 1인 경우에는 위의 로직에 따라선 unhappy지만 실제론 happy이기 때문에 해당 부분만 따로 처리한다
 */

/*
 *   일반적으로 경쟁적 프로그래밍(Competitive Programming) 대회, 이른바 알고리즘 대회에서는 종종 애드혹(ad-hoc) 문제가 출제된다. 
 *   일반적으로 애드혹 문제라고 하는 것은 해당 문제를 풀기 위해 잘 알려진 정교한(sophisticated) 알고리즘을 적용하지 않고 해결할 수 있는 유형의 문제를 일컫는다. 
 *   이러한 유형의 문제는 손으로 직접 해당 문제를 해결하기 위한 (해당 문제만을 위한) 아이디어를 찾아서 문제를 해결할 수 있다. 
 *   애드혹 문제들을 굳이 분류하자면 단순히 지시(instruction)를 따르면 되는 구현 유형이나 그리디 유형 알고리즘 혹은 수학 유형으로 분류할 수 있는 경우가 많다.
 *  다시 말해 정형화된 방법론이 아니라, 그 문제를 풀기 위한 창의적인 아이디어를 떠올려야 하는 경우에 애드혹 문제라고 한다
 */
public class Main_BJ_25186_INFP두람 {
	static long peopleSum = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			peopleSum += arr[i];
		}

		String s = "Happy";
		for (int i : arr) {
			if (peopleSum == 1) {
				break;
			}
			if (i > peopleSum / 2) {
				s = "Unhappy";
				break;
			}
		}
		System.out.println(s);
	}
}
