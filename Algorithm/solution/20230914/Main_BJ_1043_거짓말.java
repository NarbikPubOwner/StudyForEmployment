import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 유니온-파인드 문제
 * 1. 	만약 파티에 1명이라도 진실을 아는 사람이 있다면 그 파티의 모든 사람들은 진실을 아는 사람이 된다
 *  	즉 주어진 모든 파티의 모든 인원들에 대해 Union을 하면 진실을 아는 사람그룹과 아닌 그룹으로 나뉘게 된다.
 *  
 * 2.	그렇게 한차례 Union을 한 이후엔 다시 파티를 돌면서 그 파티의 첫번째 사람과, 진실을 아는 사람의 find를 비교 했을 때 
 * 		서로 같다면 해당 파티는 진실만을 말해야 하고, 아니라면 과장을 해도 된다.
 * 
 */
public class Main_BJ_1043_거짓말 {
	static int[] p;//자신의 최상위 부모를 저장하는 배열

	//유니온 파인드 P배열 기본 초기화
	public static void make() {
		for (int i = 1; i < p.length; i++) {//사용의 편의를 위해 N+1로 크기를 선언하였기 때문에 i = 1부터 시작한다.
			p[i] = i;
		}
	}

	//자신의 최상위 부모를 찾아 반환한다, 이 과정 도중 자신의 최상위 부모가 갱신되어 있지 않다면 갱신한다
	public static int find(int a) {
		if (a == p[a]) return p[a];
		return p[a] = find(p[a]);//갱신부분
	}

	//아직 연결되지 않은 정점들을 연결한다
	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return false;
		p[rootB] = rootA;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int cnt = 0;
		int trueP = -1;//진실을 말하는 사람 인덱스를 저장
		int N = Integer.parseInt(st.nextToken());//사람의 수
		int M = Integer.parseInt(st.nextToken());//파티의 수
		int[] partyFirstMember = new int[M];//각 파티의 첫 멤버
		p = new int[N + 1];//1번째 인덱스부터 사용하기위해 N+1로 선언
		make();//P배열 기본 초기화
		
		st = new StringTokenizer(br.readLine(), " ");//기본적으로 진실을 아는 사람들 관련 입력 받기
		int tLength = Integer.parseInt(st.nextToken());//진실을 아는 사람들의 인원수

		if (tLength == 0) {//만약 진실을 아는 사람이 없다면 무조건 파티의 개수가 정답이다
			System.out.println(M);
		} else {
			trueP = Integer.parseInt(st.nextToken());// 진실을 아는 사람의 인덱스(어차피 유니온 파인드로 인해 연결된 모든 정점은 부모가 같기 때문에 제일 처음 만난 사람의 인덱스를 저장)
			
			// 진실을 아는 사람들을 모두 연결
			for (int i = 1; i < tLength; i++) {
				int b = Integer.parseInt(st.nextToken());
				union(trueP, b);
			}

			// 파티에 있는 사람들끼리 연결
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int partyLength = Integer.parseInt(st.nextToken());
				int first = Integer.parseInt(st.nextToken());
				partyFirstMember[i] = first;
				for (int j = 1; j < partyLength; j++) {
					int b = Integer.parseInt(st.nextToken());
					union(first, b);
				}
			}

			// 각 파티의 첫번째 멤버의 루트와 진실을 아는사람의 루트가 같지 않다면, 그 파티는 과장이 가능한 파티이므로 카운트 ++
			for (int i = 0; i < partyFirstMember.length; i++) {
				if (find(partyFirstMember[i]) != find(trueP)) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}

	}
}
