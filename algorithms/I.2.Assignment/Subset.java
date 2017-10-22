public class Subset {
    public static void main(String[] args) {
        if (args.length == 1) {
        
        //String string = args[0];
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        String str = "";
        while (!StdIn.isEmpty())
        {
            str = StdIn.readString();
            randomizedQueue.enqueue(str);
        }
        
        for (; k > 0; k--)
         System.out.print(randomizedQueue.dequeue() + "\n");
        
        }
        
    }
}