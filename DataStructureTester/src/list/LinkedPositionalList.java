package list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedPositionalList<E> implements PositionalList<E>, Comparator<E>  {

	
    //----- inner class Node -----
    @SuppressWarnings("hiding")
	private class Node<E> implements Position<E> {
		private E element;    // reference to the element stored in this node
		private Node<E> prev; // reference to the previous node in the list
		private Node<E> next; // reference to the next node in the list
		private Object container; // reference to the node's container

		public Node( E e, Node<E> prev, Node<E> next ) {
		    this.element = e;
		    this.prev = prev;
		    this.next = next;
		    
		    // le node doit savoir dans quelle liste il est ... si l'utilisateur manipule plusieurs liste
		    this.container = LinkedPositionalList.this; // pointeur sur la classe parent, sur l'instance de l'objet qui contient le node 
		    // Cette classe precisement :) 
		}
		public E getElement() throws IllegalStateException {
			// le node a ete retire  de la liste 
			// une fois l'elem retire, on met le next a null 
		    if( this.next == null ) // convention for defunct node
		    	throw new IllegalStateException( "Position no longer valid" );
		    
		    return this.element;
		}
		
		public Node<E> getPrevious() { return this.prev; }
		public Node<E> getNext() { return this.next; }
		public Object getContainer() { return this.container; }
		public void setElement( E e ) { this.element = e; }
		public void setPrevious( Node<E> prev ) { this.prev = prev; }
		public void setNext( Node<E> next ) { this.next = next;	}
		public String toString() {
		    return "Position( element = " + this.getElement() + " )";
		}
    } 
    //----- end of inner class Node -----
	
	
	
    // instance attributes
    private Node<E> header;  // header sentinel
    private Node<E> trailer; // trailer sentinel
    private int size = 0;    // number of elements in the list
    
    // PositionalList developer methods
    private Node<E> getHead() { return this.header.getNext(); }
    private Node<E> getTail() { return this.trailer.getPrevious(); }
    
    
    // constructor 
    public LinkedPositionalList() {
		this.header = new Node<>( null, null, null ); // create header sentinel
		this.trailer = new Node<>( null, this.header, null ); // create trailer sentinel preceded by header
		this.header.setNext( this.trailer ); // link header to trailer
    }
    
    // ------- implementing developer methods -------- //
    
    // validate a position and return it as a node
    private Node<E> validate( Position<E> p ) {
    	// est ce que le pointeur est une instance de node (l'utilisateur passe tu un noeud ?)
		if( !( p instanceof Node ) ) throw new IllegalArgumentException( "Invalid position" );
    	
		// on travail avec le node directemnt, pour avoir acces au methode de node on fait un cast
		Node<E> node = (Node<E>) p; // safe cast
		
		// on verifier que c'est la liste, instance de cette classe 
		// (liste qui a appeler la creation de se noeud)
		if( node.getContainer() != (Object)this ) throw new IllegalArgumentException( "Invalid container" );
		
		// node deja retire 
		if( node.getNext() == null ) // convention for defunct node
		    throw new IllegalArgumentException( "position has been removed from the list" );

		return node;
	}
    
    // return the given node's position, or null if it is a sentinel
    private Position<E> position( Node<E> node ) {
		if( node == this.header || node == this.trailer )
		    return null;
		return node;
    }
    
    // methode d'insertion
    // add element e to the list between the given nodes
    private Position<E> addBetween( E e, Node<E> pred, Node<E> succ ) {
    	
    	// cree un nouveau node, on insere entre pred et succ 
		Node<E> novel = new Node<>( e, pred, succ ); // create and link the new node
		pred.setNext( novel );
		succ.setPrevious( novel );
		this.size++;
		
		return novel;
    }
    // --------------------------------------------------------
    
    
    
    
	
	
	// ------- implementing interface method -------- //
    
	@Override
	public int size() {
		return this.size();
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Position<E> first() {
		return this.position(this.header.getNext());
	}

	@Override
	public Position<E> last() {
		return this.position(this.trailer.getPrevious());
	}

    // return the Position immediately before Position p, or null if p is first
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return this.position(node.getPrevious());
	}
	
    // return the Position immediately after Position p, or null if p is last
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return this.position(node.getNext());
	}

	
	// methode d'insertion et deletion
	// --------------------------------------------------------
	@Override
	public Position<E> addFirst(E e) {
		return this.addBetween(e, this.header, this.header.getNext());
	}

	@Override
	public Position<E> addLast(E e) {
		return this.addBetween(e, this.trailer.getPrevious(), this.trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return this.addBetween(e, node.getPrevious(), node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return this.addBetween(e, node, node.getNext());
	}

	// replace element at p and return replaced element
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		E node_element = node.getElement();
		node.setElement(e);
		return node_element;
	}

	// remove element at p and returns it (invalidate p)
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
    	// est ce que le pointeur a deja ete retirer, est ce qu'il est defunc, on doit valider  
		Node<E> node = this.validate( p ); 
		
		// on ajuste les pointeurs 
		Node<E> pred = node.getPrevious();
		Node<E> succ = node.getNext();
		pred.setNext(succ);
		succ.setPrevious(pred);
		this.size--;
		
		E node_element = node.getElement();
		node.setElement( null ); // garbage collection
		// notre convention est que si l'element est remove() on met le next a null
		// sinon en tout temps, le dernier pointe sur le sentinel (et non null)
		node.setNext( null );    // convention for defunct node 
		node.setPrevious( null );    // unlink completely
		
		return node_element;
	}

    // move toMove Position immediately before that position
    // utilise dans testPosition
    // on pourrais avoir un moveAfter aussi 
    public void moveBefore( Position<E> that, Position<E> toMove ) throws IllegalArgumentException {
		Node<E> thatNode = this.validate( that );
		Node<E> toMoveNode = this.validate( toMove );
		
		// relink around this node
		toMoveNode.getPrevious().setNext( toMoveNode.getNext() );
		toMoveNode.getNext().setPrevious( toMoveNode.getPrevious() );
		
		// move this node: adjust toMoveNode
		toMoveNode.setPrevious( thatNode.getPrevious() );
		toMoveNode.setNext( thatNode );
		
		// move this node: adjust around
		thatNode.getPrevious().setNext( toMoveNode );
		thatNode.setPrevious( toMoveNode );
    }
    
    
    public void moveAfter( Position<E> that, Position<E> toMove ) throws IllegalArgumentException {
		Node<E> thatNode = this.validate( that );
		Node<E> toMoveNode = this.validate( toMove );
		
		// relink around this node
		toMoveNode.getPrevious().setNext( toMoveNode.getNext() );
		toMoveNode.getNext().setPrevious( toMoveNode.getPrevious() );
		
		// move this node: adjust toMoveNode
		toMoveNode.setNext( thatNode.getNext() );
		toMoveNode.setPrevious( thatNode );
		
		// move this node: adjust around
		thatNode.getNext().setPrevious( toMoveNode );
		thatNode.setNext( toMoveNode );
    }
    
    

    // insertion sort O(n^2) of the list into natural ordering of its elements
    // marker est bien trier, on met walk dessus et pivot doit etre trier 
    // on descend avec walk, quand la position est <= pivot, on fait notre insertion 
	@Override
    public void sort() {
    	// la zone trier est vide, on positione bien le marker
    	Position<E> marker = this.first();
		while( marker != this.last() ) {
			// le pivot est juste apres marker
		    Position<E> pivot = this.after( marker );
		    E value = pivot.getElement();
		    // la prochiane valeur est plus grande on avance marker
		    if( this.compare( value, marker.getElement() ) > 0 )
		    	marker = pivot;
		    else {
		    	Position<E> walk = marker;
		    	// compare > 0 si plus grand, < 0 si plus petit, == 0 si egal 
				while( walk != this.first() && this.compare( value, this.before( walk ).getElement() ) < 0 )
				    walk = this.before( walk );
				
				this.moveBefore( walk, pivot );
		    }
		}
    }
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// --------------------------------------------------------
	// Classe et methodes Iterateur
	
	 //----- inner position iterator class
    private class PositionIterator implements Iterator<Position<E>>  {

    	// point de depart est current 
		private Node<E> current = getHead(); // reference to next element
		
		// une fois iterer sur l'element a retirer on la store dans last et on remove last 
		private Node<E> last = null; // reference to the last accessed element
    	
		@Override
		public boolean hasNext() {
			return this.current != null;
		}

		@Override
		public Position<E> next() {
			// on a depasser le dernier element
		    if( this.current == null ) throw new NoSuchElementException( "No more position" );
		    
		    // on sauve la position de current dans last (si jamais l'utilisateur veut le retirer)
		    this.last = current; // element to be removed (if remove is called)
		    
		    // on convertit en position 
		    Position<E> pos = position( current );
			
		    // cas special, on ne parcours pas la sentinel, on arrete avant 
		    if( this.current == trailer.getPrevious() )
		    	this.current = null; // special case to handle trailer sentinel
		    else this.current = current.getNext(); // on avance current, on peut retirer last
		    
			return pos;
		}

		@Override
		public void remove() { // remove current node from list
			// rien a enlever
		    if( this.last == null ) throw new NoSuchElementException( "No position to remove" );
		    
		    // on appel la methode remove(), on doit envoyer la position 
		    LinkedPositionalList.this.remove( position( this.last ) ); // remove the last accessed node from the list
		    this.last = null; // no position to remove until next() is called // defunc, on le met a null, il sera garbage collected eventuellement 
		}
		
    } // ----- fin position iterator 

    //----- inner position iterable class
    private class PositionIterable implements Iterable<Position<E>> {
		// return a position iterator of the list
		public Iterator<Position<E>> iterator() { return new PositionIterator(); }
    } //----- end of inner position iterable class
    
	@Override
    // return a position iterable of the list
    public Iterable<Position<E>> positions() {
    	return new PositionIterable();
    }
   
	
	
	
	
	
	@Override
	public Iterator<E> iterator() {
		return null;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public int compare(E a, E b) throws ClassCastException {
		return((Comparable<E>)a).compareTo( b );
	}
	// ------------------------------------------
	
	
	
	
	// toString method for pretty printing 
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
