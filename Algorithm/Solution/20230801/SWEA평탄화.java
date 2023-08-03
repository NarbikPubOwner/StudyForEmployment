import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA평탄화 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int n = 0; n < 10; n++) {
			int T = Integer.parseInt(br.readLine());
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s, " ");
			int[] ground = new int[st.countTokens()];
			int index = 0;
			while (st.hasMoreTokens()) {
				ground[index] = Integer.parseInt(st.nextToken());
				index++;
			}

			int maxIdx = 0;
			int minIdx = 0;
			int max = ground[maxIdx];
			int min = ground[minIdx];

			for (int i = 0; i < T; i++) {
				maxIdx = 0;
				minIdx = 0;
				max = ground[0];
				min = ground[0];
				for (int j = 0; j < ground.length; j++) {
					if (ground[j] > max) {
						max = ground[j];
						maxIdx = j;
					} else if (ground[j] < min) {
						min = ground[j];
						minIdx = j;
					}
				}
				ground[maxIdx]--;
				ground[minIdx]++;
			}

			maxIdx = 0;
			minIdx = 0;
			max = ground[0];
			min = ground[0];
			for (int j = 0; j < ground.length; j++) {
				if (ground[j] > max) {
					max = ground[j];
					maxIdx = j;
				} else if (ground[j] < min) {
					min = ground[j];
					minIdx = j;
				}
			}

			System.out.println("#" + (n+1) + " " + (ground[maxIdx] - ground[minIdx]));
		}
	}
}
