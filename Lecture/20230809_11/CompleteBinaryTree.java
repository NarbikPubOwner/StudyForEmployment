import java.awt.geom.CubicCurve2D;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class CompleteBinaryTree<T> {
	private Object[] nodes;
	private int lastIndex=0; // 채워진 마지막 노드의 인덱스
	private final int SIZE; // 최대 노드의 개수

	public CompleteBinaryTree(int size) {
		SIZE = size;
		nodes = new Object[size+1];
	}
	
	boolean isEmpty() {
		return lastIndex == 0;
	}
	boolean isFull() {
		return lastIndex == SIZE;
	}
	
	boolean add(T value) {
		if (isFull()) return false;
		nodes[++lastIndex] = value;
		return true;
	}
	
	void bfs() {
		if (isEmpty()) return; 
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(1);
		
		while (!q.isEmpty()) {
			int target = q.poll();
			System.out.println(nodes[target]);
			if (target*2 <= lastIndex) q.offer(target*2);
			if(target*2 + 1 <= lastIndex) q.offer(target*2+1);
		}
	}
	
	void bfs2() {//해당 레벨의 너비를 알려줌
		if (isEmpty()) return; 
		//탐색 순서를 관리할 대기열 자료구조
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(1);//루트노드의 인덱스
		
		int breath = 0;
		while (!q.isEmpty()) {//탐색 대상이 있다면
			int size = q.size();
			while (--size>=0) {
				int target = q.poll();//탐색 대상 큐에서 꺼내기
				System.out.print(nodes[target] + "\t");
				//현재 탐색대상을 통해 ㅏㅁ색해야할 새로운 대상을 큐에 넣기
				if (target*2 <= lastIndex) q.offer(target*2);
				if(target*2 + 1 <= lastIndex) q.offer(target*2+1);
			}
			System.out.println();
			System.out.println("=========="+breath+"너비 탐색 완료");
			breath++;
		}
	}
	
	void bfs3() {
		if (isEmpty()) return; 
		Queue<int[]> q = new ArrayDeque<int[]>(); // int[] : {탐색노드의 인덱스, 너비}
		q.offer(new int[] {1,0}); // 루트노드의 인덱스
		
		while (!q.isEmpty()) {
			int[] info = q.poll();
			int target = info[0];
			System.out.println(nodes[target] + "//" + info[1]);
			if (target*2 <= lastIndex) q.offer(new int[] {target*2,info[1]+1});
			if(target*2 + 1 <= lastIndex) q.offer(new int[] {target*2+1,info[1]+1});
		}
	}
	
	void dsfPreOrder(int current) { //현재 노드를 전위순회로 탐색
		System.out.print(nodes[current]);//탐색 대상 방문처리
		//현재 탐색대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색하기
		if (current*2 <= lastIndex) dsfPreOrder(current*2);
		if(current*2 + 1 <= lastIndex) dsfPreOrder(current*2 + 1);
	}
	
	void dsfInOrder(int current) {
		//현재 탐색대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색하기
		if (current*2 <= lastIndex) dsfInOrder(current*2);
		System.out.print(nodes[current]);//탐색 대상 방문처리
		if(current*2 + 1 <= lastIndex) dsfInOrder(current*2 + 1);
	}
	
	void dsfPostOrder(int current) {
		//현재 탐색대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색하기
		if (current*2 <= lastIndex) dsfPostOrder(current*2);
		if(current*2 + 1 <= lastIndex) dsfPostOrder(current*2 + 1);
		System.out.print(nodes[current]);//탐색 대상 방문처리
	}
	
	void dfs() {
		if (isEmpty()) return; 
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		
		while (!stack.isEmpty()) {
			int target = stack.pop();
			System.out.print(nodes[target]);
			
			if (target*2 + 1 <= lastIndex) stack.push(target*2+1);
			if (target*2 <= lastIndex) stack.push(target*2);
			
			
		}
	}
	
	public static void main(String[] args) {
		CompleteBinaryTree<Character> cbt = new CompleteBinaryTree<>(15);
		for (int i = 0; i < 9; i++) {
			cbt.add((char)(65+i));
		}
		cbt.bfs();
		System.out.println();
		cbt.bfs2();
		System.out.println();
		cbt.bfs3();
		System.out.println();
		cbt.dsfPreOrder(1);
		System.out.println();
		cbt.dsfInOrder(1);
		System.out.println();
		cbt.dsfPostOrder(1);
		System.out.println();
		System.out.println();
		cbt.dfs();
	}
}
