public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.init();
        app.run();


    }
    public void init() { }
    public void run() {

        
        WordNet wn = new WordNet("data/synsets.txt", 
            "data/hypernyms.txt");
        //wn.distance("o", "e");
        System.out.println(wn.sap("autoinjector", "story"));
        System.out.println(wn.distance("autoinjector", "story"));


/*

        System.out.println(wn.distance("subsidence", "arras"));

        Outcast outcast = new Outcast(wn);
        String[] nouns = new String[]{"banana", "pear", "lemon", "blueberry"};
        System.out.println(outcast.outcast(nouns));
  */      

/*
        In in = new In("data/digraph9.txt");
        Digraph G = new Digraph(in);
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                StdOut.println(v + "->" + w);


        SAP sap = new SAP(G);
        StdOut.println(sap.length(8, 4));        
        StdOut.println(sap.ancestor(8, 4));        
        */
    }

    
}