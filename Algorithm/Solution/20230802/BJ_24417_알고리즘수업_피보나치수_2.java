package javapractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//멍청하게 길이만큼 배열선언 하지마세요
public class BJ_24417_알고리즘수업_피보나치수_2 {
    static int fibonachiCount = 0;

    static long fibonachi(int n) {
        if (n <= 2) {
            return 1;
        }

        long prev1 = 1;
        long prev2 = 1;
        long current = 0;

        for (int i = 3; i <= n; i++) {
            fibonachiCount++;
            current = (prev1 + prev2) % 1000000007;
            prev1 = prev2;
            prev2 = current;
        }

        return current;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(fibonachi(n) + " " + (fibonachiCount % 1000000007));
    }
}


//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class Main {
//	static long[] arr;
//	static int fibonachiCount = 0;
//	static long fibonachi(int n) {
//		arr[0] = 1;
//		arr[1] = 1;
//		
//		for (int i = 2; i < n; i++) {
//			fibonachiCount++;
//			arr[i] = arr[i-1] + arr[i-2];
//		}
//		return arr[n-1];
//	}
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int n = Integer.parseInt(br.readLine());
//		arr = new long[n];
//		System.out.println(fibonachi(n)%1000000007 + " " + fibonachiCount%1000000007);
//	//200000000	
//	//321520069
//	//1000000007
//	}
//}



