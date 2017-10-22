import java.util.HashMap;
import java.util.ArrayList;

public class WordNet {
    private Digraph graph;
    private String[] nouns;
    private HashMap<String, ArrayList<Integer>> nounsMap;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new NullPointerException();
        }
        //  counting the number of vertices
        In in = new In(synsets);
        int count = 0;
        while (in.hasNextLine()) {
            in.readLine();
            count++;
        }
        //System.out.println(count);


        graph = new Digraph(count);
        nouns = new String[count];
        nounsMap = new HashMap<String, ArrayList<Integer>>();
        
        //  read edges from file
        in = new In(hypernyms);
        while (in.hasNextLine()) {
            count--;
            String s = in.readLine();
            String[] v = s.split(",");
            int vertex = Integer.parseInt(v[0]);
            for (int i = 1; i < v.length; i++) {
                graph.addEdge(vertex, Integer.parseInt(v[i]));
            }
        }

        //  if it is not rooted DAG or graph have many roots
        DirectedCycle dc = new DirectedCycle(graph);
        if (dc.hasCycle() || count > 1) { //
            throw new IllegalArgumentException();
        }
        



        //  read nouns from file
        in = new In(synsets);
        while (in.hasNextLine()) {
            String s = in.readLine();
            String[] v = s.split(",");
            nouns[Integer.parseInt(v[0])] = v[1];
            
            String[] nounsInSynset = v[1].split(" ");
            for (int i = 0; i < nounsInSynset.length; i++) {
                String key = nounsInSynset[i];
                ArrayList<Integer> value;
                if (nounsMap.containsKey(key)) {
                    value = nounsMap.get(key);
                }
                else {
                    value = new ArrayList<Integer>();
                }
                value.add(Integer.parseInt(v[0]));
                nounsMap.put(key, value);
            }
        }        
        
        sap = new SAP(graph);
        
    }

    /*public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(graph.V()));
        sb.append(" vertices, ");
        sb.append(String.valueOf(graph.E()));
        sb.append(" edges");

        for (int i = 0; i < graph.V(); i++) {
            sb.append("\n" + nouns[i] + ": ");
            for (int v : graph.adj(i)) {
                sb.append(nouns[v]);
                sb.append(" ");
            }
        }
        return sb.toString();
    }*/


    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounsMap.keySet();
    }


    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new NullPointerException();
        }
        return nounsMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new NullPointerException();
        }
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> a = nounsMap.get(nounA); 
        ArrayList<Integer> b = nounsMap.get(nounB); 
        return sap.length(a, b);
    }

    // a synset (second field of synsets.txt) 
    // that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        ArrayList<Integer> a = nounsMap.get(nounA); 
        ArrayList<Integer> b = nounsMap.get(nounB);
        
        return nouns[sap.ancestor(a, b)];
    }

    // do unit testing of this class
    public static void main(String[] args) { }
}