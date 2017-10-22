public class CircularSuffixArray {

    private int length;
    private int[] sfIDX;

    // circular suffix array of s
    public CircularSuffixArray(String s) { 
        sfIDX = new int[s.length()];
        length = s.length();
        StringT sT = new StringT(s + s);
        
        StringT[] suffixes = new StringT[s.length()];

        
        for (int i = 0; i < s.length(); i++) {
            suffixes[i] = sT.substring(i, s.length() + i);
            suffixes[i].setIndex(i);
        }
        
        Quick3stringT.sort(suffixes);

        for (int i = 0; i < s.length(); i++) {
            sfIDX[i] = suffixes[i].index();
        }

    }

    // length of s
    public int length() { 
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) { 
        return sfIDX[i];
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}