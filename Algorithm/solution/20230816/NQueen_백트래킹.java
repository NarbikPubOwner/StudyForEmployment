	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.Arrays;
	
	//방법 2 : 같은 행열에는 퀸을 놓지 않는 버전 -> i or j 가 같으면 x
	public class NQueen_백트래킹 {
		static int N;
		static int col[];
	
		static int count = 0;
	
		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
	
			col = new int[N + 1];//1행부터 사용
			setQueen(1);
			System.out.println(count);
		}
		
		static void setQueen(int row) {
			
			//가지치기
			if (!isAvailable(row-1)) return;
			//기저조건
			if (row > N) {
				count++;
				return;
			}
			//유도파트
			for (int c = 0; c < N; c++) {
				col[row] = c;
				setQueen(row+1);
			}
			
		}
		
		static boolean isAvailable(int row) {//row : 마지막으로 놓아진 퀸의 행
			for (int i = 1; i < row; i++) {
				if (col[i] == col[row] || row-i == Math.abs(col[row] - col[i])) {//대각선이 문제가 된다면 최대진행 행이랑 같은 행에 있다
					return false;
				}
			}
			return true;
		}
		
		//개선(강의 끝자락 부분 다시 보셈)
	}
