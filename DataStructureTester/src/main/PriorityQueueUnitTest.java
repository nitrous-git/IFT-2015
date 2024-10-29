package main;

import PriorityQueue.HeapPriorityQueue;

public class PriorityQueueUnitTest {
	 
	public void HeapPriorityQueueUnitTest() {
		// --------------------------------------------
		// -------- ArrayQueue ---------// 
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
	}
}
