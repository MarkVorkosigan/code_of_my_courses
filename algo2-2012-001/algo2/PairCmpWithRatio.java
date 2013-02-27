package algo2;

import java.util.Comparator;

public class PairCmpWithRatio implements Comparator<Pair> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int compare(Pair arg0, Pair arg1) {
		if(arg0.getRatioScore()>arg1.getRatioScore())
			return 1;
		else if (arg0.getRatioScore()<arg1.getRatioScore())
			return -1;
		return 0;
	}

}
