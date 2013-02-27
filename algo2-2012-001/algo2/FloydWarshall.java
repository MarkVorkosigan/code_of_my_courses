package algo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FloydWarshall {

	/**
	 * Assign4:the all-pairs shortest-path problem
	 */
	public static final int INFINITE = Integer.MAX_VALUE/10;

	public void floydWarshall(int[][] g, int n) {
		int[][] d = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (i == j)
					d[i][j] = 0;
				else if (g[i][j] == this.INFINITE)
					d[i][j] = this.INFINITE;
				else
					d[i][j] = g[i][j];
			}
		int[][] tmp = new int[n][n];
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					tmp[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
				}
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					d[i][j] = tmp[i][j];
				}
		}
		
		boolean negcc = false;
		for (int i = 0; i < n; i++)
			if (d[i][i] < 0){
				System.out.println(d[i][i]);
				negcc = true;
			}

		if (negcc)
			System.out.println("there is a NCC!");
		else {
			int min = d[0][0];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					if (d[i][j] < min)
						min = d[i][j];
				}
			System.out.println("there is NO NCC! The shortest path is" + min);
		}

	}

	public static void main(String[] args) {
		try {
			FloydWarshall fw = new FloydWarshall();
			Scanner s = new Scanner(new File("g3.txt"));
			int n = s.nextInt();
			int e = s.nextInt();
			int[][] g = new int[n][n];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					g[i][j] = fw.INFINITE;
				}
			for (int i = 0; i < e; i++) {
				int w, v, c;
				w = s.nextInt();
				v = s.nextInt();
				c = s.nextInt();
				g[w - 1][v - 1] = c;
			}
			long start = System.currentTimeMillis();
			fw.floydWarshall(g, n);
			long finish = System.currentTimeMillis();
			System.out.println("the time is:" + (finish - start));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
