import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 최소스패닝트리 == MST == 최소신장트리
 * 개씹악질문제
 * 크루스칼로 구현
 */
public class Solution_D4_최소스패닝트리 {
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	static void makeSet(int[] p) {
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
	}

	static int findSet(int a) {
		if (p[a] == a) return a;
		return p[a] = findSet(p[a]);
	}

	static boolean unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		p[bRoot] = aRoot;
		return true;
	}
	static Edge[] edgeArr;
	static int p[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			p = new int[V];
			edgeArr = new Edge[E];
			makeSet(p);

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				edgeArr[i] = new Edge(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,
						Integer.parseInt(st.nextToken()));
			}

			long result = 0;
			int cnt = 0;
			
			Arrays.sort(edgeArr);
			
			for (Edge edge : edgeArr) {
				if (unionSet(edge.start, edge.end)) {
					result += edge.weight;
					if (++cnt == V - 1)
						break;
				}
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}

//2
//7 11
//0 1 3
//0 2 17
//0 3 6
//1 3 5
//1 6 12
//2 4 10
//2 5 8
//3 4 9
//4 5 4
//4 6 2
//5 6 14
//5 10
//0 1 5
//0 2 10
//0 3 8
//0 4 7
//1 2 5
//1 3 3
//1 4 6
//2 3 1
//2 4 3
//3 4 1
