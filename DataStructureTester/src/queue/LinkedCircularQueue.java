package queue;

import list.CircularlyLinkedList;
import list.List;

public class LinkedCircularQueue<E> implements CircularQueue<E> {

	private CircularlyLinkedList<E> list = new CircularlyLinkedList<>();
	
	public LinkedCircularQueue() { }
	
	// --------------------------------------
	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		this.list.addLast(e);
	}

	@Override
	public E dequeue() {
		return this.list.removeFirst();
	}

	@Override
	public E first() {
		return this.list.first();
	}

	@Override
	public void rotate() {
		this.list.rotate();
		
	}
	
	public String toString() { 
		return this.list.toString(); 
	}

}
