
// class to store city

import java.util.ArrayList;
import java.util.List;

class City extends NetworkSetup{
    //int id, x, y,
	int demand;
    String priority; 
    int intPriority; // Higher value means higher priority
    List<Warehouse> warehouseUsed; 

    public City(int id, int x, int y, int demand, String priority) {
    	super(id, x, y);
        this.demand = demand;
        this.priority = priority;
        this.intPriority = convertPriorityToInt(); 
        warehouseUsed = new ArrayList<>();
    }
    
    public int convertPriorityToInt() {
    	int p = 0;
    	switch (priority) {
			case "High": 
				return p = 3;
			case "Medium": 
				return p = 2;
			case "Low": 
				return p = 1;
    	}
    	return p; // return p = 0 by default
    }
    
    public int getIntPriority() {
		return intPriority;
	}
    
    public String getPriority() {
		return priority;
	}
    
    public void setDemand(int demand) {
		this.demand = demand;
	}
    
    public int getDemand() {
		return demand;
	}
    
    @Override
    public String toString() {
        return "City [ID=" + id + ", Coordinates=(" + x + ", " + y + "), Demand=" + demand + " units, Priority=" + priority + "]";
    }
}
