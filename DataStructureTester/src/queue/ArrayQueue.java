package queue;

import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {
	
    // attributes
    public static final int CAPACITY = 16;      // default capacity
    protected E[] data;                         // array to store the elements
    protected int f = 0;                        // index for the front of the queue
    protected int size = 0;                     // current number of elements
    
    // constructors
    public ArrayQueue() { this( CAPACITY ); }   // construct queue with default capacity
   
    @SuppressWarnings("unchecked")
	public ArrayQueue( int capacity ) {         // construct queue with given capacity
    	this.data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
    }
	
	
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0; 
	}

	// insert element e at the end of the queue
	@Override
	public void enqueue(E e) throws IllegalStateException {
		if( this.size == this.data.length ) throw new IllegalStateException( "Full queue" );
		int avail = ( this.f + this.size ) % this.data.length;   // use modulo for circularity
		this.data[avail] = e;
		this.size++;
	}

	@Override
	public E dequeue() {
		if( this.isEmpty() ) return null;
		
		// on prend l'elem a l'index f
		E element = this.data[this.f];
		this.data[this.f] = null; // for garbage collection // on enleve le pointeur 
		
		// on incremente la valeur de f 
		this.f = ( this.f + 1 ) % this.data.length;
		
		this.size--; 
		return element;
	}

	@Override
	public E first() {
		return null;
	}

	public String toString() { return Arrays.toString( this.data ); }
}
