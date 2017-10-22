public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.init();
        app.run();
    }
    public void init() { }
    public void run() {
/*
        StringT s = new StringT("couscous");
        System.out.println(s.substring(2, s.length()));
        System.out.println(s.length());
        */
          BurrowsWheeler.encode();
          MoveToFront.encode();
          HexDump h = new HexDump();

        /*
        In in = new In("data/dickens.txt");
        // 1000 char
        String s = in.readAll();

        System.out.println(s.length());
        Stopwatch timer = new Stopwatch();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        System.out.printf("\nelapsed time: %f\n\n", timer.elapsedTime());
        */
    }
}