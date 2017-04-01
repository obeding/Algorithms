package Chapter1;

/**
 * 最大子数组问题：
 * 寻找数组A的和最大的非空连续子数组
 * @author 俊
 *
 */
public class P4_1_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A={1, -2, 3, 10, -4, 7, 2, -5};
		int[] B=FIND_MAXIMUM_SUBARRAY(A,0,6);
		int c=maxsum(A, 8);
		System.out.println("c= "+ c);
		for(int i=0;i<B.length;i++){
			System.out.println(B[i]);
		}
	}

/****              利用分治法求解的算法                    ****/
	/**
	 * 找到数组中包含数组中间数的最大连续子数组
	 * @param A 数组A
	 * @param low
	 * @param mid
	 * @param high
	 * @return
	 */
	private static int[] Find_MAX_CROSSING_SUBARRAY(int[] A,int low,int mid,int high){
		int m=0;
		int left=0;
		int right=0;
		int n=0;
		int k=Integer.MIN_VALUE;
		int h=Integer.MIN_VALUE;
		for(int i=mid;i>=low;i--){
			m+=A[i];
			if(m>k){
				k=m;
				left=i;
			}
		}
		for(int j=mid+1;j<=high;j++){
			n+=A[j];
			if(n>h){
				h=n;
				right=j;
			}
		}
		int[] B={left,right,k+h};
		return B;
	}
	/**
	 * 用迭代计算出最大的非空连续子数组
	 * 时间复杂度为O(nlogn)
	 * @param A
	 * @param low
	 * @param high
	 * @return 返回最大的非空连续子数组的数组左右坐标和之和
	 */
	private static int[] FIND_MAXIMUM_SUBARRAY(int[] A,int low,int high){
		int[] B=new int[3];
		int mid;
		if(low==high){
			B[0]=low;
			B[1]=high;
			B[2]=A[low];
		}else{
			mid=Math.floorDiv(low+high, 2);
			int[] C=FIND_MAXIMUM_SUBARRAY(A,low,mid);
			int[] D=FIND_MAXIMUM_SUBARRAY(A,mid+1,high);
			int[] E=Find_MAX_CROSSING_SUBARRAY(A,low,mid,high);
			if(C[2]>=D[2]&&C[2]>=E[2]){
				B=C;
			}else if(D[2]>=C[2]&&D[2]>=E[2]){
				B=D;
			}else{
				B=E;
			}
		}
		return B;
	}

/****              效率最高也是最优的算法                    ****/

	/**
	 * 时间复杂度为O(n)
	 * 算法思想：从总左边（x[0]）开始遍历整个数组，一直到最右边结束（x[n-1]），在这个过程中记录到目前为止最大的子数组和maxsofar。
	 * maxsofar初始化成0。假如我们已经找到x[0]到x[n-1]之间的最大子数组和，那么x[0]到x[i]之间的最大子数组和是怎样的呢？
	 * 要么“还是x[0]到x[i-1]之间的最大子数组和”，要么是“从x[i]开始，往前几个连续的数的最大值”
	 * @param A
	 * @param num
	 * @return
	 */
	private static int maxsum(int[] A,int num){
		int maxsofar=0;       	//maxsofar记录到目前为止的最大值
		int maxendinghere=0;	//maxendinghere记录从当前位置开始往前几个连续的数的和的最大值
		for(int i=0;i<num;i++){
			maxendinghere=max(maxendinghere+A[i],0);
			maxsofar=max(maxsofar,maxendinghere);
		}
		return maxsofar;
	}
	/**
	 * 求两个数中较大的数
	 * @param a
	 * @param b
	 * @return
	 */
	private static int max(int a,int b){
		return (a)>(b)?(a):(b);
	}
}
