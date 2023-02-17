package leetcode.p202;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    // 记录替换的数字，如果循环过程中出现了之前出现过的数字就不是快乐数
    public boolean isHappy(int n) {
        Set<Integer> nums = new HashSet<>();
        while (true) {
            int sq = 0;
            // 分解数位
            while (n / 10 != 0 || n % 10 != 0) {
                int num = n % 10;
                sq += num * num;
                n = n / 10;
            }

            n = sq;
            if(n == 1) {
                return true;
            }
            if(nums.contains(n)) {
                return false;
            } else {
                nums.add(n);
            }
        }
    }

    public static void main(String[] args) {
        int n = 1;
        Solution solution = new Solution();
        System.out.println(solution.isHappy(n));
    }
}
