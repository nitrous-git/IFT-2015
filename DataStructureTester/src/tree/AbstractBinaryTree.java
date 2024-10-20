package tree;

import java.util.ArrayList;
import java.util.List;

import list.Position;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	
	
	// methode de l'interface Tree<E>
	// BinaryTree<E> extends Tree<E>
	
	@Override
	public Position<E> sibling(Position<E> p){
		Position<E> parent = this.parent(p);
		if (parent == null) {
			return null;
		}
		if (p == this.left(parent)) {
			return this.right(parent);
		}
		return this.left(parent);
	}
	
    // return the number of children of position p
    public int numChildren( Position<E> p ) {
		int count = 0;
		if( this.left( p ) != null ) {
			count++;
		}
		if( this.right( p ) != null ) {
			count++;
		}
		return count;
    }
    

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		List<Position<E>> snapshot = new ArrayList<>(2); // only 2 sibling (binary tree)
		if (this.left(p) != null) {
			snapshot.add(this.left(p));
		}
		if (this.right(p) != null) {
			snapshot.add(this.right(p));
		}
		return snapshot;
	}


	
	
	
}
