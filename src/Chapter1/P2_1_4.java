package Chapter1;

//问题：两个分别存储在两个n元数组中的n位二进制整数加起来，存入另一个n+1元数组中
public class P2_1_4 {

	public static void main(String[] args) {
		int A[]=new int[]{1,0,0,1,1};
		int B[]=new int[]{1,0,1,0,1};
		int C[]=new int[6];
		for(int i=4;i>=0;i--){
			if((A[i]+B[i]+C[i+1])<2){
				C[i+1]+=A[i]+B[i];
			}else{
				C[i+1]=0;
				C[i]++;
			}
			System.out.print(C[i+1]);
		}
		System.out.print(C[0]);
	}

}
