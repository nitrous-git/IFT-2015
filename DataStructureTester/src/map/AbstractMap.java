package map;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {

    //----- inner MapEntry class -----
    protected static class MapEntry<K,V> implements Entry<K,V> {
		private K k; // for the key
		private V v; // for the value
		public MapEntry( K key, V value ) {
		    this.k = key;
		    this.v = value;
		}
		
		// getters
		@Override
		public K getKey() { return this.k; }
		@Override
		public V getValue() { return this.v; }
		
		// developer's utilities
		protected void setKey( K key ) { this.k = key; }
		
		// parce que dasn un map on peut pas avoir deux fois la meme
		protected V setValue( V value ) {
		    V old = v;
		    this.v = value;
		    return old;
		}
		
		public String toString() { return "<" + this.getKey() + ":" + this.getValue() + ">"; }    
    } //----- end inner MapEntry class -----
	
    @Override
    public boolean isEmpty() { return this.size() == 0; }
    
    
    
    
    
    
    // ---------- 2 iterators 
    
    // support for public keySet method
    // iterator de bas niveau pour les entry
    private class KeyIterator implements Iterator<K> {
		private Iterator<Entry<K,V>> entries = entrySet().iterator(); // reuse entrySet (in interface Map)
		public boolean hasNext() { return entries.hasNext(); }
		public K next() { return entries.next().getKey(); } // return key
		public void remove() { throw new UnsupportedOperationException(); }
    }
    
    // retourne l'instance de keyIterator
    private class KeyIterable implements Iterable<K> {
		public Iterator<K> iterator() { return new KeyIterator(); }
    }
    
    @Override
    public Iterable<K> keySet() { return new KeyIterable(); }

    
    
    
    
    // support for public values
    private class ValueIterator implements Iterator<V> {
		private Iterator<Entry<K,V>> entries = entrySet().iterator(); // reuse entrySet (in interface Map)
		public boolean hasNext() { return entries.hasNext(); }
		public V next() { return entries.next().getValue(); } // return value
		public void remove() { throw new UnsupportedOperationException(); }
    }
    
    private class ValueIterable implements Iterable<V> {
    	public Iterator<V> iterator() { return new ValueIterator(); }
    }
    
    @Override
    public Iterable<V> values() { return new ValueIterable(); }
    
}
