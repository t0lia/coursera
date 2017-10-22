public class PercolationStats {
    private double mean;
    private double stddev;
    private double confideceLo;
    private double confideceHi;
    
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
       double[] x;
       x = new double[T];
       for (int i = 0; i < T; i++) {
           Percolation percolation = new Percolation(N);
           int count = 0;
           while (!percolation.percolates()) {
               count++;
               int k, l;
               while (true) {
                   k = StdRandom.uniform(N);
                   l = StdRandom.uniform(N);
                   if (!percolation.isOpen(k+1, l+1))
                       break;
               }
            percolation.open(k+1, l+1);
           }
           x[i] = (double) count / (N * N);
       }
      
       mean = StdStats.mean(x);
       stddev = StdStats.stddev(x);
       confideceLo = mean - 1.96 * stddev / Math.sqrt(T);
       confideceHi = mean + 1.96 * stddev / Math.sqrt(T);
    }    // perform T independent computational experiments on an N-by-N grid
    //private double mean;
   
    public double mean() {
        return mean;
    } // sample mean of percolation threshold
    public double stddev() {
        return stddev;
    } // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return confideceLo;
    } // returns lower bound of the 95% confidence interval
    public double confidenceHi() {
        return confideceHi;
    } // returns upper bound of the 95% confidence interval
    // test client, described below
   public static void main(String[] args) {
      
       if (args.length == 2)
       {
           if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0)
               throw new IllegalArgumentException();
           //Stopwatch stopwatch = new Stopwatch();
           PercolationStats stats = 
               new PercolationStats(Integer.parseInt(args[0]), 
                                    Integer.parseInt(args[1]));
           //System.out.print("time\t\t\t="+stopwatch.elapsedTime()+"sec.\n");
           System.out.print("mean\t\t\t="+stats.mean()+"\n");
           System.out.print("stddev\t\t\t="+stats.stddev() + "\n");
           System.out.print("95% confidence interval\t=" 
                                + stats.confidenceLo() + ", "
                                + stats.confidenceHi() + "\n");
           
           }
   }
}