package algo2;

public class UnionFind {

	/**
	 * @param args
	 */
	public int a[];
	public int count;
	public UnionFind(int n){
		a=new int[n];
		for(int i=0;i<n;i++)
			a[i]=i;
		count=n;
	}
	
	public int find(int x){
		return a[x];
	}
	
	public void union(int x1,int x2){
		int p=a[x2];//first bug here, keep it before change
		for(int i=0;i<a.length;i++){
			if(a[i]==p)
				a[i]=a[x1];
		}
		count--;
	}
	public static void main(String[] args) {
		UnionFind u=new UnionFind(8);
		System.out.println(u.find(3));
		u.union(3, 4);
		System.out.println(u.find(5));


	}

}
