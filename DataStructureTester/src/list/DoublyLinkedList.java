package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>{
	
    // attributes
    protected Node<E> header;  // dummy header Node
    protected Node<E> trailer; // dummy trailer Node
    protected int size = 0;    // number of nodes in the list
    protected boolean reversalMode = false;
	
    //----- inner Node class
    protected static class Node<E> {
		// attributes
		protected E element;    // reference to the element
		protected Node<E> next; // reference to the next Node in the list
		protected Node<E> prev; // reference to the previous Node in the list
		// constructor
		protected Node( E element, Node<E> prev, Node<E> next ) {
		    this.element = element;
		    this.prev = prev;
		    this.next = next;
		}
		// getters & setters
		protected E    getElement() { return this.element; }               // element getter
		protected void setElement( E element ) { this.element = element; } // element setter
		protected Node<E> getNext() { return this.next; }               // next Node getter
		protected void    setNext( Node<E> next ) { this.next = next; } // next Node setter
		protected Node<E> getPrev() { return this.prev; }               // next Node getter
		protected void setPrev( Node<E> prev ) { this.prev = prev; } // next Node setter
	}
	
    //----- inner iterator class
    protected class DoublyLinkedListIterator implements Iterator<E> {
		private Node<E> current = getHead(); // reference to next element

		public boolean hasNext() { 
			return current != null; 
		}
	
		public E next() throws NoSuchElementException {
		    if( current == null ) throw new NoSuchElementException( "No more element" );
		    
		    E element = current.getElement();
		    if( current == trailer.getPrev() )
		    	current = null; // special case to handle circular list as well
		    else 
		    	current = current.getNext();
		    
		    return element;
		}
    } 
    
    //----- inner iterator class
    protected class ReverseDoublyLinkedListIterator implements Iterator<E> {
		private Node<E> last = getTail(); // reference to next element		
		
		public boolean hasNext() { 
			return last != null; 
		}
		
		public E next() throws NoSuchElementException {
		    if( last == null ) throw new NoSuchElementException( "No more element" );
		    
		    E element = last.getElement();
		    if( last == header.getNext() )
		    	last = null; // special case to handle circular list as well
		    else 
		    	last = last.getPrev();
		    
		    return element;
		}
    } 
   
    public void setIteratorMode(boolean reversalMode) {
    	this.reversalMode = reversalMode;
    }
    
    public Iterator<E> iterator() { // return an iterator over the elements in proper order
    	if (reversalMode) { return new ReverseDoublyLinkedListIterator(); } 
    	else {	return new DoublyLinkedListIterator(); }
    }
    

    // constructor 
    public DoublyLinkedList() { // make an initially empty list instance
		this.header = new Node<>( null, null, null );    // dummy header Node
		this.trailer = new Node<>( null, header, null ); // dummy trailer Node with previous header
		this.header.setNext( this.trailer );             // trailer follows header
    }
    
    
    //------------------------------------------
    // interface List implementation
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
		if (isEmpty()) { return null; } 
		return this.header.getNext().getElement();
	}

	@Override
	public E last() {
		if( this.isEmpty() ) { return null; } 
		return this.trailer.getPrev().getElement(); 
	}
	
    // List developer methods
    protected Node<E> getHead() { return this.header.next; }
    protected Node<E> getTail() { return this.trailer.prev; }

	// add element between given Nodes
    protected void addBetween( E element, Node<E> predecessor, Node<E> successor ) { 
		Node<E> newNode = new Node<>(element, predecessor, successor);
		predecessor.setNext(newNode);
		successor.setPrev(newNode);
		this.size++;
    }
	
	
	// add a new element at the front of the list	
    public void addFirst( E element ) { 	
    	this.addBetween( element, header, header.getNext() );
    }
    
    // add a new element at the end of the list
    public void addLast( E element ) { 
    	addBetween( element, trailer.getPrev(), trailer );
    }
    

	@Override
	public void add(E element) {
		this.addLast( element );
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		// ----- 
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
		return null;
	}

	@Override
	public int indexOf(E e) {
		return 0;
	}

	@Override
	public boolean equals(List<E> o) {
		return false;
	}

	
	// ------------------------
	// pretty printing
    public String toString() {
		if( this.isEmpty() ) return "[]";
		String pp = "[";
		Node<E> current = this.getHead();
		while( current != this.getTail() ) {
		    pp += current.getElement() + ",";
		    current = current.getNext();
		}
		pp += current.getElement() + "]";
		return pp;
    }

	
}
