public class Quick3stringT {
    private static final int CUTOFF =  15;   // cutoff to insertion sort

    // sort the array a[] of StringTs
    public static void sort(StringT[] a) {
//        StdRandom.shuffle(a);
        sort(a, 0, a.length-1, 0);
    }

    // return the dth character of s, -1 if d = length of s
    private static int charAt(StringT s, int d) { 
        if (d == s.length()) return -1;
        return s.charAt(d);
    }


    // 3-way StringT quicksort a[lo..hi] starting at dth character
    private static void sort(StringT[] a, int lo, int hi, int d) { 

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if      (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt-1, d);
        if (v >= 0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }

    // sort from a[lo] to a[hi], starting at the dth character
    private static void insertion(StringT[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(StringT[] a, int i, int j) {
        StringT temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    // DEPRECATED BECAUSE OF SLOW SUBStringT EXTRACTION IN JAVA 7
    // private static boolean less(StringT v, StringT w, int d) {
    //    assert v.subStringT(0, d).equals(w.subStringT(0, d));
    //    return v.subStringT(d).compareTo(w.subStringT(d)) < 0; 
    // }

    // is v less than w, starting at character d
    private static boolean less(StringT v, StringT w, int d) {
        //assert v.subStringT(0, d).equals(w.substring(0, d));
        int min = Math.min(v.length(), w.length());
        for (int i = d; i < min; i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }



}
