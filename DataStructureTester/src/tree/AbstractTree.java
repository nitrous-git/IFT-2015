package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import list.Position;

public abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean isInternal( Position<E> p ) { return numChildren( p ) > 0; }
    @Override
    public boolean isExternal( Position<E> p ) { return numChildren( p ) == 0; }
	@Override
    public boolean isRoot( Position<E> p ) { return p == this.root(); }
    @Override
    public boolean isEmpty() { return this.size() == 0; }
	
    // return the number of levels separating Position p from the root (depth of p)
    // executes in O( depth of p + 1 ); O(n) in the worst case
    // methode recursif, on part de la position d'un noeud et on remonte jusqu'a la racine
    public int depth( Position<E> p ) {
    	//cas de base de la recursion 
		if (this.isRoot(p)) {
			return 0;
		}
		// on va vers le haut, on compte le nb d'appel recursif (1+ ...)
    	return 1 + depth(this.parent(p));
	}
    
    // height method 
    // worst case execution time in O(n^2), bad!
    // on fait pour n noeuds et on remonte l'arbre de n (ou log n) 
    // on a soit O(nlogn) ou O(n^2) dependant de la forme  de l'arbre 
    // mais ne sera jamais plus que n^2 
    //on prend tout les noeud et on remonte
    public int heightBad() {
		int h = 0;
    	
		for (Position<E> p : this.positions()) {
			// si le noeud externe
			// on prend un neoud externe parce qu'il ont la profondeur maximale
			if (isExternal(p)) {
				h=Math.max(h, this.depth(p));
			}
		}
		return h;
	}
    
    // return the height of the subtree rooted at Position p
    // worst case execution time in O(n)
    // on prend la racine et on descend 
    // on doit donner la racine height(root())
    public int heightGood(Position<E> p) {
		int h = 0;
		
		for (Position<E> c : this.children(p)) {
			// appel recursif 
			h = Math.max(h, 1 + this.heightGood(c));
		}
    	return h;
	}
    
    
    
    
    
    
    //-----------------------------------------------------
    // parcours de l'arbre
    // Iterator 
    //-----------------------------------------------------
    //----- inner element iterator class
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = positions().iterator();
		public boolean hasNext() { return posIterator.hasNext(); }
		public E next() { return posIterator.next().getElement(); }
		public void remove() { posIterator.remove(); }
    } //----- end inner element iterator class
	
    @Override
    public Iterator<E> iterator() { return new ElementIterator(); }
    
    
    
    // PARCOURS PREFIXE
    // return an iterable collection of positions of the tree, reported in preorder
    public Iterable<Position<E>> preorder() {
    	List<Position<E>> snapshot = new ArrayList<>();   
    	if ( !this.isEmpty()) {
			preorderSubtree(this.root(), snapshot);
		}
    	return snapshot;
    }
    
    // add the positions of the subtree rooted at p to the given snapshot
    private void preorderSubtree( Position<E> p, List<Position<E>> snapshot ) {
		snapshot.add( p ); // for preorder, we add position p before exploring subtrees
		for( Position<E> c : children( p ) )
		    preorderSubtree( c, snapshot );
    }
    
    
    
    
    
    
    // PARCOURS POSTFIXE
    // return an iterable collection of positions of the tree, reported in postorder
    public Iterable<Position<E>> postorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if( !this.isEmpty() )
		    postorderSubtree( this.root(), snapshot ); // fill the snapshot recursively
		return snapshot;
    }
    
    // add the positions of the subtree rooted at p to the given snapshot
    private void postorderSubtree( Position<E> p, List<Position<E>> snapshot ) {
		for( Position<E> c : children( p ) )
		    postorderSubtree( c, snapshot );
		snapshot.add( p ); // for postorder, we add position p after exploring subtrees
    }
    
    
    
    
   // override positions to make inorder the default for binary trees
   public Iterable<Position<E>> positions() { return preorder(); }
    
}
