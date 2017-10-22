import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
            // rescale coordinates and turn on animation mode
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
            StdDraw.show(0);
            StdDraw.setPenRadius(0.01);  // make the points a bit larger

        if (args.length == 1) {
            
            Point[] points;
            // read in the input
            String filename = args[0];
            In in = new In(filename);
            int N = in.readInt();
            points = new Point[N];
            for (int i = 0; i < N; i++) {
                int x = in.readInt();
                int y = in.readInt();
                Point p = new Point(x, y);
                points[i] = p;
            }
            Arrays.sort(points);
            for (int i = 0; i < N - 3; i++) {
                for (int j = i + 1; j < N - 2; j++) {
                    for (int k = j + 1; k < N - 1; k++) {
                        for (int l = k + 1; l < N; l++) {
                            if (points[i].slopeTo(points[j]) 
                                    == points[i].slopeTo(points[k]) 
                                && points[i].slopeTo(points[j]) 
                                    == points[i].slopeTo(points[l])
                                ) {
                                System.out.println(points[i] + " -> " 
                                    + points[j] + " -> " 
                                    + points[k] + " -> " + points[l]);
                                points[i].drawTo(points[l]);
                                
                            }
                        }
                    }
                }  
            }

            for (Point point : points) {
                point.draw();
            }
            

            
        }
        else {
            System.out.println("Error! Please, input filename.");
        }

        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}