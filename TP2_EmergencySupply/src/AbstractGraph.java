
public abstract class AbstractGraph<V, E> implements Graph<V,E> {
	
	// empty constructor 
	public AbstractGraph() { }
	
	// Unimplemented method of graph interface
	// --------------------------------------------------------
	@Override
	public Iterable<Edge<E>> edges() {
		return null;
	}

	@Override
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		return 0;
	}

	@Override
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		return 0;
	}


	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
		return null;
	}

	@Override
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		// not implemented
	}

	@Override
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		// not implemented
	}

	@Override
	public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
		return null;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		return null;
	}
	
	@Override
	public int numEdges() {
		return 0;
	}
	
	@Override
	public Edge<E> getEdge(Vertex<V> city, Vertex<V> warehouse) throws IllegalArgumentException {
		return null;
	}
	
	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> city) throws IllegalArgumentException {
		return null;
	}
	
}
