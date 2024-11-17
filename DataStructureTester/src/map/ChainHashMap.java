package map;

import java.util.ArrayList;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {

	private UnsortedTableMap<K,V>[] table; // initialized in createTable
	
	// constructors 
	public ChainHashMap() { super(); }
    public ChainHashMap( int cap ) { super( cap ); }
    public ChainHashMap( int cap, int p ) { super( cap, p ); }
	
	// --------------------------------------------
	// AbstractHashMap implemented method
	
	@SuppressWarnings("unchecked")
	@Override
	protected void createTable() {
		table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[this.capacity];
	}

    // return value associated with key k in bucket with hash value h, or else null
	@Override
	protected V bucketGet(int h, K k) {
		UnsortedTableMap<K,V> bucket = table[h];
		if( bucket == null ) return null;
		return bucket.get( k );
	}

	// associate key k with value v in bucket with hash value h; returns old value
	@Override
	protected V bucketPut(int h, K k, V v) {
		UnsortedTableMap<K, V> bucket = table[h];
		
		if (bucket == null) {
			table[h] = new UnsortedTableMap<>();
			bucket = table[h];
		}
		
		int oldSize = bucket.size();
		V old = bucket.put(k, v);
		this.n += ( bucket.size() - oldSize ); // size may have increased, gives 0 or 1
		return old;
	}

    // remove entry having key k from bucket with hash value h, if any
	@Override
	protected V bucketRemove(int h, K k) {
		UnsortedTableMap<K, V> bucket = table[h];
		
		if (bucket == null) {
			return null;
		}
		
		int oldSize = bucket.size();
		V retV = bucket.remove(k);
		n -= (oldSize - bucket.size()); // size may have decreased, gives 0 or 1
		
		return retV;
	}
	
	
    // return an iterable collection of all key-value entries of the map
    public Iterable<Entry<K,V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for( int h = 0; h < this.capacity; h++ ) {
		    if( table[h] != null ) {
				for( Entry<K,V> entry : table[h].entrySet() ) {
				    buffer.add( entry );
				}
		    }
    	}
		return buffer;
    }

}
