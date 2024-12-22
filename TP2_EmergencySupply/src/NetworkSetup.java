// parent class for city and warehouses
public class NetworkSetup {
    int id, x, y, capacity;

    public NetworkSetup(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
    public int getID() {
		return id;
	}
    
    public int getX() {
		return x;
	}
    
    public int getY() {
		return y;
	}
}
