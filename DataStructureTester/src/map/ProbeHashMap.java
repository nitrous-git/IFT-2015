package map;

import java.util.ArrayList;

public class ProbeHashMap<K ,V> extends AbstractHashMap<K, V> {

    private MapEntry<K,V>[] table; // using a fixed array of entries
    private MapEntry<K,V> DEFUNCT = new MapEntry<>( null, null ); // sentinel
    
	
    public ProbeHashMap() { super(); }
    public ProbeHashMap( int cap ) { super( cap ); }
    public ProbeHashMap( int cap, int p ) { super( cap, p ); }
    
	// defunct : valeur speciale qu'on attribut a une case ou on a retirer la cle 
    // return true if location is either empty or the 'defunct' sentinel
    private boolean isAvailable( int j ) { return ( table[j] == null || table[j] == DEFUNCT ); }
    
    
    // on fait ca parceque si on tombe sur defunct on voudrait le mettre tout suite dedans 
    // mais ... il est possible que la valeur soit apres le defunct... 
    // donc on garde le defunct en memoire, on regarde apres, si on le trouve pas, on mettra la valeur dans le defunct 
    // return index with key k, or -(a+1) so that k could be added at index a
	public int findSlot(int h, K k) {
		int avail = -1; 
		int j = h; 						 // index for scanning the table
		
		do {
			if (isAvailable(j)) {        // may be either empty or defunct
				if (avail == -1) {    	 // first available slot // si on trouve un defunct on l'assigne pas a j
					avail = j;
				}
				if (table[j] == null) {  // if empty, search fails immediately
					break;
				}
			}else if(table[j].getKey().equals(k)){
				return j;				 // successful search
			}
			j = (j + 1) % this.capacity; // keep looking (cyclically)
		} while (j != h); 				 // stop if we return to the start
		
		// en python, on retournerais un tuple(true or false, index)
		// ici on fait l'arithmetique 
		return -(avail + 1); // search has failed // return negative // indique au put de convertir l'entrer en positif
	}
	
	// --------------------------------------------
	// AbstractHashMap implemented method
	
    // create an empty table of current capacity
    @SuppressWarnings("unchecked")
	protected void createTable() {
		table = (MapEntry<K,V>[]) new MapEntry[this.capacity];
    }

    // return value associated with key k in bucket with hash value h, or else null
   	@Override
	protected V bucketGet(int h, K k) {
   		int j = findSlot(h, k);
   		if ( j < 0) {  // found defunct or null
			return null;
		}
		return table[j].getValue();
	}

    // associate key k with value v in bucket with hash value h; returns old value
	@Override
	protected V bucketPut(int h, K k, V v) {
		int j = findSlot(h, k);
		
		// this key has an existing entry
		if (j >= 0) {
			return table[j].setValue( v );
		}
		
		// on fait l'arithmetique inverse sur la case trouver du DEFUNCT 
		// convertir l'entre en positif
		table[-(j+1)] = new MapEntry<>( k, v ); // convert to proper index
		this.n++;
		return null;
	}

	@Override
	protected V bucketRemove(int h, K k) {
		int j = findSlot(h, k);
		
		// nothing to remove
		if ( j < 0 ) {
			return null;
		}
		
		V retV = table[j].getValue();
		table[j] = DEFUNCT; // mark this slot as deactivated
		this.n--;
		return retV;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for( int h = 0; h < this.capacity; h++ )
		    if( !isAvailable( h ) ) buffer.add( table[h] );
		return buffer;
	}

}
