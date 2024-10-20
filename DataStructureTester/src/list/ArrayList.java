package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {

    public static final int CAPACITY = 1;  // default capacity
    private E[] data;                      // generic array used to store the elements
    private int size = 0;                  // current number of elements
	
    protected void checkIndex( int i, int n ) throws IndexOutOfBoundsException {
    	if( i < 0 || i >= n ) throw new IndexOutOfBoundsException( "index " + i + " out of bounds" );
    }
    
    protected void resize(int capacity) {
		E[] tmp = (E[]) new Object[capacity];
		for (int k = 0; k < this.size; k++) {
			tmp[k] = this.data[k];
		}
		this.data = tmp;
	}
   
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		this.checkIndex(i, size);
		
		if (this.size == this.data.length) {
			this.resize( 2 * this.data.length);
		}

		for (int k = this.size - 1; k >= i; k--) {
			this.data[k+1] = this.data[k];
		} 
		
		this.data[i] = e;
		this.size++;
	}

    public void add( E e ) { this.add( this.size, e ); } // add element e at index size (compatible Java List; append)
    
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public E first() {
		if( this.isEmpty() ) throw new NoSuchElementException( "list is empty" );
		return this.data[this.size-1];
	}

	@Override
	public E last() {
		
		return null;
	}



	@Override
	public void addFirst(E e) {
		
		
	}

	@Override
	public void addLast(E e) {
		
		
	}

	@Override
	public E removeFirst() {
		
		return null;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		
		return null;
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		
		return null;
	}

	@Override
	public E remove(E e) {
		
		return null;
	}

	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		this.checkIndex(i, this.size);
		E tmp = this.data[i];
		
		for (int k = i; k < this.size - 1; k++) {
			this.data[k] = this.data[k+1];
		}
		
		this.data[this.size-1] = null;
		this.size--;
		
		if ( this.data.length/2 > this.size) {
			this.resize(this.data.length/2);
		}
		
		return tmp;
	}

	@Override
	public int indexOf(E e) {
		
		return 0;
	}

	@Override
	public boolean equals(List<E> o) {
		
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		
		return null;
	}

}
