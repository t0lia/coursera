public class Board {
    private int[][] tiles;
    private int N;
    private int hamming;
    private int manhattan;

    public Board(int[][] blocks) {
        N = blocks.length;
        int  counter = 1;
        tiles = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                tiles[i][j] = blocks[i][j];
                //  hamming
                if (tiles[i][j] != counter++ && (i != N - 1 || j != N - 1))
                    hamming++;
                // manhattan
                if (tiles[i][j] != 0)
                    manhattan += Math.abs((tiles[i][j] - 1) / N -i) 
                                    + Math.abs((tiles[i][j] - 1) % N  - j);
            }

    }

    private void swap(int[][] a, int fI, int fJ, int sI, int sJ) {
        int temp    = a[fI][fJ];
        a[fI][fJ]   = a[sI][sJ];
        a[sI][sJ]   = temp;
    }
    
    

    public int dimension() {
        return N;
    }
    public int hamming() {
        return hamming;
    }
    public int manhattan() {
        return manhattan;
    }

    
    public boolean isGoal() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (j == N-1 && i == N-1)
                    break;
                if (tiles[i][j] != (i * N + j + 1))
                    return false; 
                } 
        if (tiles[N - 1][N - 1] != 0)
            return false;
        return true;
    }
    // get twin copy of board
    public Board twin() {
        return (new Board(tiles)).chagneToTwin();
    }
    //  change current presentation to twin
    private Board chagneToTwin() {
        if (tiles[0][0] != 0 && tiles[0][1] != 0)
            swap(tiles, 0, 0, 0, 1);
        else
            swap(tiles, 1, 0, 1, 1);    
        return this;
    }

    public boolean equals(Object y) {
        if (y == this) 
            return true;
        if (y == null) 
            return false;
        if (y.getClass() != this.getClass())
            return false;
        Board that = (Board) y;
        if (that.dimension() != this.dimension())
            return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] != that.tiles[i][j])
                    return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        Queue<Board> queue = new Queue<Board>();
        // addition matrix for create neighbors
        int[][] m = new int[N][N];
        int iEmptyPos = 0, jEmptyPos = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                m[i][j] = tiles[i][j];          //  copy matrix
                if (tiles[i][j] == 0) {         //  save empty position
                    iEmptyPos = i;
                    jEmptyPos = j;
                }
            }
        }

        //  move block up
        if (iEmptyPos < N - 1) {
            swap(m, iEmptyPos, jEmptyPos, iEmptyPos + 1, jEmptyPos);
            queue.enqueue(new Board(m));
            swap(m, iEmptyPos, jEmptyPos, iEmptyPos + 1, jEmptyPos);
        }
        //  move block down
        if (iEmptyPos >  0) {
            swap(m, iEmptyPos, jEmptyPos, iEmptyPos - 1, jEmptyPos);
            queue.enqueue(new Board(m));
            swap(m, iEmptyPos, jEmptyPos, iEmptyPos - 1, jEmptyPos);
        }
        //  move block left
        if (jEmptyPos < N - 1) {
            swap(m, iEmptyPos, jEmptyPos, iEmptyPos, jEmptyPos + 1);
            queue.enqueue(new Board(m));
            swap(m, iEmptyPos, jEmptyPos, iEmptyPos, jEmptyPos + 1);
        }
        //  move block up
        if (jEmptyPos > 0) {
            swap(m, iEmptyPos, jEmptyPos, iEmptyPos, jEmptyPos - 1);
            queue.enqueue(new Board(m));
            swap(m, iEmptyPos, jEmptyPos, iEmptyPos, jEmptyPos - 1);
        }
        return queue;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}