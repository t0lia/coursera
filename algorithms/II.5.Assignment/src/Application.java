public class Application {
    public static void main(String[] args) {
        String dic = "data/dictionary-algs4.txt";
        String brd = "data/board4x4.txt";
        In in = new In(dic);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(brd);
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);

        brd = "data/board-q.txt";
        BoggleBoard board2 = new BoggleBoard(brd);
        score = 0;
        int i = 1;
        for (String word : solver.getAllValidWords(board2))
        {
            System.out.print(i++);
            System.out.print("\t");
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
    
}