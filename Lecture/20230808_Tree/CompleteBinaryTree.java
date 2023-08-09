import java.awt.geom.CubicCurve2D;
import java.util.ArrayDeque;
import java.util.Queue;

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
	
	public static void main(String[] args) {
		CompleteBinaryTree<Character> cbt = new CompleteBinaryTree<>(15);
		for (int i = 0; i < 15; i++) {
			cbt.add((char)(65+i));
		}
		cbt.bfs();
	}
}
