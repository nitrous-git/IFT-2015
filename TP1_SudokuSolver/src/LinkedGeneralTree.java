/**
 * 
 * LinkedGeneralTree class with inner Node class
 * Children are stored in a ArrayList 
 * 
 * Based on LinkedBinaryTree, Francois Major 
 * Based on Goodrich, Tamassia, Goldwasser
 *
 * @author      Olivier Trudel, Benjamin Dockins
 * @version     1.0
 * @since       1.0
 */

import java.util.ArrayList;
import java.util.List;

public class LinkedGeneralTree<E> extends AbstractTree<E> {
	
    //----- inner Node class
    protected static class Node<E> implements Position<E> {
		private E element; // element stored in this node
		private Node<E> parent; // reference to parent node, if any 
		private List<Node<E>> children; // list of all children
		
		// construct a node with element and neighbors
		public Node( E e, Node<E> parent, List<Node<E>> children ) {
		    this.element = e;
		    this.parent = parent;
		    this.children = new ArrayList<Node<E>>();
		}
		
		// getters
		public E getElement() { return this.element; }
		public Node<E> getParent() { return this.parent; }
		public List<Node<E>> getChild() { return this.children; }
		
		// setters
		public void setElement( E e ) { this.element = e; }
		public void setParent( Node<E> parent ) { this.parent = parent; }
		public void setChild( Node<E> child ) { this.children.add(child); }
		
		public String toString() { return this.element.toString(); }
    } //----- end of inner class Node

    
    // Developer's method to create a new node with element e
    protected Node<E> createNode( E e, Node<E> parent, List<Node<E>> children ) {
    	return new Node<E>( e, parent, children );
    }
    
    // LinkedGeneralTree attributes
    protected Node<E> root = null; // root of the tree, initially null
    private int size = 0; // number of nodes in the tree, initially 0

    // default constructor for an empty tree
    public LinkedGeneralTree() {}
    
    // --------------------------------------------
    // developer's utilities
    // validate a position and returns its node
    protected Node<E> validate( Position<E> p ) throws IllegalArgumentException {
		if( !( p instanceof Node ) ) throw new IllegalArgumentException( "Invalid position type" );
		
		// on manipule une variable de type node, et non position
		Node<E> node = (Node<E>)p; // safe cast

		// convention pour garbage collection, le parent pointe sur le noeud lui-meme
		if( node.getParent() == node ) // convention for defunct node
		    throw new IllegalArgumentException( "Position has been deleted" );
		
		return node;
    }
    
	// --------------------------------------------
    // public update methods
    // place element e at the root of an empty tree, return its position
    public Position<E> addRoot( E e ) throws IllegalStateException {
     	if( !this.isEmpty() ) throw new IllegalStateException( "Tree is not empty" );
		this.root = createNode( e, null, null );
		this.size = 1;
		return root;
    }
    
    // create a new child of position p storing element e, return its position
    public Position<E> addChild( Position<E> p, E e ) throws IllegalArgumentException {
		Node<E> parent = this.validate( p );
		Node<E> child = createNode( e, parent, null );
		parent.setChild(child);
		this.size++;
		return child;
    }
    
    // AbstractTree method implementation 
    // -----------------------------------------
	@Override
	public Position<E> root() {
		return this.root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return node.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		List<Position<E>> snapshot = new ArrayList<>(); // has 9 sibling (or k sibling)
		for (Position<E> position : node.children) {
			if (position != null) {
				snapshot.add(position);
			}
		}
		return snapshot;
	}

	@Override
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		int count = 0;
		for (Position<E> position : node.children) {
			if (position != null) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int size() {
		return this.size;
	}
	
    // ---------------------------------------------
    // pretty printing method
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String toString() {
    	IntegerBoard<Integer> board = null;
		System.out.println("{ ");
		
		for( Position pos : this.breadthfirst() )
			((IntegerBoard<Integer>) pos.getElement()).display();
		
		System.out.println();
		
		return "}";
    }
}
