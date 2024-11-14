package map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
	
	// Attributs
    protected int n = 0; // number of entries in the map
    protected int capacity; // size of the table
    private int prime; // prime factor
    private long scale, shift; // shift and scale factors
    
    // constructors 
	public AbstractHashMap(int cap, int p) {
		this.prime = p;
		this.capacity = cap;
		// randomly chose scale and shift
		Random rand = new Random();
		this.scale = rand.nextInt( prime - 1 ) + 1;
		this.shift = rand.nextInt( prime );
		this.createTable();
	}
	
    public AbstractHashMap( int cap ) { this( cap, 109345121 ); }
    public AbstractHashMap() { this( 11 ); }
    
    // developer's utilities
	/* 
	*   Fonction de hachage => Multiplier, Ajouter et Diviser (MAD)
	*   The hashValue method relies on an original key’s hash code, as returned by its
	*	hashCode( ) method, followed by MAD compression based on a prime number and
	*	the scale and shift parameters that are randomly chosen in the constructor. 
	*/
	private int hashValue( K key ) {
		// h(x) = [(a*x + b) % p] % N
		// p is a large prime number (p > N)
		// N is the capacity
		// a: scale ; b: shift
		// key.hashCode() will already hash the value 
		return (int)( ( Math.abs( key.hashCode() * scale + shift ) % prime ) % capacity );
	}

	private void resize(int newCap) {
		List<Entry<K, V>> buffer = new ArrayList<>();
		
		for (Entry<K, V> entry : this.entrySet()) {
			buffer.add(entry);
		}
		
		this.capacity = newCap;
		this.createTable(); // based on updated capacity
		this.n = 0; // wil be recomputed while reinserting entries
		
		for( Entry<K,V> entry : buffer ) {
		    put( entry.getKey(), entry.getValue() );
		}
	}
	
	// --------------------------------------------
    // AbstractMap implemented method
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean containsKey(K key) {
		return bucketGet(hashValue(key), key) != null;
	}

	@Override
	public V get(K key) {
		return bucketGet(hashValue(key), key);
	}

	@Override
	public V put(K key, V value) {
		V retV = bucketPut( hashValue(key), key, value);
		
		// keep load factor <= 0.5
		if (n > (capacity / 2)) {
			resize(2*capacity - 1); // geometric serie extension 
		}
		return retV;
	}

	@Override
	public V remove(K key) {
		return bucketRemove( hashValue(key), key);
	}
	
	// -------------------------------
    // protected abstract methods to be implemented by subclasses
    protected abstract void createTable();
    protected abstract V bucketGet( int h, K k );
    protected abstract V bucketPut( int h, K k, V v );
    protected abstract V bucketRemove( int h, K k );
}
