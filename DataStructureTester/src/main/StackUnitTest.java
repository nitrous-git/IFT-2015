package main;

import stack.*;

public class StackUnitTest {

	public void ArrayStackUnitTest() {
		// --------------------------------------------
		// -------- ArrayStack ---------// 
		// --------------------------------------------
		System.out.println("-ArrayStack Test-");
		Stack<String> AS = new ArrayStack<>();
		
		AS.push("A");
		AS.push("B");
		System.out.println( AS.top() );
		System.out.println( AS.pop() );
		System.out.println( AS.top() );
		
		AS.push("B");
		AS.push("C");
		AS.push("D");
		AS.push("E");
		System.out.println( AS );
		System.out.println( "Size of stack : " + AS.size() );
		AS.pop();
		AS.pop();
		System.out.println( AS );
		System.out.println( "Size of stack : " + AS.size() );
		AS.pop();
		AS.pop();
		System.out.println( AS );
		System.out.println( "Size of stack : " + AS.size() );
		
		System.out.println();
	}
	
	public void LinkedStackUnitTest() {
		// --------------------------------------------
		// -------- LinkedStack ---------// 
		// --------------------------------------------
		System.out.println("-LinkedStack Test-");
		Stack<String> LS = new LinkedStack<>();
		
		LS.push("A");
		LS.push("B");
		System.out.println( LS.top() );
		System.out.println( LS.pop() );
		System.out.println( LS.top() );
		
		LS.push("B");
		LS.push("C");
		LS.push("D");
		LS.push("E");
		System.out.println( LS );
		System.out.println( "Size of stack : " + LS.size() );
		LS.pop();
		LS.pop();
		System.out.println( LS );
		System.out.println( "Size of stack : " + LS.size() );
		LS.pop();
		LS.pop();
		System.out.println( LS );
		System.out.println( "Size of stack : " + LS.size() );
		
		System.out.println();
	}
	
	
	
	
}
