package algo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

/*
 * Assigment 1
 In this programming problem and the next you'll 
 code up the greedy algorithms from lecture for 
 minimizing the weighted sum of completion times..
 */
public class Qes1 {

	/*Question 1:run the greedy algorithm that 
	schedules jobs in decreasing order of the 
	difference (weight - length).*/
	public long solver(String filename) throws FileNotFoundException {
		Scanner in = new Scanner(new File(filename));
		int n = in.nextInt();
		Pair[] jobs = new Pair[n];
		for (int i = 0; i < n; i++) {
			int w = in.nextInt();
			int l = in.nextInt();
			jobs[i] = new Pair(w, l);
		}
		PairCmp p = new PairCmp();
		Arrays.sort(jobs, p);
		long values = 0;
		int finishtime = 0;
		for (int i = n - 1; i > -1; i--) {// sum up from the Big to Small
			finishtime += jobs[i].getLength();
			values += jobs[i].getWeight() * finishtime;
		}
		return values;
	}

/*	Question2: run the greedy algorithm that schedules jobs 
	(optimally) in decreasing order of 
	the ratio (weight/length).*/ 
	public long solver1(String filename) throws FileNotFoundException {
		Scanner in = new Scanner(new File(filename));
		int n = in.nextInt();
		Pair[] jobs = new Pair[n];
		for (int i = 0; i < n; i++) {
			int w = in.nextInt();
			int l = in.nextInt();
			jobs[i] = new Pair(w, l);
		}
		PairCmpWithRatio p = new PairCmpWithRatio();
		Arrays.sort(jobs, p);
		long values = 0;
		int finishtime = 0;
		for (int i = n - 1; i > -1; i--) {// sum up from the Big to Small
			finishtime += jobs[i].getLength();
			values += jobs[i].getWeight() * finishtime;
		}
		return values;
	}

	public static void main(String[] args) {
		Qes1 q = new Qes1();
		long res1 = 0;
		long res2 = 0;
		try {
			res1 = q.solver("jobs.txt");
			res2 = q.solver1("jobs.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(res1);
		System.out.println(res2);

	}

}
