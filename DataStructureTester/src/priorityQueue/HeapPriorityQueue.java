package priorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    // primary storage in an ArrayList
    protected List<Entry<K,V>> heap = new ArrayList<>();

    // constructors
    public HeapPriorityQueue() { super(); }
    public HeapPriorityQueue( Comparator<K> comp ) { super( comp ); }
    
    // utilities
    protected int parent( int j ) { return ( j-1 ) / 2; } // truncating division (return int)
    protected int left( int j ) { return 2 * j + 1; }
    protected int right( int j ) { return 2 * j + 2; }
    protected boolean hasLeft( int j ) { return this.left( j ) < this.heap.size(); }
    protected boolean hasRight( int j ) { return this.right( j ) < this.heap.size(); }

    // ----------------
	// developer method 
    
    // swap entries i and j of the ArrayList
    protected void swap(int i, int j) {
    	Entry<K, V> tmp = this.heap.get(i);
    	this.heap.set(i, this.heap.get(j));
    	this.heap.set(j, tmp);
    }
    
    // move entry j higher, if necessary, to restore the heap property in O(log n)
    protected void swim(int j) {
    	// continue until reaching the root
		while (j > 0) {
			int p = this.parent(j);
			if (compare(this.heap.get(j), this.heap.get(p)) >= 0) {
				break;
			}
			this.swap(j, p);
			j = p; // continue from parent
		}
	}

    // move entry j lower, if necessary, to restore the heap property in O(log n)
	protected void sink(int j) {	
		// continue to bottom 
		while (this.hasLeft(j)) {
			int leftIndex = this.left(j); // although right may be smaller
			int smallChildIndex = leftIndex;
			
			if (this.hasRight(j)) {
				int rightIndex = this.right(j); 
				if (this.compare(this.heap.get(leftIndex), this.heap.get(rightIndex)) > 0) {
					smallChildIndex = rightIndex; // right child is smaller
				}
			}
			
			if (this.compare(this.heap.get(smallChildIndex), this.heap.get(j)) >= 0) {
				break;
			}
			
			this.swap(j, smallChildIndex);
			j = smallChildIndex; // continue at the smallest child
		}
	}
    
	// -----------------------------------------------------
	// AbstractPriorityQueue interface implementation method 
	
	@Override
	public int size() {
		return this.heap.size();
	}

    // insert a (key, value) entry and return it in O(log n)
	@Override
	public Entry<K, V> insert(K k, V v) throws IllegalArgumentException {
		this.checkKey(k); // key-checking (could throw exception)
		Entry<K, V> newest = new PQEntry<>(k, v);
		this.heap.add(newest); // add to the end of the list
		this.swim(this.heap.size()-1); // swim the newly added entry
		return newest;
	}

	@Override
	public Entry<K, V> min() {
		if (this.heap.isEmpty()) {
			return null;
		}
		return this.heap.get(0);
	}

    // remove and return an entry of minimal key (if any) in O(log n)
	@Override
	public Entry<K, V> removeMin() {
		if (this.heap.isEmpty()) {
			return null;
		}
		Entry<K, V> ret = this.heap.get(0);
		this.swap( 0, this.heap.size() - 1 ); // exchange first and last entries
		this.heap.remove( this.size() - 1 ); // and remove it from list
		this.sink( 0 ); // sink the new root
		return ret;
	}
	
	
	// --------------------
    // convert PQ to String
    @Override
    public String toString() { return this.heap.toString(); }

}