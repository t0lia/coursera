public class TestDeque
{
    public void testAddFirstRemoveLast()
    {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("0");
        assert(deque.removeLast().equals("0"));
        assert(deque.isEmpty() == true);
    }
    public void testAddLastRemoveLast()
    {
        Deque<String> deque = new Deque<String>();
        deque.addLast("0");
        assert(deque.removeLast().equals("0"));
        assert(deque.isEmpty() == true);
    }
    public void testAddFirstRemoveFirst()
    {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("0");
        assert(deque.removeFirst().equals("0"));
        assert(deque.isEmpty() == true);
    }
    public void testAddLastRemoveFirst()
    {
        Deque<String> deque = new Deque<String>();
        deque.addLast("0");
        assert(deque.removeFirst().equals("0"));
        assert(deque.isEmpty() == true);
    }
}