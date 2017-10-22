public class BurrowsWheeler {
    private static final int R = 256;   // extended ASCII alphabet size

    //  apply Burrows-Wheeler encoding, reading 
    //  from standard input and writing to standard output
    public static void encode() { 
        String s = "";
        s = BinaryStdIn.readString();
        String ss = s + s;
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
            }
            sb.append(ss.charAt(s.length() + csa.index(i) - 1));
        }
        BinaryStdOut.write(sb.toString());   
        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler decoding, reading 
    //  from standard input and writing to standard output
    public static void decode() {
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();        
        final int SIZE = t.length();
        int[] next = new int[SIZE];

        int[] count = new int[R + 1];

        for (int i = 0; i < SIZE; i++) {
            count[t.charAt(i) + 1]++;
        }

        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        for (int i = 0; i < SIZE; i++) {
            next[count[t.charAt(i)]++] = i;
        }

        int current = next[first];
        for (int i = 0; i < SIZE; i++) {
            BinaryStdOut.write(t.charAt(current));   
            current = next[current];
        }

        BinaryStdOut.flush();
    }




    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) { 
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}