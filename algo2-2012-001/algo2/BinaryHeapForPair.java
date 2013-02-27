package algo2;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements a priority queue as a class binary heap
 * stored implicitly in an array
 * @author morin
 *
 * @param <Pair>
 */
public class BinaryHeapForPair<Pair> extends AbstractQueue<Pair> {
	Factory<Pair> f;
	
	Comparator<Pair> c;
	
	/**
	 * Our backing array
	 */
	protected Pair[] a;
	public int [] bb;
	/**
	 * The number of elements in the priority queue
	 */
	protected int n;

 
	public BinaryHeapForPair(Class<Pair> clz, Comparator<Pair> c0,int []v) {
		c = c0;
		f = new Factory<Pair>(clz);
		a = f.newArray(1);
		n = 0;
		bb=v;
	}
	
	

	public void clear() {
		a = f.newArray(1);
		n = 0;		
	}

	 


	protected void resize() {
		Pair[] b = f.newArray(Math.max(2*n,1));
		System.arraycopy(a, 0, b, 0, n);
		a = b;
	}

	/**
	 * @param i
	 * @return the index of the left child of the value at index i
	 */
	protected int left(int i) {
		return 2*i + 1;
	}

	/**
	 * @param i
	 * @return the index of the left child of the value at index i
	 */
	protected int right(int i) {
		return 2*i + 2;
	}

	/**
	 * @param i
	 * @return the index of the parent of the value at index i
	 */
	protected int parent(int i) {
		return (i-1)/2;
	}
	

	public int size() {
		return n;
	}
	
	/**
	 * Swap the two values a[i] and a[j]
	 * @param i
	 * @param j
	 */
	final protected void swap(int i, int j) {
		Pair x = a[i];
		a[i] = a[j];
		a[j] = x;
	}
	
	
	
	public boolean offer(Pair x) {
		return add(x);
	}

	public boolean add(Pair x) {
		if (n + 1 > a.length) resize();
		a[n++] = x;
		bubbleUp(n-1);
		return true;
	}
	
	public int add1(Pair x){
		if (n + 1 > a.length) resize();
		a[n++] = x;
		return bubbleUp(n-1);
	}

	public Pair get(int i){
		return a[i];
	}
	/**
	 * Run the bubbleUp routine at position i
	 * @param i
	 */
	protected int bubbleUp(int i) {
		int p = parent(i);
		while (i > 0 && c.compare(a[i], a[p]) < 0) {
			swap(i,p);
			
			i = p;
			p = parent(i);
		}
		return i;
	}
	
	/*
	 * Decrease the key given the element 
	 * @param i: the element location ; key : the value to change
	 */
	public int decreaseKey(int i,Pair newx){
		a[i]=newx;
		return bubbleUp(n-1);
	}

	public Pair peek() {
		return a[0];
	}
	
	public Pair poll() {
		return remove();
	}

	public Pair remove() {
		Pair x = a[0];
		a[0] = a[--n];
		trickleDown(0);
		if (3*n < a.length) resize();
		return x;
	}

	
	/**
	 * Move element i down in the heap until the heap
	 * property is restored
	 * @param i
	 */
	protected int trickleDown(int i) {
		do {
			int j = -1;
			int r = right(i);
			if (r < n && c.compare(a[r], a[i]) < 0) {
				int l = left(i);
				if (c.compare(a[l], a[r]) < 0) {
					j = l;
				} else {
					j = r;
				}
			} else {
				int l = left(i);
				if (l < n && c.compare(a[l], a[i]) < 0) {
					j = l;
				}
			}
			if (j >= 0)	swap(i, j);
			i = j;
		} while (i >= 0);
		return i;
	}

	public Iterator<Pair> iterator() {
		class PQI implements Iterator<Pair> {
			int i;
			public PQI() {
				i = 0;
			}
			public boolean hasNext() {
				return i < n;
			}
			public Pair next() {
				return a[i++];
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
		return new PQI();
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryHeap<Integer> h = new BinaryHeap<Integer>(Integer.class);
		Random r = new Random();
		int n = 20;
		for (int i = 0; i < n; i++) {
			h.add(r.nextInt(2500));
		}
		System.out.println(h);
		while (!h.isEmpty()) {
			System.out.print("" + h.remove() + ",");
		}
		System.out.println("");
		
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = r.nextInt(2500);
		}
		BinaryHeap.sort(a);
		for (Integer x : a) {
			System.out.print("" + x + ",");
		}
		System.out.println("");
		
		n = 100000;
		long start, stop;
		double elapsed;
		System.out.print("performing " + n + " adds...");
		start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			h.add(r.nextInt());
		}
		stop = System.nanoTime();
		elapsed = 1e-9*(stop-start);
		System.out.println("(" + elapsed + "s [" 
				+ (int)(((double)n)/elapsed) + "ops/sec])");

		n *= 10;
		System.out.print("performing " + n + " add/removes...");
		start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			if (r.nextBoolean()) {
				h.add(r.nextInt());
			} else {
				h.remove();
			}
		}
		stop = System.nanoTime();
		elapsed = 1e-9*(stop-start);
		System.out.println("(" + elapsed + "s [" 
				+ (int)(((double)n)/elapsed) + "ops/sec])");
	}

}
