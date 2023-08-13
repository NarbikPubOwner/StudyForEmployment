import java.util.Arrays;
import java.util.Scanner;

public class combinationNPTest {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] input = new int[N];
		
		for (int i = 0; i < input.length; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);//오름차순 정렬
		do {
			System.out.println(Arrays.toString(input));
		} while(np(input));
	}
	
	static boolean np(int[] p) { // p : 다음 순열을 원하는 기존 순열의 배열
//		1. 꼭대기 찾기(꼭대기 앞뒤 모두 꼭대기보다 작은 수) → 꼭대기를 못 찾으면 리턴(ex4321)
		int N = p.length;//뽑아올 배열이 매번 달라지므로
		int i = N-1;//뒤부터 탐색
		while (i>0 && p[i-1] >= p[i]) --i;
		
		if (i==0) {//맨앞자리수가 가장 크기 때문에 가장 큰 순열의 형태이다.
			return false;
		}
//		2. 꼭대기 직전 위치의 수에 가져다 놓을 한 단계 큰 수 뒤쪽에서 찾기
		int j = N-1;
		while (p[i-1] >= p[j]) --j;
		
//		3. 꼭대기 직전 위치와 찾은 수를 교환 
		swap(p, i-1,j);
//		4. 변화를 준 수는 고정, 꼭대기 부터 → 맨 뒤까지 오름차순 정렬
		
		int k = N-1;//뒤에서부터
		while (i<k) {
			swap(p, i++, k--);
		}
		return true;
	}
	
	static void swap(int[] p, int a, int b) {
		int temp = p[a];
		p[a] = p[b];
		p[b] = temp;
	}
}
