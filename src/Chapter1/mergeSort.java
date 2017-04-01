package Chapter1;
//利用分治法实现归并排序
/*分治法的核心思想：将原问题分解为几个规模较小但类似于原问题的子问题，递归地求解这些子问题，
  				然后再合并这些子问题的解来建立原问题的解。
  分治模式在每层递归时都有三个步骤：
  	1.分解原问题为若干子问题，这些子问题时原问题的规模较小的实例。
  	2.解决这些子问题，递归地求解各子问题。然而，若子问题的规模足够小，则直接求解。
  	3.合并这些子问题的解成原问题的解
 */

public class mergeSort {

	public static void main(String[] args) {
		int A[]={3,2,6,8,5,1,9,7};
		MergeSort(A,1,8);
		for(int i=0;i<8;i++){
			System.out.print(A[i]+",");
		}
	}
	private static void MergeSort(int A[],int p,int r){
		if(p<r){
			int q=(p+r)/2;
			MergeSort(A,p,q);
			MergeSort(A,q+1,r);
			Merge1(A,p,q,r);
		}
	}

	/**
	 * 利用一个哨兵简化代码，将两个有序数组合并成一个有序数组
	 * @param A
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	private static int[] Merge(int A[],int p,int q,int r){
		int n1=q-p+1;
		int n2=r-q;
		int L[]=new int[n1+1];
		int R[]=new int[n2+1];
		for(int i=0;i<n1;i++){
			L[i]=A[p+i-1];
		}
		for(int i=0;i<n2;i++){
			R[i]=A[q+i];
		}
		L[n1]=Integer.MAX_VALUE;
		R[n2]=Integer.MAX_VALUE;
		int i=0;
		int j=0;
		for(int k=p-1;k<r;k++){
			if(L[i]<=R[j]){
				A[k]=L[i];
				i++;
			}else{
				A[k]=R[j];
				j++;
			}
		}
		return A;
	}

	/**
	 * 不利用哨兵的合并算法
	 * @param A
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	private  static int[] Merge1(int A[],int p,int q,int r){
		int n1=q-p+1;
		int n2=r-q;
		int L[]=new int[n1];
		int R[]=new int[n2];
		for(int i=0;i<n1;i++){
			L[i]=A[p+i-1];
		}
		for(int i=0;i<n2;i++){
			R[i]=A[q+i];
		}
		int i=0;
		int j=0;
		int k=0;
		while(i<=n1 && j<=n2){
			if(L[i]<=R[j]){
				A[k]=L[i];
				k++;
				i++;
			}else{
				A[k]=R[j];
				k++;
				j++;
			}
		}
		while (i <= n1)
			A[k++] = L[i++];

		while (j <= n2)
			A[k++] = R[j++];
		return A;
	}
}
