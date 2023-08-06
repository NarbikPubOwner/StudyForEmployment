import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_괄호짝짓기_D4 {
//	괄호의 짝을 검사하는 방법
//	1. 닫힌 괄호를 만나면 스택을 PEEK한다
//	2. 서로 맞는 괄호라면 POP한다
//	3. 다른 괄호라면 뒤를 볼 필요 없이 실패
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			int result = 1;
			int length = Integer.parseInt(br.readLine());
			char[] input = br.readLine().toCharArray();
			if (length % 2 == 0) {
				Stack<Character> stack = new Stack<Character>();
				for (int i = 0; i < input.length; i++) {
					switch (input[i]) {
					case ')':
						if (stack.peek() == '(') {
							stack.pop();
						} else
							result = 0;
						break;
					case '}':
						if (stack.peek() == '{') {
							stack.pop();
						}
						break;
					case ']':
						if (stack.peek() == '[') {
							stack.pop();
						} else
							result = 0;
						break;
					case '>':
						if (stack.peek() == '<') {
							stack.pop();
						} else
							result = 0;
						break;
					default:
						stack.push(input[i]);
						break;
					}
				}
				if (!stack.empty())
					result = 0;
			} else
				result = 0;
			System.out.println("#" + (t) + " " + result);
		}
	}
}