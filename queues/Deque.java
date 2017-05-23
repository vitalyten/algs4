import java.util.*;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        private Node(Item i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node first;
    private Node last;
    private int n;

    public Deque() {                          // construct an empty deque
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {                // is the deque empty?
        return n == 0;
    }

    public int size() {                       // return the number of items on the deque
        return n;
    }

    public void addFirst(Item item) {         // add the item to the front
        if (item == null) throw new NullPointerException();
        Node newFirst = new Node(item, first, null);
        if (first != null) first.prev = newFirst;
        first = newFirst;
        if (last == null) last = newFirst;
        n++;
    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) throw new NullPointerException();
        Node newLast = new Node(item, null, last);
        if (last != null) last.next = newLast;
        last = newLast;
        if (first == null) first = newLast;
        n++;
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (first == null) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null) first.prev = null;
        else last = null;
        n--;
        return item;
    }

    public Item removeLast() {                // remove and return the item from the end
        if (last == null) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        if (last != null) last.next = null;
        else first = null;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // public static void main(String[] args) {  // unit testing (optional)

    // }
}
