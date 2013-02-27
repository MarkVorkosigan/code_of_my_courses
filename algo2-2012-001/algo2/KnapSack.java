package algo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KnapSack {
	/*
	 * Assign3,Question1: the knapsack algorithm from lecture
	 */
	public int ks(int[] v, int[] w, int c) {
		int res;
		int n = v.length;
		int[][] a = new int[n + 1][c + 1];
		for (int j = 0; j <= c; j++)
			a[0][j] = 0;
		for (int i = 0; i <= n; i++)
			a[i][0] = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= c; j++) {
				if (j < w[i - 1])
					a[i][j] = a[i - 1][j];
				else if (a[i - 1][j] > a[i - 1][j - w[i - 1]] + v[i - 1])
					a[i][j] = a[i - 1][j];
				else
					a[i][j] = a[i - 1][j - w[i - 1]] + v[i - 1];
			}
		res = a[n][c];
		return res;
	}

	/*
	 * Question2: solve a knapsack instance, but a much bigger one
	 */
	public int ks1(int[] v, int[] w, int c) {
		int res;
		int n = v.length;
		int[][] a = new int[2][c + 1];
		for (int j = 0; j <= c; j++)
			a[0][j] = 0;
		int t1 = 0, t2 = 0;
		for (int i = 1; i <= n; i++) {
			if (i % 2 == 0) {
				t1 = 0;
				t2 = 1;
			} else {
				t1 = 1;
				t2 = 0;
			}
			for (int j = 1; j <= c; j++) {
				if (j < w[i - 1])
					a[t1][j] = a[t2][j];
				else if (a[t2][j] > a[t2][j - w[i - 1]] + v[i - 1])
					a[t1][j] = a[t2][j];
				else
					a[t1][j] = a[t2][j - w[i - 1]] + v[i - 1];
			}

		}
		if (n % 2 == 0)
			res = a[0][c];
		else
			res = a[1][c];
		return res;
	}

	public int ksRec(int[] v, int[] w, int c, int i) {
		int res = 0;
		if (i == 1)
			res = c < w[i] ? 0 : v[1];
		else if (c < w[i])
			res = ksRec(v, w, c, i - 1);
		else {
			int res1 = ksRec(v, w, c, i - 1);
			int res2 = ksRec(v, w, c - w[i], i - 1) + v[i];
			res = Math.max(res1, res2);
		}
		System.out.println(i);
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			Scanner s = new Scanner(new File("knapsack2.txt"));
			int c = s.nextInt();
			int n = s.nextInt();
			int[] v = new int[n];
			int[] w = new int[n];
			for (int i = 0; i < n; i++) {
				v[i] = s.nextInt();
				w[i] = s.nextInt();
			}
			KnapSack ks = new KnapSack();
			long start = System.currentTimeMillis();
			int rs = ks.ks1(v, w, c);
			long finish = System.currentTimeMillis();
			System.out.println("the result is :" + rs + " the time is:"
					+ (finish - start));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
