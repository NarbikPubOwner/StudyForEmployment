import java.util.Stack;

public class SSAFY_스택 {
	// 스택을 활용한 계산기 - 후위표기법
	static void stackCalculator(String s) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c <= 57 && c >= 48) {
				// 스택에 푸쉬
				stack.push(c - 48);
			} else {
				// 연산자 4개에 대한 것을 구현
				int value1 = stack.pop();
				int value2 = stack.pop();
				int result = 0;
				switch (c) {
				case '+':
					result = value2 + value1;
					break;
				case '-':
					result = value2 - value1;
					break;
				case '*':
					result = value2 * value1;
					break;
				case '/':
					result = value2 / value1;
					break;
				default:
					break;
				}
				stack.push(result);
			}
		}
		System.out.println(stack.pop());
	}

	// 스택을 활용한 페이지 이동 VBF 구현
	static void statckBrowser() {
		Stack<String> UNDO = new Stack<>();// 방문시 undo에 push
		String CUR = "URL";
		Stack<String> REDO = new Stack<>();
		String[] command = new String[] { "URL1", "URL2", "B", "URL", "F", "B", "F", "B", "B", "B" };
		System.out.println(CUR);
		for (String s : command) {
			switch (s) {
			case "B":
				REDO.add(CUR);
				CUR = UNDO.pop();
				break;
			case "F":
				UNDO.add(CUR);
				CUR = REDO.pop();
				break;
			//V
			default:
				UNDO.add(CUR);
				CUR = s;
				break;
			}
			System.out.println(CUR);
		}

		// V를 하면 VALUE -> CUR -> UNDO에 넣는다
		// B를 하면 UNDO -> CUR -> REDO
		// F를 하면 REDO -> CUR -> UNDO
		// Q?

	}

	public static void main(String[] args) {
		stackCalculator("6528-*2/+");
		statckBrowser();

	}
}
