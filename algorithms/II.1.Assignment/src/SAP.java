public class SAP {

    private Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) { 
        graph = new Digraph(G);
    }

    // length of shortest ancestral path 
    // between v and w; -1 if no such path
    public int length(int v, int w) { 


        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        

        int ancestor = -1;
        int minDist = Integer.MAX_VALUE;
        //System.out.println(bfsV.hasPathTo(v));
        for (int i = 0; i < graph.V(); i++) {
            if (!bfsV.hasPathTo(i) || !bfsW.hasPathTo(i)) {
                continue;
            }
            int dist = bfsV.distTo(i)  + bfsW.distTo(i);
            if (dist < minDist) {
                minDist = dist;
                ancestor = i;
            }
            
        }
        if (minDist == Integer.MAX_VALUE) {
            return -1;
        }
        return minDist;
    }

    // a common ancestor of v and w that participates 
    // in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) { 

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        
        int ancestor = -1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < graph.V(); i++) {
            if (!bfsV.hasPathTo(i) || !bfsW.hasPathTo(i)) {
                continue;
            }
            int dist = bfsV.distTo(i)  + bfsW.distTo(i);
            if (dist < minDist) {
                minDist = dist;
                ancestor = i;
            }
            
        }
        return ancestor;
    }

    // length of shortest ancestral path between any vertex 
    // in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) { 
        if (v == null || w == null) {
            throw new NullPointerException();
        }
        for (int i : v) {
            if (i < 0 || i >= graph.V()) {
                throw new IndexOutOfBoundsException();
            }
        }
        for (int i : w) {
            if (i < 0 || i >= graph.V()) {
                throw new IndexOutOfBoundsException();
            }
        }

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        

        int ancestor = -1;
        int minDist = Integer.MAX_VALUE;
        //System.out.println(bfsV.hasPathTo(v));
        for (int i = 0; i < graph.V(); i++) {
            if (!bfsV.hasPathTo(i) || !bfsW.hasPathTo(i)) {
                continue;
            }
            int dist = bfsV.distTo(i)  + bfsW.distTo(i);
            if (dist < minDist) {
                minDist = dist;
                ancestor = i;
            }
            
        }
        if (minDist == Integer.MAX_VALUE) {
            return -1;
        }
        return minDist;
    }

    // a common ancestor that participates in shortest
    // ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) { 
        if (v == null || w == null) {
            throw new NullPointerException();
        }
        for (int i : v) {
            if (i < 0 || i >= graph.V()) {
                throw new IndexOutOfBoundsException();
            }
        }
        for (int i : w) {
            if (i < 0 || i >= graph.V()) {
                throw new IndexOutOfBoundsException();
            }
        }

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        
        int ancestor = -1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < graph.V(); i++) {
            if (!bfsV.hasPathTo(i) || !bfsW.hasPathTo(i)) {
                continue;
            }
            int dist = bfsV.distTo(i)  + bfsW.distTo(i);
            if (dist < minDist) {
                minDist = dist;
                ancestor = i;
            }
            
        }
        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) { }
}