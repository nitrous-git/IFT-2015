package priorityQueue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
	
    //----- inner PQEntry class -----
    protected static class PQEntry<K,V> implements Entry<K,V> {
		// attributes
		private K k; // key
		private V v; // value
		
		// constructor
		public PQEntry( K k, V v ) { this.k = k; this.v = v; }
		
		// methods to realize the Entry interface
		@Override
		public K getKey() { return this.k; }
		@Override
		public V getValue() { return this.v; }
		
		// utilities
		protected void setKey( K k ) { this.k = k; }
		protected void setValue( V v ) { this.v = v; }
		
		@Override
		public String toString() { return "(" + this.getKey() + "," + this.getValue() + ")"; }
    } //----- end inner class PQEntry -----

    
    
    // attributes
    private Comparator<K> comp; // comparator defining the ordering of the keys
    
    
    // Constructors
    // constructor with a given comparator to order keys
    public AbstractPriorityQueue( Comparator<K> comp ) {
		this.comp = comp;
	}
    
    // constructor with default comparator 
    public AbstractPriorityQueue() {
    	// appel le constructeur avec un argument
    	this( new DefaultComparator<K>() );
    }
    
    // developer method
    protected int compare( Entry<K,V> a, Entry<K,V> b ) { 
    	// on compare les entre par les cle 
    	return this.comp.compare( a.getKey(), b.getKey() ); 
	}
    
    // determine whether a key is valid
    protected boolean checkKey( K key ) throws IllegalArgumentException {
		try {
			// les cle sont incompatible
		    return( this.comp.compare( key, key ) == 0 ); // see if key can be compared to itself
		} catch( ClassCastException e ) {
		    throw new IllegalArgumentException( "Incompatible key" );
		}
    }
    
    
    
    
    // PriorityQueue interface implementation method 
    
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
}
