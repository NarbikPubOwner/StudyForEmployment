import java.util.Arrays;
import java.util.Scanner;

//무향 그래프 인접 행렬
public class AdjListTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		int [][] adjMatrix = new int[V][V];
		
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = 1;
		}
		
		for (int[] is : adjMatrix) {
			System.out.println(Arrays.toString(is));
		}
	}
}

/*
7
8
0 1
0 2	
1 3
1 4
2 4
3 5
4 5
5 6	
 */