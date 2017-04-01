package Chapter1;
public class MaximumSubarray {

	private static int StupidFunction(int[] A){
		int size = A.length;
		int realMax = Integer.MIN_VALUE;
		for(int i = 0;i < size;i++){
			int max = Integer.MIN_VALUE;
			for(int j = 0;j< size;j++){
				int temp = 0;
				for(int m = j;m<j+i+1&&m<size;m++){
					temp += A[m];
				}
				if(temp > max){
					max = temp;
				}
			}
			if(max > realMax){
				realMax = max;
			}
		}
		return realMax;
	}

	private static int maxsubset(int[] A,int l,int r){
		int temp=0;
		int summax = Integer.MIN_VALUE;
		for(int i=l;i<=r;i++){
			temp+=A[i];
			if(temp > summax) summax=temp;
			if(temp < 0) temp=0;
		}
		return summax;
	}

	private static int MaxOfThree(int a,int b,int c){
		int max = Integer.MIN_VALUE;
		if(a > b){
			max = a;
		}else{
			max = b;
		}
		if(c > max){
			max = c;
		}
		return max;
	}

	private static int maxsubset1(int[] A,int left ,int right){
		int MaxLeftSum,MaxRightSum; //左右边的最大和
		int MaxLeftBorderSum,MaxRightBorderSum; //含中间边界的左右部分最大和
		int LeftBorderSum,RightBorderSum;   //含中间边界的左右部分当前和

		if(left == right){
			if(A[left] > 0){
				return A[left];
			}else{
				return 0;
			}
		}

		int center = (left + right)/2;
		MaxLeftBorderSum = 0;
		return LeftBorderSum = 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A ={-1,-2,-5,-4,-8};
		System.out.println(StupidFunction(A));
		System.out.println(maxsubset(A,0,4));
	}

}
