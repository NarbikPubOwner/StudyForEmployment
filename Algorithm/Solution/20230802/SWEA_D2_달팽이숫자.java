import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
class SWEA_D2_달팽이숫자 {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        int [] dx = new int[] {0,1,0,-1};
        int [] dy = new int[] {1,0,-1,0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = 0;
        int[][] arr;
        int direction = 0;
        int x;
        int y;
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];//기본값 0 
            x = 0;
            y = 0;
            arr[0][0] = 1;
            direction = 0;
            for (int i = 1; i <=N*N; i++) {
                int last = 0;
                while (last != 3) {
                    if (x+dx[direction]>-1 && x+dx[direction]<N
                            && y+dy[direction]>-1 && y+dy[direction]<N
                            && arr[x+dx[direction]][y+dy[direction]] == 0) {//배열 인덱스를 벗어나지 않고
                        x += dx[direction];
                        y += dy[direction];
                        arr[x][y] = i+1;
                        break;
                    }
                    else {
                        if (direction == 3) {
                            direction = 0;
                        }
                        else {
                            direction++;
                        }
                        last++;
                    }
                }
            }
            StringBuilder sb = new StringBuilder("#");
            sb.append(t+1);
            sb.append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }
}