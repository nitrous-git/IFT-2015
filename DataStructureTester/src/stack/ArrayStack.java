package stack;


public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000;    // default capacity
    private E[] data;                           // array to store the elements, tableau de type E 
    private int t = -1;                         // index for the top of the stack

    
    public ArrayStack() { this( CAPACITY ); }   // construct stack with default capacity (1000 entre par defaut)
    
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		this.data = (E[]) new Object[capacity];
	}

	@Override
	public int size() {
		return (this.t + 1);
	}

	@Override
	public boolean isEmpty() {
		return (this.t == -1);
	}

	@Override
	public void push(E e) {
		if( this.size() == this.data.length ) throw new IllegalStateException( "Full stack" );
		// si on ne lance pas un exception 
		//on entre par exemple a l'indice 0 --> il faut incrementer ++t (juste avant) .. pas t++ sinon ca plante a la premier insertion (t = -1)
		// t++ --> utilise t et incremente, ++t --> incremente t et l'utilise
		this.data[++this.t] = e;
	}

	@Override
	public E top() {
    	// si la pile est vide
		if( this.isEmpty() ) return null;
		// sinon on cree un variable local de type E qui pointe sur le dernier elem insere
		return this.data[this.t];
	}

	@Override
	public E pop() {
		if( isEmpty() ) return null;
		E element = this.data[this.t];
		
		// memoire nettoyer == pas de pointeur dessus, on remet a null 
		this.data[this.t] = null;    // for garbage collection
		
		// on pourrait ecrire this.data[this.t--] = null;
		// pour combiner les deux lignes de code
		
		//on decremente la valeur de t, taille du tableau 
		this.t--;
		return element;
	}
	
    public String toString() {
		if( this.isEmpty() ) return "[]";
		String pp = "[ ";
		String e = "";
		
		for (int i = 0; i < this.size(); i++) {
			e += (String) this.data[i] + " ";
		}

		pp += e + "]";
		return pp;
    }

}
