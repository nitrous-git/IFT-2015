package tree;

public class ArrayBinaryTree<E> {
	
	public static final int CAPACITY = 8; 
	@SuppressWarnings({ "unchecked" })
	private E[] array = (E[]) new Object[10];
	private int size = 0;
	protected E root = null;
	
	// empty constructor
	public ArrayBinaryTree() {}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	// ---------------------------------------------
	// add method 
	public E addRoot(E e) throws IllegalStateException {
		if (!this.isEmpty()) throw new IllegalStateException("Tree is not empty");
		this.root = e;
		array[0] = root;
		this.size++;
		return e;
	}
	
	// q : position of parent
	// p : position of child
	public void addLeft(E e, int q) throws IllegalArgumentException {
		if (array[q] == null) throw new IllegalArgumentException("q is not a parent");
		else {
			int p = 2*q + 1;
			array[p] = e;	
			this.size++;
		}
	}
	
	public void addRight(E e, int q) throws IllegalArgumentException {
		if (array[q] == null) throw new IllegalArgumentException("q is not a parent");
		else {
			int p = 2*q + 2;
			array[p] = e;	
			this.size++;
		}
	}
	
	
	
	// utility method 
	public E parent(int p) throws IllegalArgumentException {
		if (p == 0) return null; 
		if (array[p] == null) throw new IllegalArgumentException("p is not a child");
		
		int q = (int) Math.floor((p-1)/2);
		return array[q];
	}
	
	public int left(int q) {
		return 2*q + 1;
	}
	
	public int right(int q) {
		return 2*q + 2;
	}
	
	public boolean isExternal(int index) {
		if (left(index) > array.length || right(index) > array.length) return true; 
		return array[left(index)] == null & array[right(index)] == null;
	}
	
	public boolean isInternal(int index) {
		if (left(index) > array.length || right(index) > array.length) return false; 
		return array[left(index)] != null || array[right(index)] != null;
	}
	
	
	
	
    // ---------------------------------------------
    // pretty printing method
    public String toString() {
		String s = "{ ";
		for(E e : array)
		    s += e + " ";
		s += " }";
		return s;
    }
    
	
	
	
}
