package priorityQueue;

import java.util.Comparator;

public class HeapAdaptablePriorityQueue<K, V> extends HeapPriorityQueue<K, V> implements AdaptablePriorityQueue<K, V> {

    //----- inner AdaptablePQEntry class -----
    // extension of the PQEntry to include location
    protected static class AdaptablePQEntry<K,V> extends PQEntry<K,V> {
		private int index; // entry's current index in the array
		
		public AdaptablePQEntry( K k, V v, int j ) {
		    super( k, v ); // set key and value
		    this.index = j; // set the index
		}
		
		public int getIndex() { return this.index; }
		public void setIndex( int j ) { this.index = j; }
    } //----- end inner AdaptablePQEntry -----
    
    
    // Constructors 
    
    // construct an empty adaptable PQ based using the natural ordering of the keys
    public HeapAdaptablePriorityQueue() { super(); }
    
    // construct an empty adaptable PQ using a given comparator to order the keys
    public HeapAdaptablePriorityQueue( Comparator<K> comp ) { super( comp ); }

	
    // utilities
    
    protected AdaptablePQEntry<K,V> validate( Entry<K,V> entry ) throws IllegalArgumentException {
		if( !( entry instanceof AdaptablePQEntry ) )
		    throw new IllegalArgumentException( "Invalid entry" );
		
		AdaptablePQEntry<K,V> locator = (AdaptablePQEntry<K,V>) entry; // safe cast
		
		int j = locator.getIndex();
		if( j >= this.heap.size() || this.heap.get( j ) != locator )
		    throw new IllegalArgumentException( "Invalid entry" );
		
		return locator;
    }
	
    // swap entries i and j of the ArrayList
    @Override
    protected void swap( int i, int j ) {
		super.swap( i, j ); // perform swap
		// swap is already performed with super(), update the index with set 
		((AdaptablePQEntry<K,V>) this.heap.get( i )).setIndex( i ); // reset index
		((AdaptablePQEntry<K,V>) this.heap.get( j )).setIndex( j ); // reset index
    }
	
    // restore the heap property by moving the entry at index j upward/downward
    protected void bubble( int j ) {
		if( j > 0 && compare( this.heap.get( j ), this.heap.get( this.parent( j ) ) ) < 0 )
		    this.swim( j );
		else
		    this.sink( j );
    }

	
	
	
    // AdaptablePriorityQueue interface methods
	
    // remove a given entry
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> locator = validate( entry );
		int j = locator.getIndex();
		
		if( j == this.heap.size() - 1 ) { // entry is at last position
		    this.heap.remove( this.heap.size() - 1 ); // so just remove it
			this.size--;
		}
		else {
			// swap entry to remove at the end
		    this.swap( j, this.heap.size() - 1 ); // swap entry to last position
		    // use .remove() to remove last index 
		    this.heap.remove( this.heap.size() - 1 ); // remove it
		    // bubble the new value at index j 
		    this.bubble( j ); // fix entry location to restor heap property
		    this.size--;
		}
	}

	// replace the key of an entry
	@Override
	public void replaceKey(Entry<K, V> entry, K k) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> locator = validate( entry );
		locator.setKey( k ); // method inherited from PQEntry
		// bubble if necessary 
		this.bubble( locator.getIndex() );
	}

	 // replace the value of an entry
	@Override
	public void replaceValue(Entry<K, V> entry, V v) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> locator = validate( entry );
		locator.setValue( v ); // method inherited from PQEntry
		// no need to bubble 
	}
	
	
	
	
	
	// EXTRA override 
    // insert a (key, value) entry and return it
    @Override
    public Entry<K,V> insert( K k, V v ) throws IllegalArgumentException {
		this.checkKey( k ); // might throw exception
		Entry<K,V> newest = new AdaptablePQEntry<>( k, v, this.heap.size() );
		this.heap.add( newest ); // add to the end of the list
		this.swim( this.heap.size() - 1 ); // swim the newly added entry
		this.size++;
		return newest;
    }
    
}
