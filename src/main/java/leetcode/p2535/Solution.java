package leetcode.p2535;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 然后缓存
    public int differenceOfSum(int[] nums) {
        int numSum = 0;
        int elemSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums) {
            elemSum += num;

            if(map.containsKey(num)) {
                numSum += map.get(num);
            } else {
                int n = numSum(num);
                numSum += n;
                map.put(num, n);
            }
        }

        return Math.abs(numSum - elemSum);
    }

    private int numSum(int num) {
        int ret = 0;
        while (num > 0) {
            ret += num % 10;
            num /= 10;
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSum(200));
    }
}
