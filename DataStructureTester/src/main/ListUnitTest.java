package main;

import java.util.Iterator;

import list.CircularlyLinkedList;
import list.DoublyLinkedList;
import list.LinkedPositionalList;
import list.PositionalList;
import list.List;
import list.Position;
import list.SinglyLinkedList;

public class ListUnitTest {
	
	public void SinglyUnitTest() {
		// -------------------------//
		// -------- Singly ---------// 
		// -------------------------//
		System.out.println("-SinglyLinkedList Test-");
		List<String> SL = new SinglyLinkedList<>();
		
		SL.add( 0, "A" );
		SL.add( 0, "B" );
		System.out.println( SL );
		System.out.println( SL.get( 0 ) );
		
		// set() will set new elem at index i and return the oldElement
		System.out.println( "Deleted element : " + SL.set( 1, "C" ) ); 
		System.out.println( SL );
		
		SL.add( 0, "X1" ); // comme addFirst()
		SL.add( 3, "X2" ); // comme addLast()
		System.out.println( SL );
		
		SL.add(1, "A");
		System.out.println( SL );
		
		System.out.println( SL.get( 4 ) );
		
		System.out.println( SL.remove( 2 ) );
		System.out.println( SL );
		
		System.out.println( SL.remove( "A" ) );
		System.out.println( SL );
		
		System.out.println( SL.remove( 1 ) );
		System.out.println( SL );
		
		SL.addFirst("Y1");
		SL.addLast("Y2");
		
		// test SinglyLinkedList iterator
		Iterator<String> itSL = SL.iterator();
		System.out.print( "list: [ " );
		while( itSL.hasNext() )
		    System.out.print( itSL.next() + " " );
		System.out.println( "]" );
		
		// test reverse method 
		((SinglyLinkedList<String>) SL).reverse();
		System.out.println( SL );
		
		((SinglyLinkedList<String>) SL).recursiveReverse();
		System.out.println( SL );

		System.out.println();
		
	}
	
	public void DoublyUnitTest() {
		// -------------------------//
		// -------- Doubly ---------// 
		// -------------------------//
		System.out.println("-DoublyLinkedList Test-");
		List<String> DL = new DoublyLinkedList<>();
		
		DL.add( "A" );
		DL.add( "B" );
		System.out.println( DL );
		
		DL.addFirst("X1");
		DL.addLast("X2");
		System.out.println( DL );
		
		// test DoublyLinkedList iterator
		// forward traversal
		((DoublyLinkedList<String>) DL).setIteratorMode(false);
		Iterator<String> itDL = DL.iterator();
		
		System.out.print( "list: [ " );
		while( itDL.hasNext() )
		    System.out.print( itDL.next() + " " );
		System.out.println( "]" );
	
		DL.addFirst("Y1");
		DL.addLast("Y2");
		System.out.println( DL );
		
		// backward traversal
		((DoublyLinkedList<String>) DL).setIteratorMode(true);
		Iterator<String> itDLReverse = DL.iterator();
		
		System.out.print( "Reversed list: [ " );
		while( itDLReverse.hasNext() )
		    System.out.print( itDLReverse.next() + " " );
		System.out.println( "]" );
		
		System.out.println();
		
	}
	
	public void CircularlyUnitTest() {
		// ----------------------------------//
		// -------- Circular Singly ---------// 
		// ----------------------------------//
		System.out.println("-Circular SinglyLinkedList Test-");
		List<String> CL = new CircularlyLinkedList<>();
		
		CL.addFirst( "X2" );
		CL.addFirst( "X1" );
		CL.addLast( "A" );
		CL.addLast( "B" );
		System.out.println( CL );
		
		((CircularlyLinkedList<String>) CL).rotate();
		System.out.println( CL );
		((CircularlyLinkedList<String>) CL).rotate();
		System.out.println( CL );
		
		// test CircularlySinglyLinkedList iterator
		Iterator<String> itCL = CL.iterator();
		System.out.print( "list: [ " );
		while( itCL.hasNext() )
		    System.out.print( itCL.next() + " " );
		System.out.println( "]" );
		
		System.out.println();
	}
	
	public void LinkedPositionalListUnitTest() {
		// -----------------------------------------//
		// -------- Linked Positional List ---------// 
		// -----------------------------------------//
		System.out.println("-Linked Positional List Test-");
		PositionalList<Integer> PL = new LinkedPositionalList<>();
		Position p, q, r, s;
		
		System.out.println( PL );
		System.out.println();
		
		p = PL.addLast( 1 );
		System.out.println( "position p : " + p );
		System.out.println( PL );
		System.out.println( "first : "  +  PL.first() );
		System.out.println();
		
		q = PL.addLast( 3 );
		System.out.println( "position q : " + q );
		System.out.println( PL );
		System.out.println( "last : " +  PL.last() );
		System.out.println();
		
		r = PL.addAfter(p, 2);
		System.out.println( "position r : " + r );
		System.out.println( PL );
		System.out.println( "last : " +  PL.last() );
		System.out.println();
		
		s = PL.addBefore(p, 0);
		System.out.println( "position s : " + s );
		System.out.println( PL );
		System.out.println( "first : " +  PL.first() );
		System.out.println();
		
		PL.remove(q);
		// System.out.println( "position q : " + q ); // Position no longer valid
		System.out.println( PL );
		System.out.println( "last : " +  PL.last() );
		System.out.println();
		
		PL.set(s, 9);
		PL.set(r, 9);
		System.out.println( PL );
		System.out.println( s.getElement() );
		System.out.println();
		
		// test foreach on the positions of PositionalList
		System.out.println( PL );
		for( Position<Integer> pos : PL.positions() )
		    System.out.println( pos );
		
		
		Iterator<Position<Integer>> itpPL = PL.positions().iterator();
		while( itpPL.hasNext() ) {
		    Integer current = itpPL.next().getElement();
		    System.out.print( current );
		    if( current > 5 ) {
				itpPL.remove();
				System.out.println( " removed" );
		    }
		    else {
		    	System.out.println( " kept" );
		    }
		}
		System.out.println( PL );
		System.out.println();
		
		s = PL.addAfter(p, 2);
		r = PL.addAfter(s, 3);
		System.out.println( PL );
		for( Position<Integer> pos : PL.positions() )
		    System.out.println( pos );
		System.out.println();
		
		PL.moveBefore(p, s);
		System.out.println( PL );
		PL.moveBefore(s, r);
		System.out.println( PL );
		System.out.println();
		
		PL.moveAfter(p, s);
		System.out.println( PL );
		PL.moveAfter(s, r);
		System.out.println( PL );
		System.out.println();
		
		q = PL.addFirst(9);
		System.out.println( PL );
		
		PL.sort();
		System.out.println( PL );
		
		
		
		System.out.println();
	}
}
