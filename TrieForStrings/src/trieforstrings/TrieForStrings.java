package trieforstrings;
public class TrieForStrings {

    public static void main(String[] args) {
        // TODO code application logic here
    }
    public class StringST<Value>{
       private final static int R = 256;
     private Node root;      // root of trie
    private int n;          // number of keys in trie

    // R-way trie node
    private class Node {
        private Object val;
        private Node[] next = new Node[R];
    }
        /**
     * Initializes an empty string symbol table.
     */
    public StringST() {
    }  
        public Value get(String key) {
        if (key == null) 
        return null;
        if (x == null) return null;
        return (Value) x.val;
    }
            private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }
                public void put(String key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) delete(key);
        else root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.val == null) n++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }
}
}


