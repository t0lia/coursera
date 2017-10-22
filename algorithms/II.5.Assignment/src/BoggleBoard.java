public class BoggleBoard
{

    private char[][] board;
    private int r;
    private int c;
    /*
    // Initializes a random 4-by-4 Boggle board.
    // (by rolling the Hasbro dice)
    public BoggleBoard() {
        r = 4; 
        c = 4;
        char[][] board = new char[r][c];
    }

    // Initializes a random M-by-N Boggle board.
    // (using the frequency of letters in the English language)
    public BoggleBoard(int M, int N) {
        r = M; 
        c = N;
        char[][] board = new char[r][c];
    }
*/
    // Initializes a Boggle board from the specified filename.
    public BoggleBoard(String filename) {
        In in = new In(filename);
        String s = in.readLine();
        s = s.trim();
        String[] ss = s.split("\\s+");
        r = Integer.parseInt(ss[0]);
        c = Integer.parseInt(ss[1]);
        board = new char[r][c];

        for (int i = 0; i < r && !in.isEmpty(); i++) {
            s = in.readLine();
            ss = s.split("\\s+");

            for (int j = 0; j < c; j++) {
                board[i][j] = ss[j].charAt(0);
            }
        
        }

        
    }
/*
    // Initializes a Boggle board from the 2d char array.
    // (with 'Q' representing the two-letter sequence "Qu")
    public BoggleBoard(char[][] a) {
        r = 4; 
        c = 4;
        char[][] board = new char[r][c];   
    }
*/
    // Returns the number of rows.
    public int rows() {
        return r;
    }

    // Returns the number of columns.
    public int cols() {
        return c;
    }

    // Returns the letter in row i and column j.
    // (with 'Q' representing the two-letter sequence "Qu")
    public char getLetter(int i, int j) {
        return board[i][j];
    }

    // Returns a string representation of the board.
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'Q') {
                    sb.append("Qu");
                }
                else {
                    sb.append(board[i][j]);
                    sb.append(" ");
                }
                sb.append(" ");
            }
            if (i != r - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();       
    }
    
}