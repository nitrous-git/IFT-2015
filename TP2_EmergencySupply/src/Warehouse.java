
// class to warehouse data
class Warehouse extends NetworkSetup{
    //int id, x, y, 
    int capacity;

    public Warehouse(int id, int x, int y, int capacity) {	
    	super(id, x, y);
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
    
    public int getCapacity() {
		return capacity;
	}
    
    
    @Override
    public String toString() {
        return "Warehouse [ID=" + id + ", Coordinates=(" + x + ", " + y + "), Capacity=" + capacity + " units]";
    }
}
