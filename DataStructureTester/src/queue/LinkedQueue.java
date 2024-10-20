package queue;

import list.SinglyLinkedList;
import list.List;


public class LinkedQueue<E> implements Queue<E>  {

	private List<E> list = new SinglyLinkedList<>(); // empty list
	
	public LinkedQueue() { } // empty constructor 
	
    // on fait le pattern matching avec le SinglyLinkedList, quasiment parfait :) 
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

	public String toString() {
		return this.list.toString();
	}
}
