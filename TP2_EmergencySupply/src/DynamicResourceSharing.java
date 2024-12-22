
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DynamicResourceSharing {
	
	DisjointSet<City> ds = new DisjointSet<>();
	List<City> cities;
	ObjectMapper mapper; // JSON mapper
	ObjectNode initalClustersNode;
	ArrayNode mergingStepsNode;
	ObjectNode clusterMembershipNode;
	ArrayNode queriesJSONList;

	public DynamicResourceSharing() {
		mapper = new ObjectMapper();
	}
	
	public void buildDisjointSet(List<City> cities) {
		this.cities = cities;
		for (City city : cities) {
			ds.add(city); 
		}
		
		System.out.println();
		System.out.println("Initial clusters : ");
		printInitClusters();
	}
	
	public void MergeClusters() {
		mergingStepsNode = mapper.createArrayNode();
		ObjectNode mergeNode;
		ArrayNode cityJSONList;
		System.out.println();
		City temp;
		City tempNext;
		
		// select first city 
		for (int i = 0; i < cities.size(); i++) {
			temp = cities.get(i);			
			// compare to other city
			for (int j = i+1; j < cities.size(); j++) {
				tempNext = cities.get(j);
				// both city are sharing exactly the same ressources 
				if (isSharingWarehouses(temp, tempNext)) {
					// create JSON node
					mergeNode = mapper.createObjectNode();
					cityJSONList = mapper.createArrayNode();
					
					// store union data 
					mergeNode.put("Action", "Merge");
					cityJSONList.add("City " + temp.getID());
					cityJSONList.add("City " + tempNext.getID());
					mergeNode.set("Cities", cityJSONList);
					mergeNode.put("Cluster After Merge", "Cluster " + (ds.find(temp)+1));
					mergingStepsNode.add(mergeNode);
					
					// console print
					System.out.println("Merging clusters of City "+ temp.getID() +" and City "+tempNext.getID()+"...");
					// Union of the sets
					ds.union(temp, tempNext);
				}
			}
		}
		
		printMergedClusters();
	}
	
	public boolean isSharingWarehouses(City city1, City city2) {
		return city1.warehouseUsed.containsAll(city2.warehouseUsed) && city2.warehouseUsed.containsAll(city1.warehouseUsed);
	}
	
	
	public void checkMembership() {
		queriesJSONList = mapper.createArrayNode();
		ObjectNode queryNode;
		System.out.println();
		City temp;
		City tempNext;
		String result = "";
		
		// select first city 
		for (int i = 0; i < cities.size(); i++) {
			temp = cities.get(i);
			// compare to other city
			for (int j = i+1; j < cities.size(); j++) {
				queryNode = mapper.createObjectNode();
				tempNext = cities.get(j);
				if (  ds.find(cities.get(i)) == ds.find(cities.get(j)) ) {
					result = "Yes";
				}else {
					result = "No";
				}
				System.out.println("Query: is City "+temp.getID()+" and City "+tempNext.getID()+" in the same cluster? \n" + result );
				queryNode.put("Query", "Are City "+temp.getID()+" and City "+tempNext.getID()+" in the same cluster?");
				queryNode.put("Result", result);
				queriesJSONList.add(queryNode);
			}
		}
	}
	
	public void printInitClusters() {		
		initalClustersNode = mapper.createObjectNode();
		// print clusters of all city
		for (City city : cities) {
			System.out.println("City "+city.getID() +" belongs to cluster : " + (ds.find(city) +1));
			initalClustersNode.put("City "+city.getID(), "Cluster " + (ds.find(city) +1));
		}
	}
	
	public void printMergedClusters() {	
		clusterMembershipNode = mapper.createObjectNode();
		// print clusters of all city
		for (City city : cities) {
			System.out.println("City "+city.getID() +" belongs to cluster : " + (ds.find(city) +1));
			clusterMembershipNode.put("City "+city.getID(), "Cluster " + (ds.find(city) +1));
		}
	}
	
    public ObjectNode convertTask4ToJson() {
    	ObjectNode task4 = mapper.createObjectNode();
    	task4.set("Initial Clusters", initalClustersNode);
    	task4.set("Merging Steps", mergingStepsNode);
    	task4.set("Cluster Membership After Merging", clusterMembershipNode);
    	task4.set("Queries", queriesJSONList);
    	return task4;
    }
}
