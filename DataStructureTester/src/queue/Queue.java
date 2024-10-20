package queue;

public interface Queue<E> {
    public int     size();         // return the number of elements in the queue
    public boolean isEmpty();      // return true if the queue is empty, false otherwise
    public void    enqueue( E e ); // insert element e at the end of the queue
    public E       dequeue();      // remove and return the first element of the queue, null if empty
    public E       first();        // return the first element of the queue, null if empty
}
