import java.util.EmptyStackException;

public class LinkedListStack<E> implements IStack<E> {// 제네릭을 구현하면 제네릭타입 추가해야함ㄴ

	private Node<E> top;
//	private int size;
	@Override
	public void push(E e) {
		top = new Node<>(e, top);
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		Node<E> tempNode = top;
		top = top.getLink();// popNode.getLink();
		tempNode.setLink(null);
		return tempNode.getData();
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		return top.getData();
	}

	@Override
	public int size() {
		int size = 0;
		for (Node<E> temp = top; temp != null; temp = temp.getLink()) {
			size++;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

}
