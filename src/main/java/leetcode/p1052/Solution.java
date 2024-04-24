package leetcode.p1052;

public class Solution {
    // 滑动窗口
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = grumpy.length;
        // 顾客总数量
        int all = 0;
        for(int i = 0; i < n; i ++) {
            if(grumpy[i] == 0) {
                all += customers[i];
            }
        }

        int ret = all;
        // 初始minutes窗口内满意的顾客数量
        for(int i = 0; i < minutes; i++) {
            if(grumpy[i] == 1) {
                ret += customers[i];
            }
        }

        int lastRet = ret;
        for(int l = 1; l < grumpy.length - minutes + 1; l++) {
            int temp = lastRet;
            int r = l + minutes - 1;

            // 减去之前窗口内屏蔽的1的顾客数
            if(grumpy[l-1] == 1) {
                temp -= customers[l-1];
            }

            // 加上窗口内新加的1的屏蔽的顾客数
            if(grumpy[r] == 1) {
                temp += customers[r];
            }

            if(temp > ret) {
                ret = temp;
            }

            lastRet = temp;
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] customers = new int[] {2,6,6,9};
        int[] grumpy = new int[]{0,0,1,1};
        int minutes = 1;
        Solution solution = new Solution();
        System.out.println(solution.maxSatisfied(customers, grumpy, minutes));
    }
}
