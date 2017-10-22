public class Outcast {
    private WordNet wn;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) { 
        wn = wordnet;
    }
   
   
    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxDist = 0;
        String noun = "";
        for (int i = 0; i < nouns.length; i++) {
            int sum = 0; 
            for (int j = 0; j < nouns.length; j++) {
                sum += wn.distance(nouns[i], nouns[j]);
            }
            if (maxDist < sum) {
                maxDist = sum;
                noun = nouns[i];
            }
            
        }
        return noun;
    }
   
    // see test client below
    public static void main(String[] args) { }
}