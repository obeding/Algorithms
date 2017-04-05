package chapterFour;

/**
 * 问题：求最大子数组
 * 问题描述：已知数组A，寻找A的和最大的非空连续子数组
 *
 * @author obed
 */
public class MaximumSubarray {
    /**
     * 暴力求解方法：求出所有情况比较得出最大子数组
     * 时间复杂度：O（n^2）
     * 评价：时间用时最多，最差选择
     */
    private static int MaxSubSumV(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            int curMax = 0;
            for (int j = i; j < A.length; j++) {
                curMax += A[j];
                if (curMax > max) {
                    max = curMax;
                }
            }
        }
        return max;
    }

    /**
     * 分治求解法
     * 方法介绍：考虑将数组从中间分为两个子数组，则最大子数组必然出现在以下三种情况之一：
     * 1、完全位于左边的数组中。
     * 2、完全位于右边的数组中。
     * 3、跨越中点，包含左右数组中靠近中点的部分。
     * 递归将左右子数组再分别分成两个数组，直到子数组中只含有一个元素，退出每层递归前，返回上面三种情况中的最大值
     * 时间复杂度:O（nlogn）
     * 评价：次优算法
     *
     * @return
     */
    //求三个数中最大值
    private static int MaxOfThree(int a, int b, int c) {
        int Max = a;
        if (b > Max)
            Max = b;
        if (c > Max)
            Max = c;
        return Max;
    }

    private static int MaxSubSumD(int[] arr, int left, int right) {
        int MaxLeftSum, MaxRightSum; //左右边的最大和
        int MaxLeftBorderSum, MaxRightBorderSum; //含中间边界的左右部分最大和
        int LeftBorderSum, RightBorderSum;   //含中间边界的左右部分当前和
        int i, center;

        //递归到最后的基本情况
        if (left == right)
            if (arr[left] > 0)
                return arr[left];
            else
                return 0;

        //求含中间边界的左右部分的最大值
        center = (left + right) / 2;
        MaxLeftBorderSum = 0;
        LeftBorderSum = 0;
        for (i = center; i >= left; i--) {
            LeftBorderSum += arr[i];
            if (LeftBorderSum > MaxLeftBorderSum)
                MaxLeftBorderSum = LeftBorderSum;
        }
        MaxRightBorderSum = 0;
        RightBorderSum = 0;
        for (i = center + 1; i <= right; i++) {
            RightBorderSum += arr[i];
            if (RightBorderSum > MaxRightBorderSum)
                MaxRightBorderSum = RightBorderSum;
        }

        //递归求左右部分最大值
        MaxLeftSum = MaxSubSumD(arr, left, center);
        MaxRightSum = MaxSubSumD(arr, center + 1, right);

        //返回三者中的最大值
        return MaxOfThree(MaxLeftSum, MaxRightSum, MaxLeftBorderSum + MaxRightBorderSum);
    }


    /**
     * 最优方法，时间复杂度O（n）
     * 和最大的子序列的第一个元素肯定是正数
     * 因为元素有正有负，因此子序列的最大和一定大于0
     * 另外，该算法的一个附带的有点是：它只对数据进行一次扫描，一旦元素被读入并被处理，它就不再需要被记忆。
     * 因此，如果数组在磁盘或磁带上，他就可以被顺序读入，在主存中不必存储数组的任何部分。
     * 不仅如此，在任意时刻，该算法都能对它已经读入的数据给出最大子数组（另外两种算法不具有这种特性）。
     * 具有这种特性的算法叫做联机算法。
     * @param A
     * @return
     */
    public static int MaxSubSumA(int[] A){
        int MaxSum = 0;
        int CurSum = 0;
        for(int i = 0;i < A.length;i++){
            CurSum += A[i];
            if(CurSum > MaxSum){
                MaxSum = CurSum;
            }
            //如果累加和出现小于0的情况，
            //则和最大的子序列肯定不可能包含前面的元素，
            //这时将累加和置0，从下个元素重新开始累加
            if(CurSum < 0){
                CurSum = 0;
            }
        }
        return MaxSum;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] A = {2, -3, -1, 2, 3};
        System.out.println(MaxSubSumV(A));
        System.out.println(MaxSubSumD(A, 0, 4));
        System.out.println(MaxSubSumA(A));
    }

}
