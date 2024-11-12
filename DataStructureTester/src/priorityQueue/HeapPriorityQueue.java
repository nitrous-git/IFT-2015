package priorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    // primary storage in an ArrayList
    protected List<Entry<K,V>> heap = new ArrayList<>();
    protected int size = 0; 

    // constructors
    public HeapPriorityQueue() { super(); }
    public HeapPriorityQueue( Comparator<K> comp ) { super( comp ); }
    
    public HeapPriorityQueue(K[] keys, V[] values) {
		super();
		// construct a PQ with given entries
		for (int i = 0; i < keys.length; i++) {
			size++;
			this.heap.add( new PQEntry<>(keys[i], values[i]) );
		}
		this.heapify(); // build heap in O(n)
	}
    
    // utilities
    protected int parent( int j ) { return ( j-1 ) / 2; } // truncating division (return int)
    protected int left( int j ) { return 2 * j + 1; }
    protected int right( int j ) { return 2 * j + 2; }
    protected boolean hasLeft( int j ) { return this.left( j ) < this.size; }
    protected boolean hasRight( int j ) { return this.right( j ) < this.size; }

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
	
	// bottom-up transformation of the heap in O(n)
	public void heapify() {
		// start at parent of the last entry
		int startIndex = this.parent(size-1);
		// loop until the root
		for (int i = startIndex; i >=0; i--) {
			this.sink(i);
		}
	}
    
	// -----------------------------------------------------
	// AbstractPriorityQueue interface implementation method 
	
	@Override
	public int size() {
		return this.size;
	}

    // insert a (key, value) entry and return it in O(log n)
	@Override
	public Entry<K, V> insert(K k, V v) throws IllegalArgumentException {
		this.checkKey(k); // key-checking (could throw exception)
		Entry<K, V> newest = new PQEntry<>(k, v);
		this.heap.add(newest); // add to the end of the list
		size++;
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
		this.swap( 0, this.size() - 1 ); // exchange first and last entries
		this.heap.remove( this.size() - 1 ); // and remove it from list
		size--;
		this.sink( 0 ); // sink the new root
		return ret;
	}
	
	// --------------------
    // convert PQ to String
    @Override
    public String toString() { return this.heap.toString(); }

    public void heapSort() {
    	for (int i = 0; i < heap.size(); i++) {
    		int min = 0; 
    		int last = size-1;
    		this.swap(min, last); 
    		// reduce size of the heap, the rest of the space is a sorted array 
    		size--;
    		this.heapify(); // heapify the new section of the board 
		}
    	// reset size after inplace sort
    	size = this.heap.size();
	}
       
}
