import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class EmergencySupplyNetwork {
	
    List<NetworkSetup> cities;
    List<NetworkSetup> warehouses;
    int total_size;
    public AdjacencyMatrixGraph<NetworkSetup, Double> G;
    public PriorityQueue<City> Q;
    List<Map> allocationResult;

    // Constructor
	public EmergencySupplyNetwork(List<NetworkSetup> cities, List<NetworkSetup> warehouses, int total_size) {
		this.cities = cities;
		this.warehouses = warehouses;
		this.total_size = total_size;
		G = new AdjacencyMatrixGraph<>(total_size);
		Q = new PriorityQueue<>(new InnerCityPriorityComparator()); // city priority queue
	} 
	
	// Comparator for the PriorityQueue to order cities by their priority
	//---------------- nested InnerCityPriorityComparator class ----------------
	public class InnerCityPriorityComparator implements Comparator<City> {
	    @Override
	    public int compare(City c1, City c2) {
	        return Integer.compare(c2.getIntPriority(), c1.getIntPriority()); // Descending order
	    }
	}//------------ end of InnerEdge class ------------
	
    //Graph Builder
    public void buildGraph() {
        // add all vertex to graph 
        for (NetworkSetup c : cities) { G.insertVertex(c); }
        for (NetworkSetup w : warehouses) { G.insertVertex(w); }
        
        // add edges to all vertex 
        for (int i = 0; i < cities.size(); i++) { // iterate on cities
    		for (int j = cities.size(); j < total_size; j++) { // iterate on warehouses
    			G.insertEdge(G.vertices.get(i), G.vertices.get(j), 0.0); // link vertex
    		}
    	} 
	}
    //Returns Graph
    public AdjacencyMatrixGraph<NetworkSetup, Double> getGraph() {
		return this.G;
	}
    
    @SuppressWarnings({ "unchecked" })
	public void AllocateCityRessources() {    
    	allocationResult = new ArrayList<>();
        // Add cities to the priority queue
    	for (NetworkSetup c : cities) {
    		Q.add((City)c);
		}

        // Allocate resources based on priority
        while (!Q.isEmpty()) {
            City city = Q.poll();
            System.out.println("Allocating resources for City " + city.getID() + " (Priority : " + city.getPriority() + ")");            
            Map m = G.updateAllocations(G.findVertex(city), cities.size());
            m.put("Priority", city.getPriority());
            m.put("City", city.getID()); 
            allocationResult.add(m);
        }
        
        System.out.println();  
        System.out.println("Remaining Warehouse Capacities: ");           
        for (int i = cities.size(); i < G.vertices.size(); i++) {
            System.out.println("Warehouse " + G.vertices.get(i).getElement().id + ": " + ((Warehouse)G.vertices.get(i).getElement()).getCapacity() + " units");            
		}
        
	}
    
    
    // convert output to json object	
    // -------------------------------------------------------
	public ObjectNode convertTask12ToJson() {
        Double[][] costMatrix = G.getCostMatrix(cities.size(), warehouses.size());

        // need to be separate class
        ObjectMapper mapper = new ObjectMapper();
        
        //-------------------------------
        ObjectNode task1_2 = mapper.createObjectNode();
        ObjectNode graphReprensation = mapper.createObjectNode();

        // add graph representation to Json object node
        ArrayNode costMatrixJSONList = mapper.createArrayNode();
        for (int i = 0; i < costMatrix.length; i++) {
        	ObjectNode m = mapper.createObjectNode();
        	m.put("City", "City " + G.vertices.get(i).getElement().id);
        	for (int j = 0; j < costMatrix[i].length; j++) {
    			m.put("Warehouses " + G.vertices.get(j+cities.size()).getElement().id, costMatrix[i][j]);
    		}
        	
        	costMatrixJSONList.add(m);
    	}
        
        graphReprensation.set("Cost Matrix", costMatrixJSONList);

        // add ressources allocations to Json object node
        ArrayNode allocationJSONList = mapper.createArrayNode();
        
        for (int i = 0; i < allocationResult.size(); i++) {
        	Object[] values = allocationResult.get(i).values().toArray();
        	ObjectNode m = mapper.createObjectNode();
        	
        	m.put("City", "City " + values[values.length-1]);
        	m.put("Priority", String.valueOf(values[values.length-2]));
        	
        	// allocation from multiple warehouse
        	if (values.length > 2) {
        		ArrayNode allocatedList = mapper.createArrayNode();
        		m.set("Allocated", allocatedList);
        		
        		for (int j = values.length-3; j > 0; j-=2) {
    				ObjectNode allocatedNode = mapper.createObjectNode();
    				allocatedNode.put("Units", (int)values[j] );
    				allocatedNode.put("Warehouse", "Warehouse " + String.valueOf(values[j-1]));
    				allocatedList.add(allocatedNode);
				}
			}
        	allocationJSONList.add(m);
		}
        
        ObjectNode remainingCapacities = mapper.createObjectNode();
        
        // add remaining capacities to Json object node
        for (int i = cities.size(); i < G.vertices.size(); i++) {
        	remainingCapacities.put("Warehouse " + G.vertices.get(i).getElement().id, ((Warehouse)G.vertices.get(i).getElement()).getCapacity());        
		}
        
        // add to task1_2
        task1_2.set("Graph Representation", graphReprensation);
        task1_2.set("Ressource Allocation", allocationJSONList);
        task1_2.set("Remaining Capacities", remainingCapacities);
        //-------------------------------
        
        return task1_2;
	}
	 
}
