package leetcode.p2708;

import java.util.Arrays;

class Solution {
    public long maxStrength(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }

        Arrays.sort(nums);
        // 正的全部选上，负的选最大偶数个（从大往小选）
        // 统计正数 负数以及零的索引区间
        int negNum = 0;
        int zeroNum = 0;
        int posNum = 0;

        long ret = 1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 0) {
                negNum++;
                ret *= nums[i];
            } else if(nums[i] > 0) {
                posNum++;
                ret *= nums[i];
            } else {
                zeroNum++;
            }
        }

        // 如果没有正数，也没有负数，但是有0，结果为0
        // 如果没有正数，负数只有一个，但是有0，结果也为0
        if(posNum == 0 && negNum <= 1 && zeroNum != 0) {
           return 0;
        }

        // 如果负数的数量为奇数个(负数个数为0，即没有负数，不用做处理，剩下的就是正数和零)，要把绝对值最小的那个负数剔除(索引最大的那个负数，因为已经排过序了)
        if(negNum % 2 != 0) {
           ret /= nums[negNum - 1];
        }

        return ret;
    }
}
