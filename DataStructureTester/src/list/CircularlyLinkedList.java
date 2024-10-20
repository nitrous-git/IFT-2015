package list;

public class CircularlyLinkedList<E> extends SinglyLinkedList<E> {

    // constructor
    public CircularlyLinkedList() { } // make an initially empty circular list instance
    
    // List developer methods
    //   the attribute head is not used in circular lists
    //   instead, the head is the Node that follows the tail
    protected Node<E> getHead() {
		if( this.isEmpty() ) return null;
		return this.tail.getNext();
    }
	
    //ne pas perdre la tete .. lol 
    public void addFirst( E element ) { // add a new element at the front of the list
    	// si la liste est vide
		if( this.isEmpty() ) {
			//on cree le noeud avec le seul element
		    this.tail = new Node<>( element, null );
		    //on met le pointeur sur cet elem, le tail.Next sera lui meme
		    this.tail.setNext( this.tail ); // link to itself circularly
		} else {
			//on cree un nouveau noeud
		    Node<E> newNode = new Node<>( element, this.tail.getNext() );
		    // on prend l'ancien tail.next et on l'assigne au nouveau 
		    this.tail.setNext( newNode );
		}
		this.size++;
    }
    
    // ne pas perdre la queue .. lol
    // la nouvelle track doit devenir tail 
    public void addLast( E element ) { // add a new element at the end of the list
    	// addFirst fait deja le cas si la liste est vide 
		this.addFirst( element );        // insert a new element at the front of the list
		this.tail = this.tail.getNext(); // new element becomes the tail!
    }
    
    // extension
    public void rotate() { // rotate the first element to the back of the list
		if( ! this.isEmpty() )               // if empty, nothing to do
		    this.tail = this.tail.getNext(); // the old head becomes the new tail
    }
    
    // on prend le pointeur sur head et on le met sur tail.Next juste avant 
    
    public E removeFirst() { // remove and return the first element of the list
    	
		if( this.isEmpty() ) return null;         // nothing to remove
		
		Node<E> head = this.tail.getNext(); 
		
		if( head == this.tail ) this.tail = null; // only one Node left
		
		else this.tail.setNext( head.getNext() ); // remove first element from the list
		size--;
		return head.getElement();
    }
    
    
    
}
