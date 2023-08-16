import java.util.Scanner;

public class MakeSpaceTest {

	
	static int spaces[][];
	static int white, green;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		spaces = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				spaces[i][j] = sc.nextInt();
			}
		}
		
		makeSpcae(0, 0, N);
		System.out.println(white);
		System.out.println(green);
	}
	
	static void makeSpcae(int sr, int sc, int size) {
		int sum = 0;
		for (int r = sr; r < sr+size; r++) {
			for (int c = sc; c < sc+size; c++) {
				sum += spaces[r][c];
			}
		}
		
		if (sum == 0) {
			white++;
		}
		else if(sum == size*size) {
			green++;
		}else {
			int half = size/2;
			makeSpcae(sr, sc, half);
			makeSpcae(sr, sc+half, half);
			makeSpcae(sr+half, sc, half);
			makeSpcae(sr+half, sc+half, half);
		}
	}
}

//8
//1 1 0 0 0 0 1 1
//1 1 0 0 0 0 1 1
//0 0 0 0 1 1 0 0
//0 0 0 0 1 1 0 0
//1 0 0 0 1 1 1 1 
//0 1 0 0 1 1 1 1
//0 0 1 1 1 1 1 1
//0 0 1 1 1 1 1 1
//9
//7
