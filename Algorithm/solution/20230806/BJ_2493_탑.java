package javapractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//양방향 가능
public class BJ_2493_탑 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Stack<Integer> valueStack = new Stack<>();
		Stack<Integer> indexStack = new Stack<>();
		StringBuilder sb = new StringBuilder("");

		valueStack.add(Integer.parseInt(st.nextToken()));
		indexStack.add(1);
		sb.append(0);
		for (int i = 2; i <= N; i++) {
			sb.append(" ");
			int value = Integer.parseInt(st.nextToken());
			while (!valueStack.isEmpty()) {
				if (valueStack.peek() > value) {
					sb.append(indexStack.peek());
					break;
				}
				valueStack.pop();
				indexStack.pop();
			}
			if (valueStack.isEmpty()) {
				sb.append(0);
			}
			valueStack.add(value);
			indexStack.add(i);

		}

		System.out.println(sb);
	}
}
