import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1931_회의실배정 {
	static class temp implements Comparable<temp>{
		int start;
		int end;
		public temp(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(temp o) {
			// TODO Auto-generated method stub
			if (end == o.end) {
				return start-o.start;
			}
			return end-o.end;
		}
		@Override
		public String toString() {
			return "temp [start=" + start + ", end=" + end + "]";
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		temp[] arr = new temp[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			arr[i] = new temp(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		
		int last = arr[0].end;
		int cnt = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].start >= last) {
				last = arr[i].end;
				System.out.println(arr[i].start + " " + arr[i].end);
				cnt++;
			}
		}
		System.out.println(cnt);
		
	}

}

//3
//1 4
//6 8
//5 8