package Chapter1;

/**
 * 运行时间为O(nlogn)的算法
 * 算法描述：
 * 给定n个整数的集合S和另一个整数x确定S中是否存在两个之和刚好为x的元素
 * @author 俊
 *
 */
public class P2_3_7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int S[]={2,3};
		int x=5;
		Boolean b=search(S,x);
		System.out.println(b);
	}
	private static  Boolean search(int S[],int x){
		Boolean b=false;
		for(int i=0;i<S.length;i++){
			for(int j=i+1;j<S.length;j++){
				if(S[i]+S[j]==x){
					b = true;
				}
			}
		}
		return b;
	}
}
