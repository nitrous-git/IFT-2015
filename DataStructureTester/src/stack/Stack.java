package stack;

public interface Stack<E> {
	// ressemble beaucoup java.util.Stack (qui est une classe et non une interface)
	// ici on en a fait un interface pour le cours
	// voir le PDF 
	//search() combien de pop a faire pour trouver l'elem, pas implemente ici 
    public int     size();         // return the number of elements in the stack
    public boolean isEmpty();      // return true if the stack is empty, false otherwise
    public void    push( E e );    // insert element e at the top of the stack
    public E       top();          // return the element at the top of the stack, null if empty
    public E       pop();          // remove and return the element at the top of the stack, null if empty
}