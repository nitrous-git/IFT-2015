package tree;

import list.Position;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{

	
	
    //----- inner Node class
    protected static class Node<E> implements Position<E> {
		private E element; // element stored in this node
		private Node<E> parent; // reference to parent node, if any
		private Node<E> left; // reference to the left child, if any
		private Node<E> right; // reference to the right child, if any
		
		// le contianer est this. c'est cette instance de l'arbre 
		// c'est un lien ver l'instance de la classe
		private Object container; // reference to the node's container
		
		// construct a node with element and neighbors
		public Node( E e, Node<E> parent, Node<E> left, Node<E> right, Object container ) {
		    this.element = e;
		    this.parent = parent;
		    this.left = left;
		    this.right = right;
		    this.container = container;  // on pourrait mettre = LinkedBinaryTree.this comme dans list, mais on va le passer dans createNode
		}
		
		// getters
		public E getElement() { return this.element; }
		public Node<E> getParent() { return this.parent; }
		public Node<E> getLeft() { return this.left; }
		public Node<E> getRight() { return this.right; }
		public Object getContainer() { return this.container; }
		// setters
		public void setElement( E e ) { this.element = e; }
		public void setParent( Node<E> parent ) { this.parent = parent; }
		public void setLeft( Node<E> left ) { this.left = left; }
		public void setRight( Node<E> right ) { this.right = right; }
		public void setContainer( Object container ) { this.container = container; }
		public String toString() { return this.element.toString(); }
    } //----- end of inner class Node
	
    // Developer's method to create a new node with element e
    protected Node<E> createNode( E e, Node<E> parent, Node<E> left, Node<E> right ) {
    	return new Node<E>( e, parent, left, right, this ); // on met this. pour le container, this est l'instance de la classe
    }
    
    // LinkedBinaryTree attributes
    protected Node<E> root = null; // root of the tree, initially null
    private int size = 0; // number of nodes in the tree, initially 0

    // default constructor for an empty tree
    public LinkedBinaryTree() {}
    
    // developer's utilities
    // validate a position and returns its node
    protected Node<E> validate( Position<E> p ) throws IllegalArgumentException {
		if( !( p instanceof Node ) ) throw new IllegalArgumentException( "Invalid position type" );
		
		// on manipule une variable de type node, et non position
		Node<E> node = (Node<E>)p; // safe cast
		if( node.getContainer() != (Object)this ) throw new IllegalArgumentException( "Invalid position container" );
		
		// convention pour garbage collection, le parent pointe sur le noeud lui-meme
		if( node.getParent() == node ) // convention for defunct node
		    throw new IllegalArgumentException( "Position has been deleted" );
		
		return node;
    }
    
    
    
    
    
    
    
    
    
    
    
	// -------------------------------------
	
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = this.validate(p);
		return node.getRight();
	}

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
	public int size() {
		return this.size;
	}

	
	
	
	// --------------------------------------------
    // public update methods
    // place element e at the root of an empty tree, return its position
    public Position<E> addRoot( E e ) throws IllegalStateException {
     	if( !this.isEmpty() ) throw new IllegalStateException( "Tree is not empty" );
		this.root = createNode( e, null, null, null );
		this.size = 1;
		return root;
    }
    
    
    // create a new left child of position p storing element e, return its position
    public Position<E> addLeft( Position<E> p, E e ) throws IllegalArgumentException {
		Node<E> parent = this.validate( p );
		
		if( parent.getLeft() != null )
		    throw new IllegalArgumentException( "p already has a left child" );
		
		Node<E> child = createNode( e, parent, null, null );
		parent.setLeft( child );
		this.size++;
		return child;
    }
    
    
    // create a new right child of position p storing element e, return its position
    public Position<E> addRight( Position<E> p, E e ) throws IllegalArgumentException {
		Node<E> parent = this.validate( p );
		
		if( parent.getRight() != null )
		    throw new IllegalArgumentException( "p already has a right child" );
		
		Node<E> child = createNode( e, parent, null, null );
		parent.setRight( child );
		this.size++;
		return child;
    }
    
    
    
    
    
    
    
    
    // ---------------------------------------------
    // pretty printing method
    @SuppressWarnings("rawtypes")
	public String toString() {
		String s = "{ ";
		for( Position pos : this.preorder() )
		    s += pos + " ";
		s += " }";
		return s;
    }
    
    
    
    
    
    
}
