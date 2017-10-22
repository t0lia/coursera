public final class StringT {

    private int index;
    private char[] value;
    private int offset;
    private int length;
    private int hash;


    public StringT(String s) {
        value = new char[s.length()];
        offset = 0;
        length = s.length();
        for (int i = 0; i < s.length(); i++) {
            value[i] = s.charAt(i);
        }
    }
    private StringT(int offset, int length, char[] value) {
        this.offset = offset;
        this.length = length;
        this.value = value;
    }

    public void setIndex(int i) {
        index = i;
    }
    public int index() {
        return index;
    }
    public int length() {
        return length;
    }

    public char charAt(int i) {
        return value[i + offset];
    }


    public StringT substring(int from, int to) {
        return new StringT(offset + from, to - from, value);
    }
    /*
    public StringT substring(int from) {
        return new StringT(offset + from, length - from, value);
    }*/

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = offset; i < offset + length; i++) {
            sb.append(value[i]);
        }
        return sb.toString();

    }
}