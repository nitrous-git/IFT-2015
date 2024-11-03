package priorityQueue;

public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {
    void remove( Entry<K,V> e  ) throws IllegalArgumentException;
    void replaceKey( Entry<K,V> e, K k ) throws IllegalArgumentException;
    void replaceValue( Entry<K,V> e, V v ) throws IllegalArgumentException;
}