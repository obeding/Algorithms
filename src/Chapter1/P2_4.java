package Chapter1;

/**
 * 确定在n个元素的任何排序中逆序对数量的算法，最坏情况需要O(nlgn)时间
 * 利用了归并排序的算法
 * 逆序对含义：
 * 假设A[1...n]是一个有n个不同数的数组。若i<j且A[i]>A[j],则对偶(i,j)称为A的一个逆序对(inversion)
 * @author 俊
 *
 */
public class P2_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[]={2,3,8,6,1};
		int s=inversionCount1(A);
		System.out.println(s);
	}
	/**
	 * 时间复杂度为O(n^2)
	 * @param A
	 * @return
	 */
	public static int inversionCount1(int[] A){
		int inversion=0;
		for(int i=0;i<A.length;i++){
			for(int j=i;j<A.length;j++){
				if(A[i]>A[j]){
					inversion++;
				}
			}
		}
		return inversion;
	}


}
