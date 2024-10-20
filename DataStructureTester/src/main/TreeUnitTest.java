package main;

import list.Position;
import tree.ArrayBinaryTree;
import tree.LinkedBinaryTree;
import trie.ArrayTrie;


public class TreeUnitTest {
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void LinkedBinaryTreeUnitTest() {
		// --------------------------------------------
		// -------- LinkedBinaryTree ---------// 
		// --------------------------------------------
		System.out.println( "-LinkedBinaryTree Test-" );
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree();
		
		Position<Integer> root = tree.addRoot( 0 );
		Position<Integer> one = tree.addLeft( root, 1 );
		Position<Integer> two = tree.addRight( root, 2 );
		System.out.println(tree);
		System.out.println("left of root : " + tree.left(root));
		System.out.println("right of root : " + tree.right(root));
		
		Position<Integer> three = tree.addLeft( one, 3 );
		Position<Integer> four = tree.addRight( one, 4 );
		System.out.println(tree);
		System.out.println("left of one : " + tree.left(one));
		System.out.println("right of one : " + tree.right(one));
		
		System.out.println("depth of one : " + tree.depth(one));
		System.out.println("depth of four : " + tree.depth(four));
		
		System.out.println("heightBad : " + tree.heightBad());
		System.out.println("heightGood : " + tree.heightGood(root));
		
		tree.remove(four);
		System.out.println(tree);
		tree.remove(one);
		System.out.println(tree);
		System.out.println("left of root : " + tree.left(root));
				
		System.out.println();
	}
	
	
	public void ArrayTrieUnitTest() {
		// --------------------------------------------
		// -------- ArrayTrie Tree ---------// 
		// --------------------------------------------
		System.out.println( "-ArrayTrie Test-" );
		ArrayTrie arrayTrie = new ArrayTrie();
	    
		// Insert words into the Trie
		arrayTrie.insert( "bear" );
		arrayTrie.insert( "bell" );
		arrayTrie.insert( "bid" );
		arrayTrie.insert( "bull" );
		arrayTrie.insert( "bullfrog" );
		arrayTrie.insert( "buy" );
		arrayTrie.insert( "sell" );
		arrayTrie.insert( "stock" );
		arrayTrie.insert( "stop" );
		
		System.out.println( "Words in the ArrayTrie:" );
		arrayTrie.printWords();
		
		// Check if words start with a prefix
		System.out.println( arrayTrie.startsWith( "bul" ) ); // true
		System.out.println( arrayTrie.startsWith( "st" ) ); // true
		System.out.println( arrayTrie.startsWith( "sl" ) ); // false
		
		System.out.println();
	}
	
	public void ArrayBinaryTreeUnitTest() {
		// --------------------------------------------
		// -------- ArrayBinaryTree ---------// 
		// --------------------------------------------
		System.out.println( "-ArrayBinaryTree Test-" );
		ArrayBinaryTree<String> tree = new ArrayBinaryTree<String>();
		
		tree.addRoot("A");
		System.out.println(tree);
		
		tree.addLeft("B", 0);
		tree.addRight("C", 0);
		System.out.println(tree);
		
		tree.addLeft("D", 1);
		tree.addRight("E", 1);
		System.out.println(tree);
		
		tree.addLeft("F", 2);
		System.out.println(tree);
		
		System.out.println("Parent of A : " + tree.parent(0)); // index 0 is A
		System.out.println("Parent of B : " + tree.parent(1)); // index 1 is B
		System.out.println("Parent of C : " + tree.parent(2)); // index 2 is C  
		System.out.println("Parent of D : " + tree.parent(3)); // index 3 is D 
		System.out.println("Parent of F : " + tree.parent(5)); // index 5 is F, parent should be C 
		
		System.out.println("A is a internal : " + tree.isInternal(0)); 
		System.out.println("A is a leaf/external : " + tree.isExternal(0)); // A is internal 
		System.out.println("B is a internal : " + tree.isInternal(1)); // B is a leaf
		System.out.println("D is a leaf/external : " + tree.isExternal(3)); // D is external 
		System.out.println("D is internal : " + tree.isInternal(3)); 
		System.out.println("F is a leaf/external : " + tree.isExternal(5)); // F is a leaf
		
		System.out.println();
	}
}
