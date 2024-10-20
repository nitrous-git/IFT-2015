package list;

public interface Position<E> {
	// return the element stored at this Position
	E getElement() throws IllegalStateException;
}
