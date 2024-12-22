/**
* Set is the interface for the ADT Set
* 
* @author      Francois Major
* @version     1.0
* @since       1.0 (2024.11.13(
*/

public interface Set<E> {
    int size();
    boolean isEmpty();
    boolean contains(E e);
    boolean add(E e);
}
