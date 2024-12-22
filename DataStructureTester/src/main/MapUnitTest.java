package main;

import map.ChainHashMap;
import map.Entry;
import map.Map;
import map.ProbeHashMap;
import map.SkipListMap;
import map.SortedTableMap;
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
		
		PHM.put(4, "Nissan"); 
		PHM.put(1, "Mazda");
		PHM.put(2, "Ford");
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
	
	public void SortedTableMapUnitTest() {
		// ------------------------------//
		// -------- SortedTableMap --------// 
		// ------------------------------//
		System.out.println("-SortedTableMap Test -");
		Map<Integer,String> SHM = new SortedTableMap<>(); // our Map
		
		SHM.put(4, "Nissan"); 
		SHM.put(1, "Mazda");
		SHM.put(2, "Ford");
		System.out.println( SHM.entrySet().toString() );
		
		System.out.println( "get 2 : " + SHM.get(2) );
		System.out.println( "get 3 : " + SHM.get(3) );
		System.out.println( "get 4 : " + SHM.get(4) );
		System.out.println( "size : " + SHM.size() );
		
		SHM.put(14, "Acura");
		SHM.put(8, "Bentley");
		System.out.println( SHM.entrySet().toString() );
		System.out.println( "size : " + SHM.size() );
		
		System.out.println();
		System.out.println( "remove(4) : " + SHM.remove(4) );
		System.out.println( SHM.entrySet().toString() );
		System.out.println( "remove(2) : " + SHM.remove(2) );
		System.out.println( "remove(1) : " + SHM.remove(1) );
		System.out.println( "remove(1) : " + SHM.remove(1) );
		System.out.println( SHM.entrySet().toString() );
		
		System.out.println();
	}
	
	public void SkipListMapUnitTest() {
		// ------------------------------//
		// -------- SkipListMap ---------// 
		// ------------------------------//
		System.out.println("-SortedTableMap Test -");
		Map<Integer, String> SLM = new SkipListMap<Integer, String>(Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		SLM.put( 12, "A" );
		System.out.println( SLM );
		SLM.put( 17, "B" );
		System.out.println( SLM );
		SLM.put( 55, "E" );
		System.out.println( SLM );
		SLM.put( 50, "D" );
		System.out.println( SLM );
		SLM.put( 20, "C" );
		System.out.println( SLM );

		// series of searches
		System.out.println("get(25) : " + SLM.get(25) );
		System.out.println("get(50) : " + SLM.get(50) );
		SLM.put( 47, "XX" );
	
		System.out.println( SLM );
	}
	
}
