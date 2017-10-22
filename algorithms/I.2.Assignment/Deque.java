import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first  = null;
    private Node last   = null;
    private int size;
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    //  is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }
    
    //  return the number of items on the deque
    public int size() {
        return size;
    }

    //  insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        newNode.prev = null;
        if (first != null) {
            first.prev = newNode;
        } 
        else {
            last = newNode;
        }
        first = newNode;
        size++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = last;
        newNode.next = null;
        if (last != null) {
            last.next = newNode;
        }
        else {
            first = newNode;
        }
        last = newNode;
        size++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } 
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        else {
            first.prev = null;
        }
        size--;
        return item;
    }
    
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } 
        Item item = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        }
        else {
            last.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator(); 
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { 
            return current != null; 
        }
        public void remove() {
            /* not supported */ 
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {
        /*
        TestDeque test = new TestDeque();
        test.testAddFirstRemoveLast();
        test.testAddLastRemoveLast(); 
        test.testAddFirstRemoveFirst(); 
        test.testAddLastRemoveFirst(); 
*/
    }
}