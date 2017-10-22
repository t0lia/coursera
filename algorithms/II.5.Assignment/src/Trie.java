public class Trie<Value> {
    private static final int R = 26;        // extended ASCII


    private Node root;      // root of trie

    // R-way trie node
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

   /**
     * Initializes an empty string symbol table.
     */
    public Trie() {
    }
    public void reRoot(Node newRoot) {
        root = newRoot;
    }
    public Trie<Value> subtrie(char ch) {
        Trie<Value> t = new Trie<Value>();
        if (root != null) {
            t.reRoot(root.next[ch - 'A']);
        }
        return t;
    }


    @SuppressWarnings({"unchecked" })
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        int  c = key.charAt(d) - 'A';
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val) {
        if (val == null) delete(key);
        else root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        int  c = key.charAt(d) - 'A';
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public boolean hasChild(String key) {
        return hasChild(root, key, 0);
    }

    private boolean hasChild(Node x, String key, int d) {
        if (x == null) return false;
        if (d == key.length()) {
            return true;
        }

        int  c = key.charAt(d) - 'A';
        return hasChild(x.next[c], key, d+1);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

    
}
