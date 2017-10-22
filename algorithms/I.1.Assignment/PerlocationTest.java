public class PerlocationTest {
    public static void main(String args[]) {
        Percolation perlocation=new Percolation(10);
        perlocation.open(1, 9);
        perlocation.open(2,1);
        perlocation.open(1,1);
        perlocation.open(2,1);
        perlocation.open(3,1);
        perlocation.open(4,1);
        perlocation.open(5,1);
        perlocation.open(5,2);
        perlocation.open(5,3);
        perlocation.open(5,4);
        perlocation.open(6,4);
        perlocation.open(7,4);
        perlocation.open(10,1);
        perlocation.open(7,5);
        perlocation.open(7,6);
        perlocation.open(7,7);
        perlocation.open(8,7);
        perlocation.open(8,8);
        perlocation.open(8,9);
        perlocation.open(8,10);
        perlocation.open(9,10);
        perlocation.open(10,10);
      //  perlocation.print();
      /*  System.out.print(perlocation.uf.find(100)+"\n");
        for(int i =0; i<10; i++) {
            for(int j =0; j<10; j++) {
        System.out.print(perlocation.uf.find(i*10+j)+"\t");
            }
        System.out.print("\n");
        }
        System.out.print(perlocation.uf.find(101)+"\n");
        //System.out.print(perlocation.percolates());
        //System.out.print(perlocation.isOpen(2,3));*/
       System.out.print(perlocation.isFull(10,1));
    }
}
