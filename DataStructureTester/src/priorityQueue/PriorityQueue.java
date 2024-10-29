package priorityQueue;

//manipule des cle valeur de Entry 
//interface parametrique a deux arguments 
public interface PriorityQueue<K,V> {
    int size();
    boolean isEmpty();
    Entry<K,V> insert( K key, V value ) throws IllegalArgumentException;
    Entry<K,V> min();
    Entry<K,V> removeMin();
}
    