package algo2;

public class Bst {

	final int BIGNUM=100;
	public double optBst(double [] a){
		int n=a.length;
		double [][] res=new double[n+2][n+2];
		double [][] w=new double[n+2][n+2];
		System.out.println(n);
		for(int l=1;l<=n;l++)
			for(int i=1;i<=n-l+1;i++){
				int j=i+l-1;
				res[i][j]=BIGNUM;//very big for the example
				w[i][j]=w[i][j-1]+a[j-1];
				for(int r=i;r<=j;r++){
					double t=res[i][r-1]+res[r+1][j]+w[i][j];
					if(t<res[i][j])
						res[i][j]=t;
				}
			}
		return res[1][n];		
				
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double [] a={.2,.05,.17,.1,.2,.03,.25};
		Bst b=new Bst();
		System.out.println(b.optBst(a));

	}

}
