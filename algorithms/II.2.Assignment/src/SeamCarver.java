import java.awt.Color;

public class SeamCarver {
    private static final int MAX_ENERGY = 195075;
    private Picture pic;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) { 
        pic = new Picture(picture);


    }
/*
    public void show() {
        pic.show();
    }
*/
        
    // current picture
    public Picture picture() {
        return pic;
    }
    
    // width of current picture
    public int width() { 
        return pic.width();
    }
    
    // height of current picture
    public     int height() { 
        return pic.height();
    }
    
    // energy of pixel at column x and row y
    public  double energy(int x, int y) { 

        if (x >= pic.width() || y >= pic.height() || x < 0 || y < 0)
            throw new IndexOutOfBoundsException();

        if (x == 0 || y == 0 || x == pic.width() - 1 || y == pic.height() - 1)
            return MAX_ENERGY;

        Color left      = pic.get(x - 1, y);
        Color right     = pic.get(x + 1, y);
        Color up        = pic.get(x, y - 1);
        Color bottom    = pic.get(x, y + 1);
        int rGr = right.getGreen();
        int lGr = left.getGreen();
        int rRd = right.getRed();
        int lRd = left.getRed();
        int rBl = right.getBlue();
        int lBl = left.getBlue();
        int uGr = up.getGreen();
        int bGr = bottom.getGreen();
        int uRd = up.getRed();
        int bRd = bottom.getRed();
        int uBl = up.getBlue();
        int bBl = bottom.getBlue();

        return  (rGr - lGr) * (rGr - lGr)
                + (rBl - lBl) * (rBl - lBl)
                + (rRd - lRd) * (rRd - lRd)
                + (uGr - bGr) * (uGr - bGr)
                + (uBl - bBl) * (uBl - bBl)
                + (uRd - bRd) * (uRd - bRd);
    }
    

    public int[] findHorizontalSeam() {

        int h = height();
        int w = width();

        int[] seam = new int[w];
        double[][] nrgMatrix = new double[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                nrgMatrix[i][j] = energy(j, i);
            }
        }
        double[] distTo = new double[h];
        int[][] pathTo = new int[h][w];

        for (int i = 0; i < h; i++) {
            distTo[i] = nrgMatrix[i][0];
            pathTo[i][0] = 0;
        }
        for (int j = 1; j < w; j++) {
            //  copy dist array
            double[] distToPrev = new double[h];
            for (int k = 0; k < h; k++) {
                distToPrev[k] = distTo[k];
            }

            for (int i = 0; i < h; i++) {
                double mdDist = distToPrev[i];
                double upDist = Double.POSITIVE_INFINITY;
                double btDist = Double.POSITIVE_INFINITY;
                if (i < h - 1) {
                    btDist = distToPrev[i + 1]; 
                }
                if (i > 0) { 
                    upDist = distToPrev[i - 1];
                }
                

                //  mid parent: nrgMatrix[i][j - 1]
                //  up parent: nrgMatrix[i - 1][j - 1] if i > 0
                //  bottom parent: nrgMatrix[i + 1][j - 1] if i < h - 1

                if (mdDist <= upDist && mdDist <= btDist) {   //  mdDist - min
                    distTo[i] = mdDist + nrgMatrix[i][j];
                    pathTo[i][j] = i;
                } else if (upDist <= mdDist && upDist <= btDist) { //  upDist - min
                    distTo[i] = upDist + nrgMatrix[i][j];
                    pathTo[i][j] = i - 1;
                } else {    //  btDist - min
                    distTo[i] = btDist + nrgMatrix[i][j];
                    pathTo[i][j] = i + 1;
                }
            }
        }
        int minIDX = 0;

        //  find minIDX
        for (int i = 0; i < h; i++) {
            if (distTo[minIDX] >= distTo[i]) {
                minIDX = i;
            }
        }

        //  write seam array
        for (int j = w - 1; j >= 0; j--) {
            seam[j] = minIDX;
            minIDX = pathTo[minIDX][j];
        }

        return seam;
    }

    public int[] findVerticalSeam() {
        int h = height();
        int w = width();

        int[] seam = new int[h];
        double[][] nrgMatrix = new double[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                nrgMatrix[i][j] = energy(j, i);
            }
        }
        double[] distTo = new double[w];
        int[][] pathTo = new int[h][w];
        for (int j = 0; j < w; j++) {
            distTo[j] = nrgMatrix[0][j];
            pathTo[0][j] = 0;
        }
        for (int i = 1; i < h; i++) {
            //  copy dist array
            double[] distToPrev = new double[w];
            for (int k = 0; k < w; k++) {
                distToPrev[k] = distTo[k];
            }

            for (int j = 0; j < w; j++) {
                double upDist = distToPrev[j];
                double lDist = Double.POSITIVE_INFINITY;
                double rDist = Double.POSITIVE_INFINITY;
                if (j < w - 1) {
                    rDist = distToPrev[j + 1];
                }
                if (j > 0) {
                    lDist = distToPrev[j - 1];
                }
                  
                //  up parent: nrgMatrix[i - 1][j]
                //  left parent: nrgMatrix[i - 1][j - 1] if j > 0
                //  right parent: nrgMatrix[i - 1][j + 1] if j < w - 1

                if (upDist <= lDist && upDist <= rDist) {   //  upDist - min
                    distTo[j] = upDist + nrgMatrix[i][j];
                    pathTo[i][j] = j;
                } else if (rDist <= upDist && rDist <= lDist) { //  rDist - min
                    distTo[j] = rDist + nrgMatrix[i][j];
                    pathTo[i][j] = j + 1;
                } else {    //  lDist - min
                    distTo[j] = lDist + nrgMatrix[i][j];
                    pathTo[i][j] = j - 1;
                }
            }
        }
        int minIDX = 0;
        //  find minIDX
        for (int j = 0; j < w; j++) {
            if (distTo[minIDX] >= distTo[j]) {
                minIDX = j;
            }
        }

        //  write seam array
        for (int i = h - 1; i >= 0; i--) {
            seam[i] = minIDX;
            minIDX = pathTo[i][minIDX];
        }

        return seam;
    }
    
    
    // remove horizontal seam from current picture
    public    void removeHorizontalSeam(int[] seam) { 
        if (seam == null) {
            throw new NullPointerException();
        }
        if (seam.length != width()) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < seam.length; i++) {
            if (seam[i - 1] - seam[i] > 1 || seam[i - 1] - seam[i] < -1) {
                throw new IllegalArgumentException();
            }
        }

        Picture newPic = new Picture(width(), height() - 1);

        for (int j = 0; j < width(); j++) {
            int i = 0;
            for (int k = 0; i < height() && k < height() - 1; i++, k++) { 
                if (seam[j] == i && i < height() - 1) {
                    i++;
                }
                newPic.set(j, k, pic.get(j, i));
            }
        }
        pic = newPic;
    }
    
    // remove vertical seam from current picture
    public    void removeVerticalSeam(int[] seam) { 
        if (seam == null) {
            throw new NullPointerException();
        }
        if (seam.length != height()) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < seam.length; i++) {
            if (seam[i - 1] - seam[i] > 1 || seam[i - 1] - seam[i] < -1) {
                throw new IllegalArgumentException();
            }
        }

        Picture newPic = new Picture(width() - 1, height());

        for (int j = 0; j < height(); j++) {
            int i = 0;
            for (int k = 0; i < width() && k < width() - 1; i++, k++) { 
                if (seam[j] == i && i < width() - 1) {
                    i++;
                }
                newPic.set(k, j, pic.get(i, j));
            }
        }
        pic = newPic;
    }
}