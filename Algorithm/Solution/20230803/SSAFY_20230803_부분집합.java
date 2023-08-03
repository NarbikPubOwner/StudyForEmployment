package javapractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SSAFY_20230803_부분집합 {
	static boolean[] isSelected = null;
	static int[] input = null;
	static int sumResult = 0;
	static void RecrusiveBasic(int cnt) {
		if (cnt == isSelected.length) {
			for (int i = 0; i < isSelected.length; i++) {
				if (isSelected[i]) {
					System.out.print(input[i] + " ");
				}
			}
			System.out.println();
			return;
		}
		isSelected[cnt] = true;
		RecrusiveBasic(cnt+1);
		isSelected[cnt] = false;
		RecrusiveBasic(cnt+1);
	}
	
	static void RecursiveSum(int cnt) {
		if (cnt == isSelected.length) {
			int sum = 0;
			for (int i = 0; i < isSelected.length; i++) {
				if (isSelected[i]) {
					sum += input[i];
				}
			}
			if (sum == sumResult) {
				for (int i = 0; i < isSelected.length; i++) {
					if (isSelected[i]) {
						System.out.print(input[i] + " ");
					}
				}
				System.out.println();
			}
			return;
		}
		isSelected[cnt] = true;
		RecursiveSum(cnt+1);
		isSelected[cnt] = false;
		RecursiveSum(cnt+1);
	}
	
	static void RecursiveBinary() {
		System.out.println("바이너리");
		for (int i = 0; i < (1<<input.length); i++) {
			for (int j = 0; j < input.length; j++) {
				if ((i & 1<<j) != 0) {
					System.out.print(input[j] + " ");
				}
			}
			System.out.println();
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("부분 집합 - 예제에 활용할 Input값을 입력해주세요 : ");
		String[] s = br.readLine().split(" ");
		input = new int[s.length];
		isSelected = new boolean[s.length];
		for (int i = 0; i < s.length; i++) {
			input[i] = Integer.parseInt(s[i]);
		}

		RecrusiveBasic(0);
		RecursiveBinary();
		System.out.println("부분 집합 - 예제에 활용할 sumResult 값을 입력해주세요");
		sumResult = Integer.parseInt(br.readLine());
		RecursiveSum(0);
		
		
		//-7 -3 -2 5 8 / 0
		//5 6 10 11 16 / 21
	}
}
