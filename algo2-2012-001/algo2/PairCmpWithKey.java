package algo2;

public class PairCmpWithKey implements Comparable<PairCmpWithKey> {

	public PairCmpWithKey(int w, int l) {
		this.weight = w;
		this.length = l;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getScore() {
		return this.weight - this.length;
	}

	public double getRatioScore() {
		return (double) this.weight / this.length;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PairCmpWithKey p1=new PairCmpWithKey(1,-100);
		PairCmpWithKey p2=new PairCmpWithKey(2,Integer.MAX_VALUE);
		System.out.println(p1.compareTo(p2));

	}

	private int weight;
	private int length;


	@Override
	public int compareTo(PairCmpWithKey o) {
		// TODO Auto-generated method stub
		return this.length-o.getLength();
	}

}
