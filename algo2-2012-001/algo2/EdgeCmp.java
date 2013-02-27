package algo2;

import java.util.Comparator;

public class EdgeCmp implements Comparator<Edge> {

	@Override
	public int compare(Edge arg0, Edge arg1) {
		return arg0.getW()-arg1.getW();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
