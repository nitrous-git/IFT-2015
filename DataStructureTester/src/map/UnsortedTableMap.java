package map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
	
    // physical storage for the map entries
    private List<MapEntry<K,V>> table = new ArrayList<>();

    // construct an initially empty map
    public UnsortedTableMap() {}
   
    // private utilities
    // return the index of the entry with key k, or -1 if not found
    // dans l'ordre de O(n)
    private int findIndex(K k) {
		int n = this.table.size();
		for (int i = 0; i < n; i++) {
			if (this.table.get(i).getKey().equals(k)) {
				return i; // index is found
			}
		}
		return -1; // no such entry convention 
	}
		
	// --------------------------------------------
    // AbstractMap implemented method
	
	
	@Override
	public int size() {
		return this.table.size();
	}

	@Override
	public boolean containsKey(K key) {
		return this.findIndex(key) != -1;
	}

    // return the value associated with the specified key, or null if not found
	@Override
	public V get(K key) {
		int index = this.findIndex(key);
		if (index == -1) {
			return null;
		}
		return this.table.get(index).getValue();
	}

	@Override
	public V put(K key, V value) {
		int index = this.findIndex(key);
		
		if (index == -1) {
			this.table.add( new MapEntry<>(key, value) ); // on ajoute a la fin du tableau 
			return null; 
		} else {
			return this.table.get(index).setValue(value);
		}
	}

	@Override
	public V remove(K key) {
		int index = this.findIndex(key);
		// no such entry found
		if (index == -1) { 
			return null;
		}
		
		int n = this.size();
		V retV = this.table.get(index).getValue();
		
		// on echange la valeur a retirer avec le dernier, et on enleve le dernier 
		// permet un remove d'ordre O(1) avec ArrayList 
		// cette astuce ne marche pas si le tableau est triee 
		// remove in O(1)
		if (index != n-1) {
			// swap index entry with last entry
			this.table.set(index, this.table.get( n-1 ));
		}
		this.table.remove( n-1 ); // remove the last entry
		
		// remove in O(n)
		//this.table.remove( j );
		
		return retV;
	}

	
	
	// iterator -----------
	
	// support for entrySet public method
	private class EntryIterator implements Iterator<Entry<K, V>> {
		int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < table.size();
		}

		@Override
		public Entry<K, V> next() {
		    if( index == table.size() ) throw new NoSuchElementException();
		    // on prend la valeur j et on fait j++ apres 
		    // si on avais, ++j on increment avant d'acceder a la valeur 
			return (MapEntry<K, V>) table.get(index++);
		}
		
		public void remove() { throw new UnsupportedOperationException(); }
	}
	
	private class EntryIterable implements Iterable<Entry<K, V>>{

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}
	
	@Override
	public String toString() {
		return table.toString();
	}
}
