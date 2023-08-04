import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 주사위던지기 {
	static int[] arr;
	static boolean[] isSelected;
	static int M;
	
	//중복 순열
	static void diceRoll1(int m) {
		if (m == M) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		for (int i = 1; i <= 6; i++) {
			arr[m] = i;
			diceRoll1(m+1);
		}
	}
	
	//순열
	static void diceRoll2(int m) {
		if (m == M) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		for (int i = 1; i < 7; i++) {
			if (isSelected[i]) continue;
			arr[m] = i;
			isSelected[i] = true;
			diceRoll2(m+1);
			isSelected[i] = false;
		}
	}
	
	//중복 조합
	static void diceRoll3(int m, int start) {
		if (m == M) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		for (int i = start; i < 7; i++) {
			arr[m] = i;
			diceRoll3(m+1, i);
		}
	}
	//조합
	static void diceRoll4(int m, int start) {
		if (m == M) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		for (int i = start; i < 7; i++) {
			arr[m] = i;
			diceRoll4(m+1, i+1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());//던지기 모드
		M = Integer.parseInt(br.readLine());//던지는 횟수
		arr = new int[M];
		isSelected = new boolean[7];
		for (int i = 0; i < isSelected.length; i++) {
			isSelected[i] = false;
		}
		
		switch (n) {
		case 1:
			diceRoll1(0);
			break;

		case 2:
			diceRoll2(0);
			break;
		case 3:
			diceRoll3(0,1);
			break;
		case 4:
			diceRoll4(0,1);
			break;
		}
	}
}
