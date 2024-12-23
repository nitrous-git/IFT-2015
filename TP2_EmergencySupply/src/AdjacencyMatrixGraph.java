
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
author = "Olivier Trudel"
version = "1.0"
date = "8 December 2024"

**/

public class AdjacencyMatrixGraph<V,E> extends AbstractGraph<V,E> {

	List<Vertex<V>> vertices;
	Edge<E>[][] adjacencyMatrix;
	
	// Construct an empty graph using the adjacency matrix format
	@SuppressWarnings("unchecked")
	public AdjacencyMatrixGraph(int size){		
		vertices = new ArrayList<Vertex<V>>();
		adjacencyMatrix = (Edge<E>[][]) new Edge<?>[size][size]; 
	}
	
    //---------------- nested Vertex class ----------------
    // A vertex of an adjacency map graph representation
    @SuppressWarnings("hiding")
	public class InnerVertex<V> implements Vertex<V> {
		private V element;
		private int index;
		private int ID, x, y;
	
		// Construct a new InnerVertex instance storing the given element
		public InnerVertex( V elem ) {
		    element = elem;
		    this.ID = ((NetworkSetup) elem).getID();
		    this.x = ((NetworkSetup) elem).getX();
		    this.y = ((NetworkSetup) elem).getY();
		}
		
		public void setIndex(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
		
		public int getID() {
			return ID;
		}
		
		// Return the element associated with the vertex
		public V getElement() { return element; }
		
		public String printer() {
			//return "elem : " + this.getElement() + ", index : " + this.getIndex();
			return "ID : " + this.ID + ", index : " + this.getIndex();
		}
		
    } //------------ end of InnerVertex class ------------
		
    //---------------- nested InnerEdge class ----------------
    // An edge between two vertices
    @SuppressWarnings("hiding")
	public class InnerEdge<E> implements Edge<E> {
		private E element;
		private Double distance;

		// Construct InnerEdge instance from u to v, storing the given element
		@SuppressWarnings("unchecked")
		public InnerEdge( Vertex<V> u, Vertex<V> v ) {
		    this.distance = calculateDistance((InnerVertex<V>)u, (InnerVertex<V>)v);
		    this.element = (E) getCost();
		}
	
		private Double calculateDistance(InnerVertex<V> u, InnerVertex<V> v) {
			return (Double) Math.sqrt(Math.pow((v.x - u.x), 2) +  Math.pow((v.y - u.y), 2));
		}
		
		public Double getCost() {
			double result = 0.0;
			if (this.distance <= 10) { result = this.distance; }
			if (this.distance <= 20 && this.distance > 10) { result = 2*this.distance; }
			if (this.distance > 20) { result = 3*this.distance; }
			return (double) (Math.round(result*100.0) /100.0); // round up two decimal point
		}
		
		
		// Return the element associated with the edge
		public E getElement() { return element; }
    } //------------ end of InnerEdge class ------------

    
    // Graph interface implemented method
    // ---------------------------------------------
	@Override
	public int numVertices() {
		return vertices.size();
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		return vertices;
	}


	@Override
	public Vertex<V> insertVertex(V element) {
		InnerVertex<V> newVertex = new InnerVertex<V>(element);
		vertices.add(newVertex);
		newVertex.setIndex(vertices.indexOf(newVertex));
		return newVertex;
	}
	// For city/warehouse and warehouse/city
	@Override
	public Edge<E> insertEdge(Vertex<V> city, Vertex<V> warehouse, E element) throws IllegalArgumentException {
		InnerEdge<E> newEdge = new InnerEdge<E>(city, warehouse);
		InnerVertex<V> IVc = (InnerVertex<V>)city;        
		InnerVertex<V> IVw = (InnerVertex<V>)warehouse;
	
		adjacencyMatrix[IVw.getIndex()][IVc.getIndex()] = newEdge; //newEdge.getCost();
		adjacencyMatrix[IVc.getIndex()][IVw.getIndex()] = newEdge; //newEdge.getCost();
		return newEdge;
	}
	
	
	// get cost Matrix from adjencyMatrix of the graph 
	public Double[][] getCostMatrix(int citySize, int warehouseSize) {
		Double[][] temp = new Double[adjacencyMatrix.length-warehouseSize][adjacencyMatrix[0].length-citySize];
		for(int i = 0; i < adjacencyMatrix.length - warehouseSize; i++) {
			for(int j = citySize; j < adjacencyMatrix[i].length; j++) {
				InnerEdge<E> IE = (InnerEdge<E>) adjacencyMatrix[i][j];
				temp[i][j - citySize] = IE.getCost();   // cost matrix
			}
		}
		return temp;
	}
	
	public InnerVertex<V> findVertex(NetworkSetup ns) {
		InnerVertex<V> temp = null;
		for (Vertex<V> v : vertices) {
			InnerVertex<V> IV = (InnerVertex<V>) v;
			if (IV.getElement().getClass() == ns.getClass() 
					&& ((NetworkSetup) IV.getElement()).getID() == ns.getID()) {
				temp = IV;
			}
		}
		return temp;
	}
	//Vertex index using previous function 	
	public int findVertexIndex(NetworkSetup ns) {
		return findVertex(ns).getIndex();
	}
	
