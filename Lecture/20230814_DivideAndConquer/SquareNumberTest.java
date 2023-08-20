import java.util.Scanner;

public class SquareNumberTest {

	static int X,N;
	static int count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		int N = sc.nextInt();
		
		count = 0;
		System.out.println(exp2(X,N) + " " + count);
		
		count = 0;
		System.out.println(exp1(X,N) + " " + count);
	}

	//분할 정복 미적용
	static long exp1(int x, int n) {
		if (n==1) return x;
		count++;
		return x*exp1(x,n-1);
	}
	
	//분할 정복 적용
	static long exp2(int x, int n) {
		if (n==1) return x;
		count++;
		long y = exp2(x,n/2);
		if (n%2 == 0) return y*y;
		else return y*y*x;
		
	}
}
