public class BoggleSolver {
    private Trie<Boolean> t;
    private BoggleBoard board;
    private Boolean[][] isMarked;
    private MinPQ<String> pq; 
    private String[] dic;

    //  Initializes the data structure using 
    //  the given array of strings as the dictionary.
    //  (You can assume each word in the dictionary 
    //  contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        dic = new String[dictionary.length];
        for (int i = 0; i < dictionary.length; i++) {
            dic[i] = dictionary[i];
        }
        t = new Trie<Boolean>();
        for (int i = 0; i < dic.length; i++) {
            t.put(dic[i], true);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard b) {
        board = b;
        int r = board.rows();
        int c = board.cols();
        isMarked = new Boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                isMarked[i][j] = false;
            }    
        }
        Queue<String> queue = new Queue<String>();
        pq = new MinPQ<String>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                traverse("", i, j, t);
            }    
        }
        String prev = "";
        while (!pq.isEmpty()) {
            t.put(pq.min(), true);
            queue.enqueue(pq.delMin());
        }


        return queue;
    }

    //  Returns the score of the given word 
    //  if it is in the dictionary, zero otherwise.
    //  (You can assume the word contains only 
    //  the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (t.get(word) == null || word.length() <= 2) {
            return 0;
        } else if (word.length() <= 4) {
            return 1;
        } else if (word.length() == 5) {
            return 2;
        } else if (word.length() == 6) {
            return 3;
        } else if (word.length() == 7) {
            return 5;
        } else {
            return 11;
        } 
    }

    private void traverse(String prevAc, 
                            int i, 
                            int j, 
                            Trie<Boolean> trie) {
        
        Trie<Boolean> subTrie;

        int r = board.rows();
        int c = board.cols();
        String ac = "";
        String shift;

        //  insert QU if letter is Q
        if (board.getLetter(i, j) == 'Q') {
            shift = "QU";
        }
        else {
            shift = "" + board.getLetter(i, j);
        }
        ac = prevAc + shift;

        // if trie have not child of this prefix
        if (ac.length() > 2 && !trie.hasChild(shift)) 
            return;

        if (shift.equals("QU")) {
            subTrie = trie.subtrie('Q').subtrie('U');
        } else {
            subTrie = trie.subtrie(board.getLetter(i, j));
        }

        //  insert value into pq 
        if (ac.length() > 2 && trie.contains(shift) && trie.get(shift)) {   
            trie.put(shift, false);
            pq.insert(ac);
        } 
        
        //  check adj cells
        //  down
        if (i < r - 1 && !isMarked[i + 1][j]) {
            isMarked[i][j] = true;
            traverse(ac, i + 1, j, subTrie);
            isMarked[i][j] = false;
        }
        // up
        if (i > 0 && !isMarked[i - 1][j]) {
            isMarked[i][j] = true;
            traverse(ac, i - 1, j, subTrie);   
            isMarked[i][j] = false;
        }
        //  right
        if (j < c - 1 && !isMarked[i][j + 1]) {
            isMarked[i][j] = true;
            traverse(ac, i, j + 1, subTrie);
            isMarked[i][j] = false;
        }
        //  left
        if (j > 0 && !isMarked[i][j - 1]) {
            isMarked[i][j] = true;
            traverse(ac, i, j - 1, subTrie);
            isMarked[i][j] = false;
        }
        //  down-right
        if (i < r - 1 && j < c - 1 && !isMarked[i + 1][j + 1]) {
            isMarked[i][j] = true;
            traverse(ac, i + 1, j + 1, subTrie);
            isMarked[i][j] = false;
        }
        // down-left
        if (i < r - 1 && j > 0 && !isMarked[i + 1][j - 1]) {
            isMarked[i][j] = true;
            traverse(ac, i + 1, j - 1, subTrie);   
            isMarked[i][j] = false;
        }
        //  up-right
        if (i > 0 && j < c - 1 && !isMarked[i - 1][j + 1]) {
            isMarked[i][j] = true;
            traverse(ac, i - 1, j + 1, subTrie);
            isMarked[i][j] = false;
        }
        //  up-left
        if (j > 0 && i > 0 && !isMarked[i - 1][j - 1]) {
            isMarked[i][j] = true;
            traverse(ac, i - 1, j - 1, subTrie);
            isMarked[i][j] = false;
        }
    }
}