import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    BufferedReader br;
    List<NetworkSetup> cities = new ArrayList<>();
    List<NetworkSetup> warehouses = new ArrayList<>();

    // Method to read and process the file
    public void read(String fileName) {
        try {
            String filePath = "src/"+fileName;
            br = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Parse Cities
                if (line.startsWith("City")) {
                    String[] partsC = line.split(", ");
                    if (partsC.length > 1) {
	                    int id = Integer.parseInt(partsC[0].split("ID = ")[1]);
	                    String coordinateX = partsC[1].split("Coordinates = ")[1].replace("(", "").replace(")", "");
	                    String coordinateY = partsC[2].replace(")", "");
	                    int x = Integer.parseInt(coordinateX);
	                    int y = Integer.parseInt(coordinateY);
	                    int demand = Integer.parseInt(partsC[3].split("Demand = ")[1].split(" ")[0]);
	                    String priority = partsC[4].split("Priority = ")[1];
	                    cities.add(new City(id, x, y, demand, priority));
                    }
                }

                // Parse Warehouses
                else if (line.startsWith("Warehouse")) {
                    String[] partsW = line.split(", ");
                    if (partsW.length > 1) {
	                    int id = Integer.parseInt(partsW[0].split("ID = ")[1]);
	                    String coordinateX = partsW[1].split("Coordinates = ")[1].replace("(", "").replace(")", "");
	                    String coordinateY = partsW[2].replace(")", "");
	                    int x = Integer.parseInt(coordinateX);
	                    int y = Integer.parseInt(coordinateY);
	                    int capacity = Integer.parseInt(partsW[3].split("Capacity = ")[1].split(" ")[0]);
	                    warehouses.add(new Warehouse(id, x, y, capacity));
                    }
                }
            }
	//Catch cases off original try	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public List<NetworkSetup> getCities() {
		return cities;
	}
    
    public List<NetworkSetup> getWarehouses() {
		return warehouses;
	}
    
    // Pretty printing 
    // -------------------------------------------------
    public void print() {
        // Print results
        System.out.println("Cities:");
        for (NetworkSetup city : cities) {
            System.out.println(city);
        } 
        
        System.out.println();
        System.out.println("Warehouses:");
        for (NetworkSetup warehouse : warehouses) {
            System.out.println(warehouse);
        }
	}
}
