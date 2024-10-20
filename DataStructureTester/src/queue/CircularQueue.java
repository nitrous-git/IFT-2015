package queue;

public interface CircularQueue<E> extends Queue<E>{
	// rotate the front element of the queue to the back of the queue
	void rotate();
}
