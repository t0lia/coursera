public class Percolation {
    private boolean[] sites;
    private int size;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 2);
        size = N;
        sites = new boolean[size * size + 2];
        for (int i = 0; i < size * size; i++) {
            sites[i] = false;
        }
    }              
    /*public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (sites[i * size + j] == true) {
                    System.out.print(". ");
                }
                else {
                       System.out.print("X ");
                }
            }
            System.out.print("\n");
        }
    }*/
    // open site (row i, column j) if it is not already
    public void open(int k, int l) {
        int i = k - 1;
        int j = l - 1;
        if (i < 0 || j < 0 || i >= size || j >= size) {
            throw new IndexOutOfBoundsException();
        }
        sites[i * size + j] = true;
        if (i == 0) {
            uf.union(size * size, j);
            uf2.union(size * size, j);
        }
        if (i == size-1) {
            uf.union(size * size - size + j, size * size + 1);
        }
        if (i > 0 && isOpen(i , j + 1)) {
            uf.union(i * size + j, (i - 1) * size + j);
            uf2.union(i * size + j, (i - 1) * size + j);
        }
        if (i < size - 1 && isOpen(i + 2, j + 1)) {
            uf.union(i * size + j, (i + 1) * size + j);
            uf2.union(i * size + j, (i + 1) * size + j);
        }
        if (j > 0 && isOpen(i + 1, j)) {
            uf.union(i * size + j, i * size + j - 1);
            uf2.union(i * size + j, i * size + j - 1);
        }
        if (j < size - 1 && isOpen(i + 1, j + 2)) {
            uf.union(i * size + j, i * size + j + 1);
            uf2.union(i * size + j, i * size + j + 1);
        }
    }  
    
    // is site (row i, column j) open?
    public boolean isOpen(int k, int l) {
        int i = k - 1;
        int j = l - 1;
        if (i < 0 || j < 0 || i >= size || j >= size) {
            throw new IndexOutOfBoundsException();
        }
        return sites[i * size + j];
    }    
    
    // is site (row i, column j) full?
    public boolean isFull(int k, int l) {
        int i = k - 1;
        int j = l - 1;
        if (i < 0 || j < 0 || i >= size || j >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(k, l) && uf2.connected(size * size, i * size + j)) {
            return true;
        }
        return false;
    }    
    
    // does the system percolate?
    public boolean percolates() {
        /*for (int i = 0; i < size; i++) {
            if (isOpen(size, i + 1) 
                   &&  uf.connected(size * size, size * size - size + i)) {
                return true;
        }*/
        if (uf.connected(size * size, size * size + 1)) {
            return true;
        }
        return false;
    }
}
