import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 순열 DFS타면 될듯 -> 시간초과
 * 그리디하게 -> 오름차순 정렬이후 합 구하기
 */
public class Main_BJ_11399_ATM {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//입력받기 위한 버퍼 생성
		N = Integer.parseInt(br.readLine());//숫자 개수 입력
		arr = new int[N];//숫자 개수에 따라 입력을 저장할 배열 생성
		StringTokenizer st = new StringTokenizer(br.readLine()," ");//입력받을 숫자들 입력받음
        //입력받은 숫자들 배열에 저장
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        //내림차순 정렬
		Arrays.sort(arr);
        /*
        *값 구하기
        */
		int[] value = new int[N];
		value[0] = arr[0];
		for (int i = 1; i < N; i++) {
			value[i] = value[i-1] + arr[i];
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += value[i];
		}
		System.out.println(sum);
	}

}
