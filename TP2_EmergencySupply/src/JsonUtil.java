import java.io.FileWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {
	
	ObjectMapper mapper;
	ObjectNode obj;
	
	public JsonUtil() {
		mapper = new ObjectMapper();
		obj = mapper.createObjectNode();
	}
	
	public void addToJson(String taskName, ObjectNode taskNode) {
		obj.set(taskName, taskNode);
	}
	
	public void saveJsonFile() {
		
        try (FileWriter file = new FileWriter("Output_testCase1.json"))
        {
        	//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
    		file.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
    		file.flush();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} 
	}
	
}
