package priorityQueue;

import java.util.Comparator;

//comparateur par default si l'utilisateur ne donne pas de comparator en argument 
//en creant une implementation physique de AbstractPriorityQueue
public class DefaultComparator<E> implements Comparator<E> {

	@SuppressWarnings("unchecked")
	@Override
	public int compare(E a, E b) throws ClassCastException {
    	//on appelle compareTo de l'interface Comparable
    	//si on implemente dans la classe de l'objet (Comparable comme d'habitude) on ne pourra plus changer 
		return ((Comparable<E>)a).compareTo(b);
	}
	
}
