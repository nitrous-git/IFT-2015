import java.util.List;

public class NetworkApp {
    // Main method for testing
	public static void main(String[] args) {
    	JsonUtil jsonUtil = new JsonUtil();
        FileUtil fileUtil = new FileUtil();
        fileUtil.read("TestCase3.txt"); 
        
        List<NetworkSetup> cities = fileUtil.getCities();
        List<NetworkSetup> warehouses = fileUtil.getWarehouses();
        int total_size = (cities.size() + warehouses.size());
        //fileUtil.print();
        
        // Task 1 and 2
        EmergencySupplyNetwork ESN = new EmergencySupplyNetwork(cities, warehouses, total_size);
        ESN.buildGraph();
        ESN.getGraph().printCostMatrix(cities.size(), warehouses.size());
        ESN.AllocateCityRessources();
        jsonUtil.addToJson("Task 1 and 2", ESN.convertTask12ToJson());
        
        // Task 3
        ResourceRedistribution RR = new ResourceRedistribution();
        RR.buildHeap(ESN.getGraph().getWarehouse(cities.size()));
        RR.redistributeResources();
        RR.printFinalResource();
        jsonUtil.addToJson("Task 3", RR.convertTask3ToJson());
        
        // Task 4
        DynamicResourceSharing DRS = new DynamicResourceSharing();
        DRS.buildDisjointSet(ESN.getGraph().getCities(cities.size()));
        DRS.MergeClusters();
        DRS.checkMembership();
        jsonUtil.addToJson("Task 4", DRS.convertTask4ToJson());
        
        // save the output JSON file 
        jsonUtil.saveJsonFile();
    }
}
