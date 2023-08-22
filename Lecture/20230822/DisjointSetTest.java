import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.sun.xml.internal.ws.api.pipe.Fiber;

/*
 * 트리 표현 서로소 집합과 다른 서로소 집합은 내부 동작이 약간다르다
 * 트리 표현은 Union시 합쳐질 노드의 헤드의 대표자가 합칠노드의 대표자로 바뀐다
 * 배열을 이용한다
 * 트리기반 서로소 집합은 find를 할 경우 루트를 찾기 위해 최악의 경우 N-1번을 반복한다 -> Rank를 이용한 Union or Path compresstion
 * Rank를 이용한 Union -> Union에서 처리 / a,b유니온시 b가 랭크가 높은 경우가 계속해서 나오면 기존 비최적화 처럼 깊어질 수 있다
 * Path complresstion -> findSet에서 처리 
 * 
 */


public class DisjointSetTest {
	static int N;
	static int[] parrent;
	static void makeSet(int x) {
		parrent[x] = x;
	}
	static int findSet(int x) {
		if (x == parrent[x]) return x;
		parrent[x] = findSet(parrent[x]);
		return parrent[x];
		
//		return findSet(parrent[x]);
		
	}
	static boolean unionSet(int x, int y) {
		int xRoot = findSet(x);
		int yRoot = findSet(y);
		if (xRoot == yRoot) return false;
		parrent[yRoot] = xRoot;
		return true;
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parrent = new int[N];
		
		for (int i = 0; i < N; i++) {
			makeSet(i);
		}
		
		System.out.println(Arrays.toString(parrent));
		unionSet(0, 1);
		System.out.println(Arrays.toString(parrent));
		unionSet(2, 1);
		System.out.println(Arrays.toString(parrent));
		unionSet(3, 2);
		System.out.println(Arrays.toString(parrent));
		unionSet(4, 3);
		System.out.println(Arrays.toString(parrent));
		
		System.out.println("find");
		
		System.out.println(findSet(4));
		System.out.println(findSet(3));
		System.out.println(findSet(2));
		System.out.println(findSet(0));
		System.out.println(findSet(1));
	}

}
