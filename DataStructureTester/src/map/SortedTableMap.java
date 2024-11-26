package map;

import java.util.ArrayList;
import java.util.Comparator;


public class SortedTableMap<K, V> extends AbstractSortedMap<K, V> {

    // physical storage for the map entries
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();

    // construct an initially empty map
    public SortedTableMap() { super(); }
    public SortedTableMap( Comparator<K> comp ) { super( comp ); }
	
    // binary search in O(log n)
    // return the smallest index of range [low..high] inclusive storing an entry
    // with a key greater than or equal to k, otherwise index high+1, by convention
    private int findIndex( K key, int low, int high ) {
		if( high < low ) return high + 1; // no entry qualifies
		int mid = ( low + high ) / 2;
		int comp = compare( key, this.table.get( mid ) );
		if( comp == 0 ) return mid; // found entry with exact match
		else if( comp < 0 ) return findIndex( key, low, mid - 1 ); // search left of mid
		else return findIndex( key, mid + 1, high ); //search right of mid
    }
    
    // interface to search the entire table
    private int findIndex( K key ) { 
    	return findIndex( key, 0, this.table.size() - 1); 
    }

    // return the entry at index j, or null if j is out of bounds
    private Entry<K,V> safeEntry( int j ) {
		if( j < 0 || j >= this.table.size() ) {
			return null;
    	}
		return this.table.get( j );
    }
	
	// Implemented method 
	// ------------------------------------------
	
    // return the entry having the least key, or null if map is empty
	@Override
	public Entry<K, V> firstEntry() {
		return safeEntry(0);
	}

	// return the entry having the greatest key, or null if map is empty
	@Override
	public Entry<K, V> lastEntry() {
		return safeEntry(this.table.size() - 1);
	}

	// return the entry with least key greater than or equal to given key, if any
	@Override
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
		return safeEntry(this.findIndex(key));
	}

    // return the entry with greatest key less than or equal to given key, if any
    // plus grand key plus petit que K (ou egal)
	@Override
	public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
		int j = this.findIndex(key);
		if (j == this.size() || !key.equals(this.table.get(j).getKey())) {
			j--; // look one earlier and take it unless found exact match
		}
		return safeEntry(j);
	}

	// return the entry with greatest key strictly less than given key, if any
	@Override
	public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
		return safeEntry(this.findIndex(key) - 1);
	}

	@Override
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
		int j = this.findIndex( key );
		if (j < this.size() && key.equals(this.table.get(j).getKey())) {
			j++; // go past exact match
		}
		return safeEntry(j);
	}

	@Override
	public int size() {
		return this.table.size();
	}

	// check if an entry with given key exists
	@Override
	public boolean containsKey(K key) {
		return this.findIndex(key) < this.size();
	}

    // return the value associated with the specified key, or null if not found
	@Override
	public V get(K key) {
		int j = this.findIndex(key);
		if (j == this.size() || compare(key, this.table.get(j)) != 0) {
			return null; // no match
		}
		return this.table.get(j).getValue();
	}

	// associate the pair key-value, replacing existing value if any
	@Override
	public V put(K key, V value) {
		int j = this.findIndex(key);
		// match exists
		if (j < this.size() && compare(key, this.table.get(j)) == 0) {
			return this.table.get(j).setValue(value);
		}
		// otherwise add new entry
		this.table.add( j, new MapEntry<K,V>( key, value ) ); 
		return null;
	}

    // remove the entry with specified key, if any, return its value
    // meme si la recherche est en O(logn) on doit compacter le tableau donc O(n)
	@Override
	public V remove(K key) {
		int j = findIndex(key);
		if (j == this.size() || compare(key, this.table.get(j)) != 0) {
			return null; // no match
		}
		return this.table.remove(j).getValue();
	}

    // Iterators
    // support for snapshot iterators for entrySet and subMap
    private Iterable<Entry<K,V>> snapshot( int startIndex, K stop ) {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		int j = startIndex;
		while( j < this.table.size() && ( stop == null || compare( stop, this.table.get( j ) ) > 0 ) )
		    buffer.add(this.table.get(j++)); // on ajoute et on incremente 
		return buffer;
    }
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return snapshot(0, null);
	}
	
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
		return snapshot(this.findIndex(fromKey), toKey);
	}
	
	// pretty printing
	// ------------------------------------------
    public String toString() {
		if( this.isEmpty() ) return "{}";
		String s = "{";
		for( int i = 0; i < this.table.size() - 1; i++ ) {
		    Entry<K,V> entry = this.table.get( i );
		    s += entry.toString() + ", ";
		}
		s += this.table.get( this.table.size() - 1 ) + "}";
		return s;
    }
}
