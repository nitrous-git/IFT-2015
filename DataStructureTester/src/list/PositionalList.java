package list;

import java.util.Iterator;

public interface PositionalList<E> extends Iterable<E> {
    int size();          // return the number of elements in the list
    boolean isEmpty();   // return a boolean indicating if the list is empty or not
    
    Position<E> first(); // return the first element of the list // return la position de l"elem, on doit faire un getElement 
    Position<E> last();  // return the last element of the list
    
    // return the Position immediately before Position p, or null if p is first 
    // dans un tableau normal on ferais i-1 
    Position<E> before( Position<E> p ) throws IllegalArgumentException;
    
    // return the Position immediately after Position p, or null if p is last
    Position<E> after( Position<E> p ) throws IllegalArgumentException;
    
    
    // cree la position associe avec la chaine de  character et insere la position 
    Position<E> addFirst( E e ); // insert element e at the front of the list and return its position
    Position<E> addLast( E e );  // insert element e at the back of the list and return its position
    
    // insert element e immediately before position p and return its position
    Position<E> addBefore( Position<E> p, E e ) throws IllegalArgumentException;
    // insert element e immediately after position p and return its position
    Position<E> addAfter( Position<E> p, E e ) throws IllegalArgumentException;
    
    
    E set( Position<E> p, E e ) throws IllegalArgumentException; // replace element at p and return replaced element
    
    // enleve la postion de la structure de donneee
    E remove( Position<E> p ) throws IllegalArgumentException;   // remove element at p and returns it (invalidate p)
    
    void moveBefore( Position<E> that, Position<E> toMove ) throws IllegalArgumentException;
    
    void moveAfter( Position<E> that, Position<E> toMove ) throws IllegalArgumentException;
    // moveAfter a faire /// :( 
    
    
    void sort(); // sort the positional list using natural ordering of its elements
    
    //iterateur normal
    Iterator<E> iterator(); // return an iterator on list elements
    
    // iterateur sur les positions 
    Iterable<Position<E>> positions(); // return an iterable on list positions
}
