package stack;

import list.SinglyLinkedList;
import list.List;

/**
* 	LinkedStack is an implementation of the ADT/interface Stack using a SinglyLinkedList
*   A collection of elements inserted and removed using the last-in first-out policy.
*   All operations execute in O(1).
*/

public class LinkedStack<E> implements Stack<E> {

	private List<E> list = new SinglyLinkedList<>();
	
	// empty constructor
	public LinkedStack() { }
	
	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(E e) {
		this.list.addFirst(e);
	}

	@Override
	public E top() {
		return this.list.first();
	}

	@Override
	public E pop() {
		return this.list.removeFirst();
	}

    public String toString() {
    	return this.list.toString();
    }
}
