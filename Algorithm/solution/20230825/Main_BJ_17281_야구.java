import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17281_야구 {

	static boolean[] isVisited;
	static int[] output = new int[9];
	static int[][] arr;
	static int N;
	static int result = 0;
	static void perm(int cnt) {
		if (cnt == 9) {
			//output에는 각 번호의 선수들이 몇번 타자로 서는지에 대한 순열의 정보가 담겨져 있다
			//그 정보를 기반으로 실제 이닝에서 1번~9번타석에 몇번 선수가 서는지에 대해서 다시 저장한다
			/*
			 * 순열로 뽑아낸 것은 ~번 선수가 ~번 타석에 선다는 것이고
			 * 아래의 야구 로직을 사용할려면 ~번 타석에 ~번 선수가 있다는 정보가 필요하기 때문에
			 */
			int[] hitOrder = new int[9];
			for (int i = 0; i < 9; i++) {
				hitOrder[output[i]] = i;
			}
			
			int score = 0;
			int order = 0;
			for (int i = 0; i < N; i++) {
				int outCnt = 0;
				int[] base = new int [3];
				while (outCnt != 3) {
					if (arr[i][hitOrder[order]] == 0) {
						outCnt++;
						order = (order+1)%9;
						continue;
					}
					else if(arr[i][hitOrder[order]] == 1) {
						for (int j = 2; j > -1; j--) {
							if (base[j] == 1) {
								if (j+1 >= 3) {
									score++;
								}
								else {
									base[j+1] = 1;
								}
								base[j] = 0;
							}
						}
						base[0] = 1;
					}
					else if(arr[i][hitOrder[order]] == 2) {
						for (int j = 2; j > -1; j--) {
							if (base[j] == 1) {
								if (j+2 >= 3) {
									score++;
								}
								else {
									base[j+2] = 1;
								}
								base[j] = 0;
							}
						}
						base[1] = 1;
					}
					else if(arr[i][hitOrder[order]] == 3) {
						for (int j = 2; j > -1; j--) {
							if (base[j] == 1) {
								score++;
								base[j] = 0;
							}
						}
						base[2] = 1;
					}
					else if(arr[i][hitOrder[order]] == 4) {
						for (int j = 2; j > -1; j--) {
							if (base[j] == 1) {
								score++;
								base[j] = 0;
							}
						}
						score++;
					}
					order = (order+1)%9;
				}
			}
			result = Math.max(result, score);
			return;
		}
		
		for (int i = 0; i < isVisited.length; i++) {
			if (i == 3) continue;//4번타자는 이미 정해져있으니 패스
			if (isVisited[i]) continue;//순열이기 때문에 방문한 선수도 패스
			isVisited[i] = true;
			output[cnt] = i;
			perm(cnt+1);
			isVisited[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isVisited = new boolean[9];
		output[0] = 3;//1번 선수가 무조건 4번타자라 했으니 그것을 설정
		arr = new int[N][9];//n번이닝의 9번 타자까지의 타격 정보 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(1);//순열
		System.out.println(result);
	}

}
