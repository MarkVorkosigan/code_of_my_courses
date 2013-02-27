package algo2;

import java.util.Comparator;

public class PairCmp implements Comparator<Pair> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * compare two Pair objects
	 * 
	 * @param args0 is the first Pair;arg1 is the second Pair
	 */
	public int compare(Pair arg0, Pair arg1) {
		if (arg0.getScore() > arg1.getScore())
			return 1;
		else if (arg0.getScore() < arg1.getScore())
			return -1;
		else if (arg0.getWeight() > arg1.getWeight())
			return 1;
		else if (arg0.getWeight() < arg1.getWeight())
			return -1;
		return 0;
	}

}
