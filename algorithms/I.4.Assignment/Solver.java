import java.util.Comparator;

public class Solver {
    private Stack<Board> solution;
    private boolean isSolvable;
    private int moves;

    private class BoardWrapper implements Comparable<BoardWrapper> {
        private final Board board;
        private final int moves;
        private BoardWrapper prev;
        private final Comparator<BoardWrapper> MNH_ORDER = new ManhattanOrder();
        private final Comparator<BoardWrapper> HMN_ORDER = new HammingOrder();

        public BoardWrapper(Board brd, int mvs, BoardWrapper prv) {
            moves = mvs;
            board = brd;
            prev  = prv;
        }

        private class ManhattanOrder implements Comparator<BoardWrapper> {
            public int compare(BoardWrapper v, BoardWrapper w) { 
                if (v.manhattan() < w.manhattan())
                    return -1;
                if (v.manhattan() > w.manhattan())
                    return 1;
                return 0;                    
            }
        }


        private class HammingOrder implements Comparator<BoardWrapper> {
            public int compare(BoardWrapper v, BoardWrapper w) { 
                if (v.hamming() < w.hamming())
                    return -1;
                if (v.hamming() > w.hamming())
                    return 1;
                return 0;                    
            }
        }

        public BoardWrapper prev() {
            return prev;
        }
        public boolean isGoal() {
            return board.isGoal();
        }    
        public Board unWrap() {
            return board;
        }
        public int hamming() {
            return board.hamming() + moves;
        }
        public int manhattan() {
            return board.manhattan() + moves;
        }
        public int compareTo(BoardWrapper that) {
            return MNH_ORDER.compare(this, that);
            //return HMN_ORDER.compare(this, that);

        }
    }

    public Solver(Board initial) {

        MinPQ<BoardWrapper> minPQprm = new MinPQ<BoardWrapper>();
        MinPQ<BoardWrapper> minPQtwn = new MinPQ<BoardWrapper>();

        BoardWrapper primary    = new BoardWrapper(initial, 0, null);
        BoardWrapper twin       = new BoardWrapper(initial.twin(), 0, null);
        
        moves = 0;
        solution = new Stack<Board>();

        minPQprm.insert(primary);
        minPQtwn.insert(twin);
        while (true) {
            primary = minPQprm.delMin();
            twin    = minPQtwn.delMin();

            if (primary.isGoal()) {
                moves = primary.moves;
                isSolvable = true;
                break;
            }

            if (twin.isGoal()) {
                solution = null;
                moves = -1;
                isSolvable = false;
                break;
            }
            
            for (Board b : primary.unWrap().neighbors()) 
                if (primary.prev() == null  || !(b.equals(primary.prev().unWrap()))) 
                    minPQprm.insert(new BoardWrapper(b, 
                                                        primary.moves + 1, 
                                                        primary));
            for (Board b : twin.unWrap().neighbors()) {
                if (twin.prev() == null  || !(b.equals(twin.prev().unWrap()))) 
                    minPQtwn.insert(new BoardWrapper(b, twin.moves + 1, twin));
            }
        
        }
        BoardWrapper current = primary;
        while (solution != null && current != null) {
            solution.push(current.unWrap());
            current = current.prev;
        }
  
        }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    public static void main(String[] args) {
 
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();

        //Stopwatch stopwatch = new Stopwatch();


        Board initial   = new Board(blocks);      
        
        Solver solver = new Solver(initial);
        
        //print solution to standard output
        int counter = 0;
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board.toString());
            }
        }
      
        //StdOut.println("Elapsed time: " + stopwatch.elapsedTime());
    }
}