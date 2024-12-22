import java.util.PriorityQueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Comparator;
import java.util.List;

public class ResourceRedistribution {
	
	// using PQ as binary heap 
	PriorityQueue<Warehouse> surplusHeap;
	PriorityQueue<Warehouse> needHeap;
	List<Warehouse> warehouses;
	ObjectMapper mapper; 
	ArrayNode transfersJSONList;
	ObjectNode finalCapacityNode;
	
	public ResourceRedistribution() {
		 mapper = new ObjectMapper();
	}
	
	public void buildHeap(List<Warehouse> warehouses) {
        // Max-heap for surplus (warehouses with units > 50)
        surplusHeap = new PriorityQueue<>(Comparator.comparingInt((Warehouse w) -> w.capacity).reversed());

        // Min-heap for need (warehouses with units < 50)
        needHeap = new PriorityQueue<>(Comparator.comparingInt(w -> w.capacity));

        // Initialize heaps
        for (Warehouse warehouse : warehouses) {
            if (warehouse.capacity > 50) {
                surplusHeap.offer(warehouse);
            } else if (warehouse.capacity < 50) {
                needHeap.offer(warehouse);
            }
        }
        
        this.warehouses = warehouses;
	}
	
    public void redistributeResources() {
    	System.out.println();
        System.out.println("Resource Redistribution:");
        transfersJSONList = mapper.createArrayNode();

        // Redistribute resources
        while (!surplusHeap.isEmpty() && !needHeap.isEmpty()) {
            Warehouse surplusWarehouse = surplusHeap.poll();
            Warehouse needWarehouse = needHeap.poll();

            int surplusAmount = surplusWarehouse.capacity - 50;
            int needAmount = 50 - needWarehouse.capacity;

            int transferAmount = Math.min(surplusAmount, needAmount);

            surplusWarehouse.capacity -= transferAmount;
            needWarehouse.capacity += transferAmount;

            System.out.println("Transferred " + transferAmount + " units from Warehouse " + surplusWarehouse.id + " to Warehouse " + needWarehouse.id + ".");
    		ObjectNode transferNode = mapper.createObjectNode();
    		transferNode.put("From ", "Warehouse " + surplusWarehouse.id);
    		transferNode.put("To ", "Warehouse " + needWarehouse.id);
    		transferNode.put("Units ", transferAmount);
    		transfersJSONList.add(transferNode);
            
            // Re-add warehouses to heaps if they still have surplus or need
            if (surplusWarehouse.capacity > 50) {
                surplusHeap.offer(surplusWarehouse);
            }
            if (needWarehouse.capacity < 50) {
                needHeap.offer(needWarehouse);
            }
        }
    }
    
    public void printFinalResource() {
    	finalCapacityNode = mapper.createObjectNode();
        // Print final resource levels
        System.out.println("\nFinal Resource Levels:");
        for (Warehouse warehouse : warehouses) {
            System.out.println("warehouse " + warehouse.id + " : " + warehouse.capacity + " units");
    		finalCapacityNode.put("Warhouse " + warehouse.id, warehouse.capacity);
        }
	}
    
    public ObjectNode convertTask3ToJson() {
    	ObjectNode task3 = mapper.createObjectNode();
    	task3.set("Transfers", transfersJSONList);
    	task3.set("Final Resource Levels ", finalCapacityNode);
    	return task3;
    }
   
}
