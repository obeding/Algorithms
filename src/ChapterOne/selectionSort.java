package ChapterOne;

 /**
 * 选择排序算法
 */

public class selectionSort {

	public static void main(String[] args) {
		int A[]={3,2,4,5,1};
		Selection_Sort(A);
		System.out.println(A[0]);
	}

	private static int[] Selection_Sort(int A[]){
		for(int i=0;i<A.length-1;i++){
			for(int j=i;j<A.length;j++){
				if(A[j]<A[i]){
					A[i]=A[j];
				}
			}
		}
		return A;
	}

}

