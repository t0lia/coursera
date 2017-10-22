public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.init();
        app.run();
    }
    public void init() { }
    public void run() {
        Picture picture = new Picture("data/HJocean.png"); //("data/6x5.png");
        // 

        SeamCarver seamCarver = new SeamCarver(picture);

        //seamCarver.show();
        for (int j = 0; j < 301; j++) {
            
            System.out.println(j);
            int[] seam = seamCarver.findHorizontalSeam();
            seamCarver.removeHorizontalSeam(seam);

            int[] seam2 = seamCarver.findVerticalSeam();
            seamCarver.removeVerticalSeam(seam2);

            if (j % 50 == 0) {

               // seamCarver.show();
            }
        }

    }
}