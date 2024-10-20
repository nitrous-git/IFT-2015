package main;

import list.Position;
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
}
