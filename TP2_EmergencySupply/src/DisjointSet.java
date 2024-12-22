import java.util.ArrayList;
import java.util.List;

public class DisjointSet<E> implements Set<E> {

    // List of elements => index in this list maps to index in 'parent'
    private final List<E> elements;
    // parent.get(i) => index of the parent of the element at 'elements.get(i)'
    private final List<Integer> parent;
    private int size;

    // Constructor
    public DisjointSet() {
        elements = new ArrayList<>();
        parent = new ArrayList<>();
        size = 0;
    }

    // Set interface implemented method
    // -----------------------------------------
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        return elements.contains(e);
    }

    @Override
    public boolean add(E e) {
        // If already present, don't add
        if (contains(e)) {
            return false;
        }
        // add element to the list
        elements.add(e);
        // make parent index itself
        parent.add(elements.size() - 1);
        size++;
        return true;
    }

    // UnionFind
    // ----------------------------------------------------------

    // private find index of the representative of the set
    // This is the classic unionfind (no path compression)
    private int findIndex(int i) {
        if (parent.get(i) == i) {
            return i;
        }
        return findIndex(parent.get(i));
    }

    // public find of element e 
    // return -1 if the element not exist
    public int find(E e) {
        int idx = elements.indexOf(e);
        if (idx == -1) {
            return -1;
        }
        return findIndex(idx);
    }

    // Union of two sets, e1 and e2 
    // return if already in the same set 
    public void union(E e1, E e2) {
        int i = elements.indexOf(e1);
        int j = elements.indexOf(e2);
        
        int irep = findIndex(i);
        int jrep = findIndex(j);
        
        // If already in the same set, do nothing
        if (irep == jrep) {
            return;
        }
        
        // Simple union: attach the root of j's set to i's root
        parent.set(jrep, irep);
    }
}
