package priorityQueue;

import java.util.Comparator;

// classe definit a l'exterieur des elements de comparaison 
public class StringLengthComparator implements Comparator<String> {
    // compare two strings according to their lengths
	@Override
	public int compare(String a, String b) {
		if( a.length() < b.length() ) return -1;
		else if( a.length() == b.length() ) return 0;
		else return 1;
	}

}
