import java.util.*;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] rq;
	private int n;

	public RandomizedQueue() {                // construct an empty randomized queue
		rq = (Item[]) new Object[1];
		n = 0;
	}

	public boolean isEmpty() {                // is the queue empty?
		return n == 0;
	}

	public int size() {                       // return the number of items on the queue
		return n;
	}

	private void resize(int size) {
		Item[] copy = (Item[]) new Object[size];
		for (int i = 0; i < n; i++) {
			copy[i] = rq[i];
		}
		rq = copy;
	}

	public void enqueue(Item item) {          // add the item
		if (item == null) throw new NullPointerException();
		if (n == rq.length) resize(2 * rq.length);
		rq[n++] = item;
	}

	public Item dequeue() {                   // remove and return a random item
		if (n == 0) throw new NoSuchElementException();
		int i = StdRandom.uniform(n);
		Item item = rq[i];
		n--;
		rq[i] = rq[n];
		rq[n] = null;
		if (n > 0 && n <= rq.length / 4) resize(rq.length / 2);
		return item;
	}

	public Item sample() {                    // return (but do not remove) a random item
		if (n == 0) throw new NoSuchElementException();
		return rq[StdRandom.uniform(n)];
	}

	private class ArrayIterator implements Iterator<Item> {
		private int i = 0;
		private int[] shuffle = StdRandom.permutation(n);

		public boolean hasNext() {
			return i < n;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (i >= n) throw new NoSuchElementException();
			return rq[shuffle[i++]];
		}
	}

	public Iterator<Item> iterator() {        // return an independent iterator over items in random order
		return new ArrayIterator();
	}

	// public static void main(String[] args) {  // unit testing (optional)

	// }
}
