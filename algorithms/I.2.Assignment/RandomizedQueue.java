/*************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue < input.txt
 *  Data files:   
 *  
 *  RandomizedQueue implementation with a resizing array.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    /** 
     *  Construct an empty randomized queue
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }    

    /**
     *  Is the queue empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     *  Return the number of items on the queue
     */
        public int size() {
        return N;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     *  Add the item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        // double size of array if necessary
        if (N == a.length) resize(2*a.length);    
        a[N++] = item;
    }          
    
    /**
     *  Delete and return a random item
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } 
        exch(a, N-1, StdRandom.uniform(N));
        Item item = a[N-1];
        a[N-1] = null;                              // to avoid loitering
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }                   

    /**
     *  Return (but do not delete) a random item
     */
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } 
        return a[StdRandom.uniform(N)];
    }

    private void exch(Item[] array, int i, int j) {
        Item swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }   

    /**
     * Returns an iterator to this queue 
     * that iterates through the items in random order.
     * @return an iterator to this queue 
     * that iterates through the items in random order.
     */
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private int[] order;
        public RandomArrayIterator() {
            order = new int[N];
            for (int k = 0; k < N; k++) {
                order[k] = k;
            }
            StdRandom.shuffle(order);
            i = N;
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[order[--i]];
        }
    }






    /** 
     *  Unit testing
     */
    public static void main(String[] args) {
     /*   
        TestRandomizedQueue test = new TestRandomizedQueue();
        test.firstTest();
        test.oneEnqueue_checkIsNotEmpty();
        test.oneEnqueueOneSample_checkSample();
        test.oneEnqueueOneDequeue_checkIsEmpty();
        test.thousandEnqueueThousandDequeue_checkIsEmptyAndSample();
        test.callsEnqueueDequeueSample_checkSampleNotNull();
    */
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(""+i);        
        }
        for (String s : randomizedQueue) {
            System.out.print(s + " ");
        }
        System.out.println("");
        
    }

}