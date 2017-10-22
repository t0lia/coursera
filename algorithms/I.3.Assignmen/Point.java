/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point v, Point w) { 
            double slopeV = slopeTo(v);
            double slopeW = slopeTo(w);
            double order =  slopeV- slopeW;
            if (slopeV == Double.POSITIVE_INFINITY 
                    && slopeW == Double.POSITIVE_INFINITY
                || slopeV == Double.NEGATIVE_INFINITY 
                    && slopeW == Double.NEGATIVE_INFINITY) 
                order = 0;
            
            if (order > 0) 
                return 1;
            if (order < 0) 
                return -1;
            return 0;
        }
    }





    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double top =    (double) (that.y - this.y);
        double bottom = (double) (that.x - this.x);
        double result = top / bottom;
        if (result == Double.NEGATIVE_INFINITY) 
            result = Double.POSITIVE_INFINITY;
        if (top == bottom && top == 0)
            result = Double.NEGATIVE_INFINITY;
        if (result == -0.0) 
            result = 0;
        return result;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y > that.y) 
            return 1;
        else if (this.y < that.y) 
            return -1;
        else {
            if (this.x == that.x) 
                return 0;
            else if (this.x > that.x) 
                return 1;
            else 
                return -1;
        }
        
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        
    }
}
