import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//순열, 백트래킹 문제
//보고 느끼십쇼
public class BJ_15649_N과M {
	
	static int[] arr;
	static boolean[] isSelected;
	static int N;
	static int M;
	static void recursive(int m) {//n은 선택가능한 수와 관련된 정수, 출력 배열에 대한 인덱스
		if (m == M) {//순열이 꽉차면 출력
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < N; i++) {//1~N을 입력
			if (isSelected[i] == true) continue;//이전 재귀에서 입력한 값이라면 건너뜀 - 중복방지
			arr[m] = i+1;
			isSelected[i] = true;
			recursive(m+1);
			isSelected[i] = false;	
		}
	}	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M =  Integer.parseInt(s[1]);
		
		arr = new int [M];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			isSelected[i] = false;
		}
		recursive(0);
		
	}
}
