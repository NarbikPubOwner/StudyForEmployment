import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_6808_규영이와인영이의카드게임 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			sb.append(t);
			st = new StringTokenizer(br.readLine(), " ");
			int[] PlayerACard = new int[9];
			int[] PlayerBCard = new int[9];
			for (int i = 0; i < 9; i++) {
				PlayerACard[i] = Integer.parseInt(st.nextToken());
			}
			
			/*
			 * A가 가지고 있는 카드와 다른 카드로 B의 카드를 구성
			 */
			int k = 0;
			for (int i = 1; i <= 18; i++) {
				boolean isInFlag = false;
				for (int j = 0; j < 9; j++) {
					if (i == PlayerACard[j]) {
						isInFlag = true;
						break;
					}
				}
				if (!isInFlag) {
					PlayerBCard[k] = i;
					k++;
				}
			}
			Arrays.sort(PlayerBCard);//np사용을 위한 오름차순 정렬
			int win = 0;
			int lose = 0;
			do {
				int APoint = 0;
				int BPoint = 0;
				for (int j = 0; j < 9; j++) {
					if (PlayerACard[j] > PlayerBCard[j]) APoint+= PlayerACard[j] + PlayerBCard[j];
					else if(PlayerACard[j] < PlayerBCard[j]) BPoint+= PlayerACard[j] + PlayerBCard[j];
					
				}
				
				if (APoint > BPoint) win++;
				else if(BPoint > APoint) lose++;
			} while (np(PlayerBCard));
			System.out.println("#" + t + " " + win + " " + lose);
		}

	}
	/*
	 * Next Permutation
	 */
	static boolean np(int[] p) {
		int N = p.length;
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i]) --i;

		if (i == 0) {
			return false;
		}
		int j = N - 1;
		while (p[i - 1] >= p[j]) --j;

		swap(p, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(p, i++, k--);
		}
		return true;
	}
	
	static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
