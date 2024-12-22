package map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class SkipListMap<K, V> extends AbstractSortedMap<K, V> {

    //----- inner class SkipNode -----
    protected static class SkipNode<K,V> {
		// attributes
		protected Entry<K,V> element; // reference to the element
		protected SkipNode<K,V> prev; // reference to the previous SkipNode
		protected SkipNode<K,V> next; // reference to the next SkipNode
		protected SkipNode<K,V> belo; // reference to the below SkipNode
		protected SkipNode<K,V> abov; // reference to the above SkipNode
		
		// constructor
		protected SkipNode( Entry<K,V> element,
				    SkipNode<K,V> prev, SkipNode<K,V> next,
				    SkipNode<K,V> belo, SkipNode<K,V> abov ) {
		    this.element = element;
		    this.prev = prev;
		    this.next = next;
		    this.belo = belo;
		    this.abov = abov;
		}
		
		// getters & setters
		public Entry<K,V> getElement() { return this.element; }
		public K getKey() { return this.element.getKey(); }
		public V getValue() { return this.element.getValue(); }
		public V setValue( V value ) { return ((MapEntry<K,V>)this.element).setValue( value ); }
		public void setElement( Entry<K,V> element ) { this.element = element; }
		protected SkipNode<K,V> getPrevious() { return this.prev; }
		protected SkipNode<K,V> getNext() { return this.next; }
		protected SkipNode<K,V> getBelow() { return this.belo; }
		protected SkipNode<K,V> getAbove() { return this.abov; }
		protected void setPrevious( SkipNode<K,V> prev ) { this.prev = prev; }
		protected void setNext( SkipNode<K,V> next ) { this.next = next; }
		protected void setBelow( SkipNode<K,V> belo ) { this.belo = belo; }
		protected void setAbove( SkipNode<K,V> abov ) { this.abov = abov; }
		@Override
		public String toString() { return "element = " + this.getElement() + " )"; }
    } //----- end inner SkipNode class
	
    // attributes
    private SkipNode<K,V> start;
    private int n; // number of elements // nb d'element dans la liste 0 4
    private int h; // height // hauteur de la plus haute column
    private K minusInfinite;
    private K plusInfinite; 
    private Random coinFlip = new Random(); // SDD probabiliste de 1/2
    //private int nbCompareCalls = 0; // combien de clee on ete compare pour acceder a une clee
    //private boolean trace = false; // activer ou pas le compteur de comparaison 

    // initializor
	public void init(K minusInfinite, K plusInfinite) {
		this.minusInfinite = minusInfinite;
		this.plusInfinite = plusInfinite;
		
		// 4 nodes sentinnelles 
		SkipNode<K,V> leftTop = new SkipNode<>( new MapEntry( minusInfinite, null ), null, null, null, null );
		SkipNode<K,V> rightTop = new SkipNode<>( new MapEntry( plusInfinite, null ),  leftTop, null, null, null );
		SkipNode<K,V> leftBottom = new SkipNode<>( new MapEntry( minusInfinite, null ), null, null, null, leftTop );
		SkipNode<K,V> rightBottom = new SkipNode<>( new MapEntry( plusInfinite, null ), leftBottom, null, null, rightTop );

		// on les attache ensemble
		leftTop.setNext( rightTop );
		leftTop.setBelow( leftBottom );
		leftBottom.setNext( rightBottom );
		rightTop.setBelow( rightBottom );
		
		// init attributs
		this.start = leftTop;
		this.n = 0;
		this.h = 0;
		
		// on met un seed pour avoir des valeurs comparable de random 
		this.coinFlip.setSeed( 34567 );
	}
	
    // constructors
    public SkipListMap( K minusInfinite, K plusInfinite ) {
		super();
		this.init( minusInfinite, plusInfinite );
    }
    
    public SkipListMap( K minusInfinite, K plusInfinite, Comparator<K> comp ) {
		super( comp );
		this.init( minusInfinite, plusInfinite );
    }
	
    // utilities
    // -----------------------------------------
    
    // return the entry in SkipNode p, or null if p is null or a sentinel
    private Entry<K,V> safeEntry( SkipNode<K,V> p ) {
		if( p == null ) return null;
		if( p.getValue() == null ) return null;
		return p.getElement();
    }
    
    private SkipNode<K, V> getFirst() {
    	SkipNode<K, V> getDown = this.start.getBelow(); // go the left sentinel's node below start
    	// get down until level 0
    	while (getDown.getBelow() != null) {
			getDown = getDown.getBelow();
		}
    	// return the next skip node
		return getDown.getNext();
	}
    
    private SkipNode<K, V> getLast() {
		SkipNode<K,V> getDown = this.start.getNext().getBelow(); // go the right sentinel's node below start
		while( getDown.getBelow() != null ) getDown = getDown.getBelow();
		// return the previous skip node
		return getDown.getPrevious();
	}
	
    private SkipNode<K, V> skipSearch(K key) {
		SkipNode<K, V> p = this.start;
		// si on peut descendre, on le fait 
		while (p.getBelow() != null) {
			p = p.getBelow();
			// pour arreter on a < 0, on a depasser de 1, on a trouver 
			while (compare(key, p.getNext().getKey()) >= 0) {
				p = p.getNext(); // on peut toujours comparer avec la sentinnelle, getNext toujours
			}
		}
		return p;
	}
    
    // si on insere au dernier niveau, on ajoute une couche de sentinelles
    private void increaseHeight() {
    	// pointeur sur les anciennes sentinnelles
		SkipNode<K,V> oldLeft = this.start;
		SkipNode<K,V> oldRight = this.start.getNext();
		// nouveau sentinelle
		SkipNode<K, V> newLeft = new SkipNode<>(new MapEntry<K, V>(this.minusInfinite, null), null, null, oldLeft, null);
		SkipNode<K, V> newRight = new SkipNode<>(new MapEntry<K, V>(this.plusInfinite, null), newLeft, null, oldRight, null);
		newLeft.setNext(newRight);
		oldLeft.setAbove(newLeft);
		oldRight.setAbove(newRight);
		this.h++;
		this.start = newLeft;
	}
    
    // insert apres 44, au dessus de null (niveau 0)    q = InsertAfterAbove( p, null, 47 )
    // insert apres p, above q, l'entre entry 
    // on fait le maillage a 4 pointeurs, on retourne le nouveau noeud
    private SkipNode<K, V> insertAfterAbove(SkipNode<K, V> p, SkipNode<K, V> q, Entry<K, V> entry) {
		SkipNode<K, V> novel = new SkipNode<>(entry, p, p.getNext(), q, null);
		// on met a jours les pointeurs
		p.getNext().setPrevious(novel);
		p.setNext(novel);
		if (q != null) {
			q.setAbove(novel);
		}
		return novel;
	}
    
    // p precede le noeud d'insertion
    private void skipInsert(K key, V value, SkipNode<K, V> p) { // insert key, value after p
		SkipNode<K, V> q = null; // q parcours les listes
    	int i = -1;  // current height // init en dessous de la liste 
    	
		// on tire a pile ou face, si on monte la tour, on recule et on insert afterAbove (niveau 2) etc... 
    	do {
			i++; // on commence au niveau 0
			// si la hauteur est plus haute, on ajoute un niveau sentinelle
			if (i > this.h) {
				this.increaseHeight();
			}
			
			 // insert after p above q
			q = insertAfterAbove(p, q, new MapEntry(key, value)); // insert value regardless of level
			
			// on recule tant que le above n'est pas nul
			while (p.getAbove() == null) {
				p = p.getPrevious();
			}
			p = p.getAbove();
		} while (this.coinFlip.nextBoolean() == true); // pile ou face 
    	
    	this.n++;
	}
	
	// Implemented method  
	// --------------------------------------------
	
	@Override
	public Entry<K, V> firstEntry() {
		return safeEntry(this.getFirst());
	}

	@Override
	public Entry<K, V> lastEntry() {
		return safeEntry(this.getLast());
	}

	// return the entry with least key greater than or equal to given key, if any
	@Override
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
		SkipNode<K, V> p = this.skipSearch(key);
		if (compare(key, p.getKey()) != 0) {
			p = p.getNext();
		}
		return safeEntry(p); 
	}

	// return the entry with greatest key less than or equal to given key, if any
	@Override
	public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
		return safeEntry(this.skipSearch(key));
	}

	// return the entry with greatest key strictly less than given key, if any
	@Override
	public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
		SkipNode<K, V> p = this.skipSearch(key);
		if (compare(key, p.getKey()) == 0) {
			p = p.getPrevious();
		}
		return safeEntry(p);
	}

	// return the entry with greatest key strictly greater than given key, if any
	@Override
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
		SkipNode<K, V> p = this.skipSearch(key);
		return safeEntry(p.getNext());
	}

	@Override
	public int size() {
		return this.n;
	}

	@Override
	public boolean containsKey(K key) {
		return this.get(key) != null;
	}

    // return the value associated with the specified key, or null if not found
	@Override
    public V get( K key ) {
		SkipNode<K,V> p = this.skipSearch( key );
		if( compare( key, p.getKey() ) != 0 ) {
		    return null; // no match
		}
		return p.getValue();
    }

    // associate the pair key-value, replacing existing value if any
	@Override
	public V put(K key, V value) {
		SkipNode<K, V> p = this.skipSearch(key);
		if (compare(key, p.getKey()) == 0) {
			return p.setValue(value);
		}
		this.skipInsert(key, value, p);
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub 
		return null;
	}

	
	// iterators
	// ---------------------------------------
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return snapshot(this.firstEntry().getKey(), null);
	}
	
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
		return snapshot(fromKey, toKey);
	}

    // support for snapshot iterators for entrySet and subMap
    private Iterable<Entry<K,V>> snapshot( K start, K stop ) {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		SkipNode<K,V> p = this.skipSearch( start );
		if( compare( start, p.getKey() ) != 0 ) {
		    //this.nbCompareCalls++;
		    p = p.getNext(); // if stopped on a smaller key, get the next one
		}
		while( p.getNext().getNext() != null && ( stop == null || compare( stop, p.getKey() ) > 0 ) )  {
		    buffer.add( p.getElement() );
		    p = p.getNext();
		}
		return buffer;
    }
    
	// Pretty printing
	// ----------------------------------------
    public String toString() {
		if( this.isEmpty() ) return "[]";
		String s = "[";
		SkipNode<K,V> curr = this.getFirst();
		while( curr.getNext().getNext() != null ) {
		    s += curr.getElement() + ", ";
		    curr = curr.getNext();
		}
		s += curr.getElement() + "] (h=" + this.h + ")" ;
		return s;
    }
}
