public class MoveToFront {
    
    //  apply move-to-front encoding, reading from 
    //  standard input and writing to standard output
    public static void encode() { 
        char[] table = new char[256];
        for (char i = 0; i < 256; i++) {
            table[i] = i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            char prev = table[0];
            char pos = 0;
            for (char i = 0; i < 256; i++) {
                if (table[i] == c) {
                    pos = i;
                    break;
                }
            }
            for (char i = pos; i > 0; i--) {
                table[i] = table[i - 1];
            }
            table[0] = c;

            BinaryStdOut.write(pos);
        }
        BinaryStdOut.flush();
    }

    //  apply move-to-front decoding, reading 
    //  from standard input and writing to standard output
    public static void decode() { 
        char[] table = new char[256];
        for (char i = 0; i < 256; i++) {
            table[i] = i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char pos = BinaryStdIn.readChar();
            char c = table[pos];
            for (char i = pos; i > 0; i--) {
                table[i] = table[i - 1];
            }
            table[0] = c;
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) { 
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}