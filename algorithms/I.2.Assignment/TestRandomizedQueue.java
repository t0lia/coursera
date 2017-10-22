public class TestRandomizedQueue {
    public void firstTest() {
        //assert(true == true);
        /*
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
       randomizedQueue.enqueue("0");
       randomizedQueue.enqueue("1");
       randomizedQueue.enqueue("2");
       randomizedQueue.enqueue("3");
       randomizedQueue.enqueue("4");
       randomizedQueue.enqueue("5");
       //int temp=3;
       //while(temp-->0) {
       
       for (String s:randomizedQueue) {
           System.out.print(s + " ");
       }
       System.out.print("size = " + randomizedQueue.size() + "\n");
       
           System.out.print(randomizedQueue.dequeue() + "\t");
           System.out.print(randomizedQueue.dequeue() + "\t");
           System.out.print(randomizedQueue.dequeue() + "\t");
           System.out.print(randomizedQueue.dequeue() + "\t");
           System.out.print("\n");
       //}
      
       for (String s:randomizedQueue) {
           System.out.print(s + " ");
       }
       System.out.print("size = " + randomizedQueue.size() + "\n");
        */
       /*
       Iterator<String> i = randomizedQueue.iterator();
       while (i.hasNext()) {
           String s = i.next();
           System.out.print(s + "\t");
       }
       System.out.print("size = " + randomizedQueue.size() + "\n");
       */

    }
    public void oneEnqueue_checkIsNotEmpty() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("0");
        assert(randomizedQueue.isEmpty() == false);
    }
    public void oneEnqueueOneSample_checkSample() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("0");
        assert(randomizedQueue.sample() == "0");
    }
    public void oneEnqueueOneDequeue_checkIsEmpty() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("0");
        assert(randomizedQueue.dequeue() == "0");
        assert(randomizedQueue.isEmpty() == true);
    }
    public void thousandEnqueueThousandDequeue_checkIsEmptyAndSample() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        for (int i = 0; i < 1000; i++) {
            randomizedQueue.enqueue(""+i);
            assert(randomizedQueue.sample() != null);
        }
        for (int i = 0; i < 1000; i++) {
            assert(randomizedQueue.sample() != null);
            randomizedQueue.dequeue();
        }
        assert(randomizedQueue.isEmpty() == true);
    }

    public void callsEnqueueDequeueSample_checkSampleNotNull() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        for (int i = 0; i < 1000; i++) {
            int variant = StdRandom.uniform(10);
            if (variant < 8) {
                randomizedQueue.enqueue(""+i);
            }
            else if (variant == 8) {
                randomizedQueue.dequeue();
            }
            else if(!randomizedQueue.isEmpty()){
                assert(randomizedQueue.sample() != null);
            }

        }
    }



}