package main;

public class DataStructureTester {
	public static void main(String[] args) {
		
		ListUnitTest listUnitTest = new ListUnitTest();
		listUnitTest.SinglyUnitTest();
		listUnitTest.DoublyUnitTest();
		listUnitTest.CircularlyUnitTest();
		listUnitTest.LinkedPositionalListUnitTest();
		
		StackUnitTest stackUnitTest = new StackUnitTest();
		stackUnitTest.ArrayStackUnitTest();
		stackUnitTest.LinkedStackUnitTest();
	
		QueueUnitTest queueUnitTest = new QueueUnitTest();
		queueUnitTest.ArrayQueueUnitTest();
		queueUnitTest.LinkedQueueUnitTest();
		queueUnitTest.LinkedCircularQueueUnitTest();
		
		TreeUnitTest treeUnitTest = new TreeUnitTest();
		treeUnitTest.LinkedBinaryTreeUnitTest();
		treeUnitTest.ArrayTrieUnitTest();
		treeUnitTest.ArrayBinaryTreeUnitTest();
	
		PriorityQueueUnitTest priorityQueueUnitTest = new PriorityQueueUnitTest();
		priorityQueueUnitTest.HeapPriorityQueueUnitTest();
		priorityQueueUnitTest.HeapAdaptablePriorityQueueUnitTest();
		
	}
}
