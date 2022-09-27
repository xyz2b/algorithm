package leetcode.p788;

public class Solution {
    public int rotatedDigits(int n) {
        // 含有不是0、1、8、2、5、6、9的数字都不满足要求
        // 只含有0、1、8的数字都不满足要求
        // 单独含有2、5、6、9，是满足要求的
        // 不仅单独含有2、5、6、9，且其他数字是0、1、8的，满足要求
        // 有多少种排列组合
        // 一个数字
        // 2、5、6、9
        // 两个数字
        // 12、15、16、19
        // 20、21、22、25、26、28、29
        // 50、51、52、55、56、58、59
        // 60、61、62、65、66、68、69
        // 82、85、86、89
        // 90、91、92、95、96、98、99

        int rst = 0;
        for(int i = 1; i <= n; i++) {
            // 分解数位
            int flag = 1;   // 到目前遍历数字时，是否不含有3、4、7
            int eFlag = -1;  // 到目前遍历数字时，是否都只含有0、1、8（-1为初始状态，第一次遇到0、1、8时才改成这个状态位为1。如果之前已经遍历过除0、1、8之外的符合要求的数字了(2、5、6、9)，此时eFlag为0，就表明num不止含有0、1、8了，就不需要改变这个状态为1了；如果是连续多次遇到0、1、8也不需要重复修改该状态了，之前已经修改为1了）
            int x = i;
            while (x != 0) {
                int y = x % 10;
                if(y == 3 || y == 4 || y == 7) {
                    flag = 0;
                    break;
                }
                if(eFlag == -1 && (y == 0 || y == 1 || y == 8)) {
                    eFlag = 1;
                }
                if(y == 2 || y == 5 || y == 6 || y == 9) {
                    eFlag = 0;
                }
                x = x / 10;
            }

            if(flag == 1 && eFlag == 0) {   // num中不含有3、4、7，并且不只含有0、1、8
                rst++;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rotatedDigits(100));
    }
}