	// Utilities 
	// ---------------------------------------------------
	// for a given vertex find the closest warehouse 
	public InnerVertex<V> minCostWarehouse(Vertex<V> city, int citySize, int demand) {
		InnerVertex<V> minCostWarehouse = null; 
		int cityRow = vertices.indexOf(city); // the row to check in the CostMatrix
		Double maxCost = (double) Integer.MAX_VALUE;
		for (int i = citySize; i < adjacencyMatrix.length; i++) {
			InnerEdge<E> IE = (InnerEdge<E>) adjacencyMatrix[cityRow][i];
			InnerVertex<V> temp = (InnerVertex<V>) vertices.get(i);
			if (IE.getCost() < maxCost && ((Warehouse) temp.getElement()).getCapacity() >= demand && ((Warehouse) temp.getElement()).getCapacity() != 0) {
				maxCost = IE.getCost();
				minCostWarehouse = (InnerVertex<V>) vertices.get(i);
			}
		}
		return minCostWarehouse;
	}
	
	// Update allocations of a city by check minCostWarehouse
	// if warehouse is in deficit, we look for the next minCostWarehouse
	// return a map of the allocated output
	@SuppressWarnings("rawtypes")
	public Map updateAllocations(InnerVertex<V> city, int citySize) {
		City c = ((City) city.getElement());
		Map<String, Integer> m = new LinkedHashMap<>(); // map of the result
		//System.out.println("City :" + c);
		
		InnerVertex<V> minCostWarehouse;
		Warehouse w; 

		int ressourceRemainder = 0; // how many ressources we still need 
		
		do {
			minCostWarehouse = minCostWarehouse(city, citySize, ressourceRemainder);
			w = ((Warehouse) minCostWarehouse.getElement());
			if (w.capacity - c.demand >= 0) { // warehouse is not in deficit
				if (ressourceRemainder != 0) { 
					//System.out.println("Allocated " + w);
					w.setCapacity(w.capacity - ressourceRemainder);
					System.out.println("Allocated " + ressourceRemainder + " units from Warehouse " + w.getID()); 
					c.warehouseUsed.add(w);
					m.put("Warehouse " + w.getID(), w.getID());
					m.put("Allocated remainder", ressourceRemainder);
					//System.out.println("Allocated " + w);
				}
				else { 
					//System.out.println("Allocated " + w);
					w.setCapacity(w.capacity - c.demand);
					System.out.println("Allocated " + c.demand + " units from Warehouse " + w.getID()); 
					c.warehouseUsed.add(w);
					m.put("Warehouse " + w.getID(), w.getID());
					m.put("Allocated demand", c.demand);
					//System.out.println("Allocated " + w);
				}
				ressourceRemainder = 0; // no more ressources needed
			}else { // warehouse will be in deficit 
				//System.out.println("Allocated " + w);
				ressourceRemainder = Math.abs(w.capacity - c.demand); // recalculate remainder
				System.out.println("Allocated " + w.capacity + " units from Warehouse " + w.getID());
				c.warehouseUsed.add(w);
				m.put("Warehouse " + w.getID(), w.getID());
				m.put("Allocated before deficit", w.capacity);
				w.setCapacity(0); // set the warehouse capacity to 0 
				//System.out.println("Allocated " + w);
			}
		} while (ressourceRemainder > 0);	
		
		return m;
	}
	// Warehouse elemnts
	public List<Warehouse> getWarehouse(int citySize) {
		List<Warehouse> temp = new ArrayList<>();
		for (int i = citySize; i < vertices.size(); i++) {
			temp.add((Warehouse)vertices.get(i).getElement());
		}
		return temp;
	}
	//city elements
	public List<City> getCities(int citySize){
		List<City> temp = new ArrayList<>();
		for (int i = 0; i < citySize; i++) {
			temp.add((City)vertices.get(i).getElement());
		}
		return temp;
	}
	
	// Pretty Printing
	// -----------------------------------------------
	public void printAdjacencyMatrix() {
		//for (Vertex<V> v : vertices) {
		//	System.out.println( ((InnerVertex<V>) v).printer() );
		//}
		
		for(int i = 0; i < adjacencyMatrix.length; i++) {
			for(int j = 0; j < adjacencyMatrix[i].length; j++) {
				InnerEdge<E> IE = (InnerEdge<E>) adjacencyMatrix[i][j];
				System.out.print(IE.getCost() + " ");
			}
			System.out.println();
		}
	}
	// Prints cost matrix associated with city and warehouse sizes
	public void printCostMatrix(int citySize, int warehouseSize) {
		System.out.println("Graph representation (Cost Matrix) : ");
		System.out.print("cities | ");
		for (int j = citySize; j < adjacencyMatrix.length; j++) {
			System.out.print("Warehouse " + ((NetworkSetup) vertices.get(j).getElement()).getID() + " | ");
		}
		
		System.out.println();
		for(int i = 0; i < adjacencyMatrix.length - warehouseSize; i++) {
			System.out.print("City " + ((NetworkSetup) vertices.get(i).getElement()).getID() + " | "); 
			for(int j = citySize; j < adjacencyMatrix[i].length; j++) {
				
				InnerEdge<E> IE = (InnerEdge<E>) adjacencyMatrix[i][j];
				System.out.print("    " + IE.getCost() + "      ");
			}
			System.out.println();
		}
		System.out.println();
	}	
	
}
