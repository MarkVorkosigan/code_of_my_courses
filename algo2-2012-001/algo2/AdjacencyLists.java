package algo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AdjacencyLists {

	/**
	 * Assign1,Question3:Prim's minimum spanning tree algorithm.
	 */
	private List<Pair>[] adj;
	private List<Pair>[] mst;
	private int vertices;
	private int edges;

	public AdjacencyLists(int n) {
		this.vertices = n;
		adj = (List<Pair>[]) new List[n + 1];
		for (int i = 1; i < adj.length; i++) {
			adj[i] = new ArrayList<Pair>();
		}
		mst = (List<Pair>[]) new List[n + 1];
		for (int i = 1; i < adj.length; i++) {
			mst[i] = new ArrayList<Pair>();
		}
	}

	public void addEdges(int i, int j, int w) {
		Pair p1 = new Pair(j, w);
		adj[i].add(p1);
		Pair p2 = new Pair(i, w);
		adj[j].add(p2);
		this.edges++;

	}

	public void addEdgesToMst(int i, int j, int w) {
		Pair p1 = new Pair(j, w);
		mst[i].add(p1);
		Pair p2 = new Pair(i, w);
		mst[j].add(p2);
	}

	public void primMst() {
		int[] inX = new int[this.vertices + 1];
		for (int i = 0; i < inX.length; i++)
			inX[i] = 0;
		// X to contain the vertex in mst current
		ArrayList<Integer> X = new ArrayList<Integer>();
		X.add(new Integer(1));// seed vertex
		inX[1] = 1;

		while (X.size() != this.vertices) {
			// find the min-cost edge which link X and V-X
			int minCost = Integer.MAX_VALUE;
			int iedge = 0;
			int jedge = 0;
			for (Iterator<Integer> it = X.iterator(); it.hasNext();) {
				int i = it.next().intValue();
				ArrayList<Pair> ad = (ArrayList<Pair>) adj[i];
				for (Iterator<Pair> it1 = ad.iterator(); it1.hasNext();) {
					Pair p = it1.next();
					int j = p.getWeight();
					int w = p.getLength();
					if (inX[j] == 0 && w < minCost) {
						minCost = w;
						iedge = i;
						jedge = j;
					}
				}
			}
			X.add(new Integer(jedge));
			inX[jedge] = 1;
			this.addEdgesToMst(iedge, jedge, minCost);
		}
	}

	public void fastPrimMst() {
		int[] inX = new int[this.vertices + 1];
		for (int i = 0; i < inX.length; i++)
			inX[i] = 0;
		// init the PriorityQueue
		IndexMinPQ<PairCmpWithKey> pp = new IndexMinPQ<PairCmpWithKey>(
				this.vertices + 1);
		PairCmpWithKey s = new PairCmpWithKey(1, 0);
		inX[1] = 1;
		pp.insert(1, s);
		System.out.println(1 + "*****");

		for (int i = 1; i < this.vertices; i++) {
			PairCmpWithKey t = new PairCmpWithKey(i + 1, 20000);
			pp.insert(i + 1, t);
			System.out.println(i + 1 + "*****");
		}
		/*
		 * for(int i=0;i<inL.length;i++) System.out.println(inL[i]);
		 */
		// X to contain the vertex in mst current
		ArrayList<Integer> X = new ArrayList<Integer>();
		int[] pmst = new int[this.vertices + 1];
		int[] wmst = new int[this.vertices + 1];

		while (X.size() != this.vertices) {
			PairCmpWithKey mp = pp.minKey();
			pp.delMin();
			int v = mp.getWeight();
			X.add(new Integer(v));
			inX[v] = 1;
			System.out.println("====" + X.size());
			for (Iterator<Pair> lv = adj[v].iterator(); lv.hasNext();) {
				Pair wp = lv.next();
				int w = wp.getWeight();
				int weig = wp.getLength();
				if (inX[w] == 0 && weig < pp.keyOf(w).getLength()) {
					PairCmpWithKey ni = new PairCmpWithKey(w, weig);
					System.out.println(weig + "++" + pp.keyOf(w).getLength());
					pp.decreaseKey(w, ni);
					pmst[w] = v;
					wmst[w] = weig;
				}
			}
		}
		for (int i = 2; i < pmst.length; i++) {
			System.out.println(pmst[i] + "*****" + i);
			this.addEdgesToMst(i, pmst[i], wmst[i]);
		}
	}

	public int getCost() {
		int cost = 0;
		for (int i = 1; i < mst.length; i++) {
			ArrayList<Pair> m = (ArrayList<Pair>) mst[i];
			for (Iterator<Pair> it = m.iterator(); it.hasNext();) {
				Pair p = it.next();
				int w = p.getLength();
				cost += w;
			}
		}
		return cost / 2;
	}

	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("edges.txt"));
			// in = new Scanner(new File("clustering1.txt"));

			int v = in.nextInt();
			int e = in.nextInt();
			AdjacencyLists g = new AdjacencyLists(v);
			for (int i = 0; i < e; i++) {
				int iv = in.nextInt();
				int jv = in.nextInt();
				int w = in.nextInt();
				g.addEdges(iv, jv, w);
			}
			g.primMst();
			int c=g.getCost();
			System.out.println(c);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getVertices() {
		return vertices;
	}

	public int getEdges() {
		return edges;
	}

}
