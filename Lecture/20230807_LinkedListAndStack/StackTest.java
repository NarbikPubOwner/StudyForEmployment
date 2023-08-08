
public class StackTest {
	public static void main(String[] args) {
		IStack<String> stack = new LinkedListStack<String>();
		System.out.println(stack.size());
		System.out.println(stack.isEmpty());
		
		stack.push("서다찬");
		stack.push("전은희");
		stack.push("유현지");
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
			
		}
	}
}
