#include <iostream>
#include<bitset>
#include<fstream>

using namespace std;

/*
 * Assign 5: tsp algorithm
*/
const int citySize=10;
const int bitsize=9;
const float big=500000.0;
const int psetSize=1<<bitsize;
float a[psetSize][citySize];
float c[citySize][citySize];
float p[citySize][2];

void cityDist(string file)
{
    cout<<file<<endl;
    ifstream fin;
    fin.open(file.c_str());
	if(!fin) {
		cout << "Cannot open file.\n";
		return ;
	}
    int n=0;
    fin>>n;
	cout<<n<<endl;
    for(int i=0; i<citySize; i++)
    {
        fin>>p[i][0]>>p[i][1];
    }
	fin.close();
    for(int i=0; i<citySize; i++)
    {
        for(int j=0; j<citySize; j++)
        {
            c[i][j]=sqrt(pow(p[i][0]-p[j][0],2)+pow(p[i][1]-p[j][1],2));
        }
    }
}

int setTransInt(int arr[],int size){
	int n;
	bitset<bitsize> b;
	for (int i=1;i<=size;i++){
		int j=arr[i];
		b.set(j-1);
	}
	n=(int)b.to_ulong();
	return n;
}

int setDiffOne(int s,int i){
	int n;
	bitset<bitsize> b(s);
	b.reset(i-1);
	n=(int)b.to_ulong();
	return n;
}

float minPathDist(int arr[],int size,int j){
	float minval=big;
	int s=setTransInt(arr,size);
	int news=setDiffOne(s,j);
	float val=0;
	for(int i=0;i<=size;i++){
		int k=arr[i];
		if(k!=j){
			 val=a[news][k]+c[k][j];
			 if(val<minval)
				 minval=val;
		}
	}
	return minval;
}

void minSet(int arr[],int size){
	int s=setTransInt(arr,size);
    int i;
    for (i=1;i<=size;i++){
		int j=arr[i];
		a[s][j]=minPathDist(arr,size,j);
		//cout<<j<<" ";
	}
	//cout<<endl;
}

void optPath (int n){
    int stack[citySize],k;
    stack[0]=0; /* 0 is not considered as part of the set */
    k = 0;
    while(1){
        if (stack[k]<n){
            stack[k+1] = stack[k] + 1;
            k++;
        }
        else{
            stack[k-1]++;
            k--;
        }
        if (k==0)
            break;
        minSet(stack,k);
    }

}
 void optPath1(int n)
{
    unsigned int i, j, bits, i_max = 1U << n;
    for (i = 0; i < i_max ; ++i)
    {
        int st[citySize]= {0};
        int k=1;
        for (bits = i, j = 0; bits; bits >>= 1, ++j)
        {
            if (bits & 1)
            {
                st[k]=j+1;
                k++;
            }
        }
        if((k-1)!=0)
            minSet(st,k-1);
    }
}

float tsp(string file){
	float optTsp=big;
	cityDist(file);
	for(int i=0;i<psetSize;i++){
		if(i==0)
			a[i][0]=0;
		else
			a[i][0]=big;
	}
	optPath1 (bitsize);
	//for(int i=0;i<psetSize;i++){
	//	for(int j=0;j<citySize;j++){
	//		cout<<a[i][j]<<"  ";
	//	}
	//	cout<<endl;
	//}
	for(int j=1; j<citySize; j++)
    {
        float tspval=a[psetSize-1][j]+c[j][0];
        if(tspval<optTsp)
			optTsp=tspval;
    }
	return optTsp;
}
int main(){
	string s="data/tsp.txt";
	float optTsp=tsp(s);
	cout<<optTsp<<endl;
	char c;
	cin>>c;
    return 0;
}
