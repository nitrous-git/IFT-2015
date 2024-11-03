package main;

import java.util.Arrays;

import priorityQueue.Entry;
import priorityQueue.HeapAdaptablePriorityQueue;
import priorityQueue.HeapPriorityQueue;

public class PriorityQueueUnitTest {
	 
	public void HeapPriorityQueueUnitTest() {
		// --------------------------------------------
		// -------- HeapPriorityQueue ---------// 
		// --------------------------------------------
		System.out.println();
		System.out.println("-HeapPriorityQueue Test-");
		HeapPriorityQueue<Integer, String> HPQ = new HeapPriorityQueue<>();
		
		HPQ.insert( 5, "A" );
		System.out.println( "insert(5,A) " + HPQ );
		HPQ.insert( 9, "C" );
		System.out.println( "insert(9,C) " + HPQ );
		HPQ.insert( 3, "B" );
		System.out.println( "insert(3,B) " + HPQ );
		
		System.out.println();
		System.out.println( "min() " + HPQ.min() );
		System.out.println( "removeMin() " + HPQ.removeMin() );
		
		System.out.println();
		HPQ.insert( 7, "D" );
		System.out.println( "insert(7,D) " + HPQ );
		
		System.out.println( "removeMin() " + HPQ.removeMin() );
		System.out.println( "removeMin() " + HPQ.removeMin() );
		System.out.println( "removeMin() " + HPQ.removeMin() );
		System.out.println( "removeMin() " + HPQ.removeMin() );
		System.out.println( "isEmpty() " + HPQ.isEmpty() );
		
		System.out.println();
	    String[] value = new String[] { "Flavie" ,"Alice", "Dave", "Bob", "Charlie"};
	    Integer[] key = new Integer[] {98, 91, 80, 72, 46};
	    
		HeapPriorityQueue<Integer, String> HPQ_2 = new HeapPriorityQueue<>(key, value);
		System.out.println( "Key : "+ Arrays.toString(key) );
		System.out.println( "Value : "+ Arrays.toString(value) );
		System.out.println();
		System.out.println("Constructed heap");
		System.out.println( "Heap : "+ HPQ_2 );
		
		System.out.println();
		HPQ_2.heapSort();
		System.out.println("In descending order");
		System.out.println("After HeapSort : " + HPQ_2);
		
		System.out.println();
		HPQ_2.heapify();
		System.out.println("Back to heap");
		System.out.println("After Heapify : " + HPQ_2);
	}
	
	public void HeapAdaptablePriorityQueueUnitTest() {
		// --------------------------------------------
		// -------- HeapAdaptablePriorityQueue ---------// 
		// --------------------------------------------
		System.out.println();
		System.out.println( "-HeapAdaptablePriorityQueue test-" );
		HeapAdaptablePriorityQueue<Integer,String> HAPQ = new HeapAdaptablePriorityQueue<>();
	
		Entry<Integer,String> e;
		e = HAPQ.insert( 5, "A" );
		System.out.println( HAPQ );
		HAPQ.insert( 9, "C" );
		System.out.println( HAPQ );
		HAPQ.insert( 3, "B" );
		System.out.println( HAPQ );
		
		System.out.println();
		System.out.println( "min() " + HAPQ.min() );
		HAPQ.remove( e );
		System.out.println( "remove( " + e + " ) " + HAPQ );
		
		System.out.println();
		e = HAPQ.insert( 7, "D" );
		System.out.println( HAPQ );
		HAPQ.replaceKey(e, 8);
		System.out.println( "replaceKey(e, 8) : "+ HAPQ );
		HAPQ.replaceKey(e, 1);
		System.out.println("replaceKey(e, 1) : "+ HAPQ );
		
		HAPQ.replaceValue( e, "K" );
		System.out.println( "replaceValue( e, 'K' ) " + HAPQ );
		
		System.out.println();
		System.out.println( "min() " + HAPQ.min() );
		System.out.println( "removeMin() " + HAPQ.removeMin() );
		System.out.println( "removeMin() " + HAPQ.removeMin() );
		System.out.println( "removeMin() " + HAPQ.removeMin() );
		System.out.println( "isEmpty() " + HAPQ.isEmpty() );
	}
}
 