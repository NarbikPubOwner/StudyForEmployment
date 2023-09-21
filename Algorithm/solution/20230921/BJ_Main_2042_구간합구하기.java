	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.StringTokenizer;
	
	/*
	 * 세그먼트 트리란 특정 구간의 결과를 구할 때 쓰인다(구간합, 구간곱, 최솟값, 최댓값)
	 * 세그먼트 트리는 완전 이진트리의 형태를 띈다
	 * 문제에 "입력으로 주어지는 모든 수는 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다."라는 말이 있기 때문에
	 * 값으로 활용되는 변수들은 long으로 선언해줘야 한다
	 */
	public class SegmentTree {
		static long[] tree;
		static long[] arr;
		static int treeSize;
		static int getTreeSize(int N) {
			/*
			 * 트리의 사이즈를 구하는 방법은 2가지
			 * 1. (h-1)^2 <= N <= h^2라는 이진트리의 성질이 존재하는데, 이를 이용해 N을 안다면 모든 항에 log2를 취해서 h를 구할 수 있다
			 * (h-1)^2 <= N <= h^2 -> h-1 <= log2(N) <= h
			 * h-1로 잡으면 인덱스 오류가 날 수 있기 때문에 h를 높이로 잡는다, 그리고 logA(B) = logB/logA이므로
			 * h = logN/log2가 된다. 아래는 이를 이용해 트리의 크기를 구하는 자바코드
			 * ceil을 통해 올림을 해준다
			 * int h = (int)Math.ceil(Math.log(N)/Math.log(2)) +1;
			 */
			
			/*
			 * 2. 다른 방법으로는, 노드가 N개라 할 때, 이진트리의 최대크기는 N과 가장 가까운 2의 배수에 *2를 한 값이다. ex)N=5일 경우 8*2 = 16이다
			 * 즉 n에 2를 곱하면 가장 가까운 2의 배수보다 무조건 커지고, 거기에 2를 더 곱하면 최종적인 크기를 넉넉하게 구할 수 있따
			 */
			return N*4;
		}
		
		/*
		 * 구간 합 트리 초기화
		 * start는 1, end는 N으로 고정(문제에서 인덱스 입력시 1부터 시작하기 때문에 사용 편의상 arr의 크기를 N+1로 잡기 때문)
		 * Node는 이진트리 사용의 편의상 1부터 시작한다(이진트리의 인덱스는 좌측 자식은 *2, 우측 자식은 *2+1이 되는데 값이 0일 경우 예외처리를 따로 해줘야 하기 때문이다)
		 * 파라미터 사용 예시 (1,N,1)
		 */
		static long init(int start, int end, int node) {
			/*
			 * 스타트와 엔드가 같은 경우는 리프노드에 도착한 경우이다.이 경우 해당 노드의 값은 arr[start]의 값과 같다(arr[end]도 된다)
			 */
			if (start == end) return tree[node] = arr[start];
			/*
			 * 이진트리의 탐색은 좌우 자식노드를 타겟으로 하는데, 좌측은 자신 노드 번호의 *2 우측은 *2+1이다
			 * 관계되는 배열 또한 갈라지는 시점을 기준으로 절반으로 나눠지기 때문에 중간값을 구해서 각각의 끝,시작으로 삼는다
			 */
			int mid = (start + end)/2;
			return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
		}
		
		/*
		 * 구간 합 트리 업데이트
		 * start와 end의 사이에 있다는 것은 해당 인덱스의 배열값을 포함하는 노드라는 의미이다
		 * 구간합의 특성상 하나의 노드를 바꾸면 해당 노드의 연관된 모든 상위노드의 값이 바뀌게된다
		 * 그렇기 때문에 관련된 모든 노드의 값을 기존값과의 차이만큼 더해준다
		 * 주의사항 - 업데이트를 시행하기 전에 idx에 해당하는 arr의 값도 차이를 반영시켜줘야 한다. 코드 순서상 지나치기 쉬운 부분이 때문에 명심해야 한다. 
		 */
		static void update(int start, int end, int idx, int node, long dif) {
			if (idx >= start && idx <= end) tree[node] += dif;
			else return;//사이에 존재하지 않는다면 이후에 진행하더라도 더 이상 인덱스가 연관되지 않는다
			if (start == end) return;//리프 노드에 도달했다면 끝낸다
			int mid = (start + end)/2;
			update(start, mid, idx, node*2, dif);
			update(mid+1, end, idx, node*2+1, dif);
		}
		/*
		 * 구간 합 구하기
		 * left와 right는 구하려는 구간의 시작과 끝을 의미한다
		 */
		static long getPrefixSum(int left, int right, int start, int end, int node) {
			if (left > end || start > right) return 0;//구하려는 구간인 left와 right의 범위를 벗어나는 경우 끝낸다
			if (left <= start && end <= right) return tree[node];//구하려는 구간의 안쪽에 존재한다면 리프노드까지 갈 필요없이 해당 노드의 값을 반환하면 된다, 리프 노드는 기본적으로 이 성질을 가진다
			int mid = (start + end)/2;
			return getPrefixSum(left, right, start, mid, node*2) + getPrefixSum(left, right, mid+1, end, node*2+1);
		}
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			
			int N = Integer.parseInt(st.nextToken());//배열의 크기
			int M = Integer.parseInt(st.nextToken());//업데이트 횟수
			int K = Integer.parseInt(st.nextToken());//구간합구하는 횟수
			
			arr = new long[N+1];//입력 특성상 편의를 위해 N+1로 선언
			treeSize = getTreeSize(N);
			tree = new long[treeSize+1];//이진 트리 특성상 편의를 위해 N+1로 선언
			
			
			for (int i = 1; i <= N; i++) {
				arr[i] = Long.parseLong(br.readLine());
			}
			
			init(1, N, 1);
			
			for (int i = 0; i < M+K; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				if (cmd == 1) {
					int b = Integer.parseInt(st.nextToken());//바꿀 숫자의 인덱스
					long c = Long.parseLong(st.nextToken());//바뀔 숫자
					long dif = c-arr[b];//이전값과의 차이
					arr[b] += dif;//차이 반영
					update(1, N, b, 1, dif);	
				}
				else {
					int b = Integer.parseInt(st.nextToken());//시작 구간 인덱스
					int c = Integer.parseInt(st.nextToken());//끝 구간 인덱스
					sb.append(getPrefixSum(b, c, 1, N, 1)).append("\n");
				}
			}
			System.out.println(sb);
		}
	
	}
