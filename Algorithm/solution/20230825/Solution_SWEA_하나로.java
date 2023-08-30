import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SWEA_하나로 {

	static int[] p;

	static void make() {
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
	}

	static int find(int a) {
		if (p[a] == a) return a;
		else return p[a] = find(p[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		long weight;

		public Edge(int start, int end, long weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 받은 정점을 기준으로 가중치를 부여한 간선리스트를 생성해야한다 - 크루스칼 사용을 위해서

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			int[][] vertexes = new int[N][2];
			for (int i = 0; i < vertexes.length; i++) {
				vertexes[i][0] = Integer.parseInt(st1.nextToken());
				vertexes[i][1] = Integer.parseInt(st2.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			Edge[] edgeList = new Edge[(N - 1) * N / 2];
			int tempLength = 0;
			for (int i = 0; i < vertexes.length; i++) {
				for (int j = i + 1; j < vertexes.length; j++) {
					long distX = Math.abs(vertexes[i][0] - vertexes[j][0]);
					long distY = Math.abs(vertexes[i][1] - vertexes[j][1]);
					edgeList[tempLength] = new Edge(i, j, distX * distX + distY * distY);//피타고라스...정점간 길이 구하기
					tempLength++;
				}
			}

			Arrays.sort(edgeList);
			long result = 0;
			int count = 0;
			p = new int[N];
			make();

			for (Edge edge : edgeList) {
				if (union(edge.start, edge.end)) {
					result += edge.weight;
					if (++count == N - 1) {
						break;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(Math.round(result*E)).append("\n");
		}
		System.out.println(sb);
	}
}
