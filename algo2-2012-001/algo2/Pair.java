package algo2;

public class Pair {
	
	public Pair(int w,int l){
		this.weight=w;
		this.length=l;
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
		return this.weight-this.length;
	}
	
	public double getRatioScore() {
		return (double)this.weight/this.length;
	}
	
	



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private int weight;
	private int length;

}
