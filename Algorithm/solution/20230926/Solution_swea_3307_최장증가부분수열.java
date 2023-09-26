import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 최장증가부분수열
 * 최장증가부분수열의 값을 구하는게 아니라 길이를 구하는 것이다
 * 각 값의 기본 길이는 모두 1이기 떄문에 1로 초기화를 하고 시작한다
 * 
 * 자신의 앞의 값들을 보아서, 앞의 값들이 자신보다 작은 숫자라면, 그 숫자의 최장길이 + 1을 자신의 최장 길이로 한다, 이떄 자신의 기존 최장길이보다 길 경우에만 반영한다
 * dp
 */
public class Solution_swea_3307_최장증가부분수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] arr = new int[n];
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] arrLength = new int[n];
			Arrays.fill(arrLength, 1);
			
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (arr[j] < arr[i]) {
						arrLength[i] = Math.max(arrLength[i], arrLength[j]+1);
					}
				}
			}
			
			int result = 0;
			for (int i = 0; i < n; i++) {
				result = Math.max(arrLength[i], result);
			}
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
