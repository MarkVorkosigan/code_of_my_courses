package algo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
/*
 *  Assign2: clustering algorithm from lecture 
 *  for computing a max-spacing k-clustering
 */
public class Cluster {

	/*
	 * Ques1: clustering algorithm from lecture 
	 * for computing a max-spacing k-clustering
	 */
	public int clustering(Edge[] edges, int n, int k) {
		// prepare the edges and sort them
		EdgeCmp p = new EdgeCmp();
		Arrays.sort(edges, p);
		// cluster
		UnionFind u = new UnionFind(n + 1);
		int clusterNum = n;
		int i = 0;
		while (clusterNum != k) {
			Edge e = edges[i];
			int v1 = e.getV1();
			int v2 = e.getV2();
			if (u.find(v1) != u.find(v2)) {
				u.union(v1, v2);
				clusterNum--;
				// System.out.println(clusterNum+"****");
			}
			i++;
		}
		int res = Integer.MAX_VALUE;
		for (int j = 0; j < edges.length; j++) {
			Edge e = edges[j];
			int v1 = e.getV1();
			int v2 = e.getV2();
			if (u.find(v1) != u.find(v2)) {
				if (e.getW() < res)
					res = e.getW();
			}
		}
		return res;
	}
/*
 * Question2:what is the largest value of k 
 * such that there is a k-clustering with 
 * spacing at least 3?
 */
	public int solver(String filename, int kd) throws FileNotFoundException {
		Scanner in;
		in = new Scanner(new File(filename));
		int v = in.nextInt();
		int bn = in.nextInt();
		Byte[][] nodes = new Byte[v][bn];
		for (int i = 0; i < v; i++) {
			nodes[i] = new Byte[bn];
			for (int j = 0; j < bn; j++) {
				Byte num = in.nextByte();
				nodes[i][j] = num;
			}
		}
		LinkedList<Edge>[] d = new LinkedList[kd];
		for (int i = 0; i < kd; i++)
			d[i] = new LinkedList<Edge>();
		for (int i = 0; i < nodes.length - 1; i++) {
			for (int j = i + 1; j < nodes.length; j++) {
				int hd = hammingDistance(nodes[i], nodes[j]);
				for (int l = 0; l < kd; l++)
					if (hd == l) {
						Edge e = new Edge(i, j, hd);
						d[l].add(e);
					}
			}
		}
		
		UnionFind u = new UnionFind(nodes.length);
		int clusterNum = nodes.length;
		for (int i = 0; i < d.length; i++) {
			for (Iterator<Edge> it = d[i].iterator(); it.hasNext();) {
				Edge e = it.next();
				int v1 = e.getV1();
				int v2 = e.getV2();
				if (u.find(v1) != u.find(v2)) {
					u.union(v1, v2);
					clusterNum--;
				}
			}
		}
		return clusterNum;
	}

	public int hammingDistance(Byte[] b1, Byte[] b2) {
		int res = 0;
		for (int i = 0; i < b1.length; i++)
			if (b1[i] != b2[i]) {
				res++;
			}
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Scanner in; try { in = new Scanner(new File("clustering1.txt")); //
		 * in = new Scanner(new File("test.txt")); int v = in.nextInt(); int e =
		 * in.nextInt(); Edge[] edges = new Edge[e]; for (int i = 0; i < e; i++)
		 * { int iv = in.nextInt(); int jv = in.nextInt(); int w = in.nextInt();
		 * edges[i] = new Edge(iv, jv, w); //
		 * System.out.println(edges[i].getV1()
		 * +"  "+edges[i].getV2()+"**"+edges[i].getW()); } Cluster c = new
		 * Cluster(); int k = 4; int res = c.clustering(edges, v, k);
		 * System.out.println(res);
		 * 
		 * } catch (FileNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		Cluster c = new Cluster();
		try {
			int res = c.solver("clustering2.txt", 3);
			System.out.println(res);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
