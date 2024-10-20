package trie;

public class ArrayTrie {
	
    // a node in the Trie has an array for children and a flag to indicate the end of a word
    private class TrieNode {
    	// on peut representer tout les char d'un texte
    	// noeud qui correspond au char
        TrieNode[] children = new TrieNode[128]; // array for ASCII characters // 128 cases avec null 
        // on peut etre sur un noeud qui termine un mot ... 
        // si c'est un mots qui est dans la liste, 
        boolean isWord = false; // true if the node represents a word
    }
    
    // root node
    private final TrieNode root;

    // constructor
    // on cree la racine au debut
    public ArrayTrie() { root = new TrieNode(); }

    
    // ------------------------------------------- 
    public void insert(String word) {
    	TrieNode current = root;
    	
    	for (char c : word.toCharArray()) {
			if (current.children[c] == null) {
				current.children[c] = new TrieNode();
			}
    		current = current.children[c]; // on avance le pointeur
		}
        // le dernier current est la fin du mot 
        current.isWord = true;
    }
    
    
    // check if there is any word in the Trie that starts with the given prefix
    public boolean startsWith( String prefix ) {
        TrieNode current = root;
        for( char c : prefix.toCharArray() ) {
            if( current.children[c] == null ) {
                return false; // Prefix is not found
            }
            current = current.children[c];
        }
        return true; // If we can traverse the entire prefix
    }
    
    
    
    
    
    
    
    
    
    
    
    
    // utility method to print all words stored in the Trie
    public void printWords() {
        printWords( root, new StringBuilder() );
    }
    
    private void printWords( TrieNode node, StringBuilder prefix ) {
        if( node.isWord ) {
            System.out.println( prefix.toString() );
        }
        
        for( int i = 0; i < node.children.length; i++ ) {
            if( node.children[i] != null ) {
                prefix.append( (char) i ); // append the corresponding character for the index
                printWords( node.children[i], prefix );
                prefix.deleteCharAt( prefix.length() - 1 ); // backtrack
            }
        }
    }
    
    
    
    
}
