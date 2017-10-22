import java.util.Arrays;


public class Fast {
    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
            
        Point[] points;
        Point[] origins;

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        points = new Point[N];
        origins = new Point[N];

        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            origins[i] = new Point(x, y);
        }
        
        Arrays.sort(origins);
        for (Point p : points) {
            p.draw();
        }

        for (int i = 0; i < N; i++) {
            Arrays.sort(points);
            Point origin = origins[i];
            Arrays.sort(points, origin.SLOPE_ORDER);

            int counter = 1;
            double prevSlope = origin.slopeTo(points[0]);
            for (int k = 0; k <= N; k++) {
                if (k != N && prevSlope == origin.slopeTo(points[k])) {
                    counter++;
                }
                else {
                    if (counter > 2) {
                        if (origin.compareTo(points[k - counter]) < 0) {
                            String buf = new String();
                            buf = origin.toString();
                            for (int l = k - counter; l < k; l++) {
                                buf = buf + " -> " + points[l];
                            }
                            System.out.println(buf);
                            origin.drawTo(points[k-1]);
                        }
                    }
                    counter = 1;
                    if (k != N)
                        prevSlope = origin.slopeTo(points[k]);
                }
            }

        }

    StdDraw.show(0);
    StdDraw.setPenRadius();
    }
}