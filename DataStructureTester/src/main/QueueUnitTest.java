package main;

import queue.ArrayQueue;
import queue.LinkedQueue;
import queue.LinkedCircularQueue;
import queue.Queue;

public class QueueUnitTest {
	
	public void ArrayQueueUnitTest() {
		// --------------------------------------------
		// -------- ArrayQueue ---------// 
		// --------------------------------------------
		System.out.println("-ArrayQueue Test-");
		Queue<String> AQ = new ArrayQueue<>(4); // specific capacity = 4 
		
		AQ.enqueue("A");
		System.out.println(AQ);
		AQ.enqueue("B");
		AQ.enqueue("C");
		System.out.println(AQ);
		
		System.out.println(AQ.dequeue());
		System.out.println(AQ);
		
		AQ.enqueue("D");
		System.out.println(AQ);
		
		System.out.println(AQ.dequeue());
		System.out.println(AQ);
		
		AQ.enqueue("E");
		AQ.enqueue("F");

		System.out.println(AQ);
		
		System.out.println(AQ.dequeue());
		System.out.println(AQ);
		
		System.out.println(AQ.dequeue());
		System.out.println(AQ.dequeue());
		System.out.println(AQ.dequeue());
		System.out.println(AQ);
		
		System.out.println();
	}
	
	public void LinkedQueueUnitTest() {
		// --------------------------------------------
		// -------- LinkedQueue ---------// 
		// --------------------------------------------
		System.out.println("-LinkedQueue Test-");
		Queue<String> LQ = new LinkedQueue<>();
		
		LQ.enqueue("A");
		System.out.println(LQ);
		LQ.enqueue("B");
		LQ.enqueue("C");
		System.out.println(LQ);
		
		System.out.println(LQ.dequeue());
		System.out.println(LQ);
		
		LQ.enqueue("D");
		System.out.println(LQ);
		
		System.out.println(LQ.dequeue());
		System.out.println(LQ);
		
		LQ.enqueue("E");
		LQ.enqueue("F");

		System.out.println(LQ);
		
		System.out.println(LQ.dequeue());
		System.out.println(LQ);
		
		System.out.println(LQ.dequeue());
		System.out.println(LQ.dequeue());
		System.out.println(LQ.dequeue());
		System.out.println(LQ);
		
		System.out.println();
	}
	
	public void LinkedCircularQueueUnitTest() {
		// --------------------------------------------
		// -------- LinkedCircularQueue ---------// 
		// --------------------------------------------
		System.out.println("-LinkedCircularQueue Test-");
		Queue<String> LCQ = new LinkedCircularQueue<>();
		
		LCQ.enqueue("A");
		System.out.println(LCQ);
		LCQ.enqueue("B");
		LCQ.enqueue("C");
		System.out.println(LCQ);
		
		System.out.println(LCQ.dequeue());
		System.out.println(LCQ);
		
		LCQ.enqueue("D");
		LCQ.enqueue("E");
		LCQ.enqueue("F");
		System.out.println(LCQ);
		
		System.out.println(LCQ.dequeue());
		System.out.println(LCQ);
		
		((LinkedCircularQueue<String>) LCQ).rotate();
		System.out.println(LCQ);
		
		System.out.println(LCQ.dequeue());
		System.out.println(LCQ);
		
		
	}
	
}
