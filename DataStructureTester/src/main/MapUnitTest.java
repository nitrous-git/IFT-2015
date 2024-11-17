package main;

import map.ChainHashMap;
import map.Entry;
import map.Map;
import map.ProbeHashMap;
import map.UnsortedTableMap;

public class MapUnitTest {
	public void UnsortedMapUnitTest() {
		// ------------------------------//
		// -------- UnsortedMap ---------// 
		// ------------------------------//
		System.out.println("-UnsortedMap Test -");
		Map<Integer,String> UM = new UnsortedTableMap<>(); // our Map
		
		UM.put(1, "Mazda");
		UM.put(2, "Ford");
		UM.put(4, "Nissan");
		System.out.println( UM );

		System.out.println( "get 2 : " + UM.get(2) );
		System.out.println( "get 3 : " + UM.get(3) );
		System.out.println( "get 4 : " + UM.get(4) );
		System.out.println( "size : " + UM.size() );
		
		UM.put(8, "Honda");
		System.out.println( UM );
		System.out.println( "size : " + UM.size() );
		
		System.out.println();
		for (Entry<Integer, String> entry : UM.entrySet()) {
			System.out.println(entry);
		}
		System.out.println();
		

		System.out.println( "get(4) : " + UM.get(4) );
		System.out.println( "remove(4) : " + UM.remove(4) );
		System.out.println( UM );
		System.out.println( "remove(2) : " + UM.remove(2) );
		System.out.println( "remove(1) : " + UM.remove(1) );
		System.out.println( "remove(1) : " + UM.remove(1) );
		System.out.println( UM );
		
		System.out.println();
	}
	
	
	public void ChainHashMapUnitTest() {
		// ------------------------------//
		// -------- ChainHashMap --------// 
		// ------------------------------//
		System.out.println("-ChainHashMap Test -");
		Map<Integer,String> CHM = new ChainHashMap<>(); // our Map
		
		CHM.put(1, "Mazda");
		CHM.put(2, "Ford");
		CHM.put(4, "Nissan");
		System.out.println( CHM.entrySet().toString() );
		
		System.out.println();
		for (Entry<Integer, String> entry : CHM.entrySet()) {
			System.out.println(entry);
		}
		System.out.println();
		
		System.out.println( "get 2 : " + CHM.get(2) );
		System.out.println( "get 3 : " + CHM.get(3) );
		System.out.println( "get 4 : " + CHM.get(4) );
		System.out.println( "size : " + CHM.size() );
		
		System.out.println( "containsKey 4 : " + CHM.containsKey(4) );
		System.out.println( "containsKey 6 : " + CHM.containsKey(6) );
		
		System.out.println();
		CHM.put(4, "Honda");
		System.out.println( "get 4 : " + CHM.get(4) );
		System.out.println( CHM.entrySet().toString() );
		
		CHM.put(8, "Bentley");
		System.out.println( CHM.entrySet().toString() );
		System.out.println( "size : " + CHM.size() );
		
		System.out.println();
		System.out.println( "remove(4) : " + CHM.remove(4) );
		System.out.println( CHM.entrySet().toString() );
		System.out.println( "remove(2) : " + CHM.remove(2) );
		System.out.println( "remove(1) : " + CHM.remove(1) );
		System.out.println( "remove(1) : " + CHM.remove(1) );
		System.out.println( CHM.entrySet().toString() );
		
		System.out.println();
	}
	
	
	public void ProbeHashMapUnitTest() {
		// ------------------------------//
		// -------- ProbeHashMap --------// 
		// ------------------------------//
		System.out.println("-ProbeHashMap Test -");
		Map<Integer,String> PHM = new ProbeHashMap<>(); // our Map
		
		PHM.put(1, "Mazda");
		PHM.put(2, "Ford");
		PHM.put(4, "Nissan"); 
		System.out.println( PHM.entrySet().toString() );
		
		System.out.println( "get 2 : " + PHM.get(2) );
		System.out.println( "get 3 : " + PHM.get(3) );
		System.out.println( "get 4 : " + PHM.get(4) );
		System.out.println( "size : " + PHM.size() );
		
		PHM.put(8, "Bentley");
		System.out.println( PHM.entrySet().toString() );
		System.out.println( "size : " + PHM.size() );
		
		System.out.println();
		System.out.println( "remove(4) : " + PHM.remove(4) );
		System.out.println( PHM.entrySet().toString() );
		System.out.println( "remove(2) : " + PHM.remove(2) );
		System.out.println( "remove(1) : " + PHM.remove(1) );
		System.out.println( "remove(1) : " + PHM.remove(1) );
		System.out.println( PHM.entrySet().toString() );
		
		System.out.println();
	}
	
}
