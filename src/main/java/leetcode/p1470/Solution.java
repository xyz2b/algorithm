package leetcode.p1470;

import java.util.Arrays;

public class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] rst = new int[nums.length];

        // 结果数组中偶数索引都是x
        // 结果数组中奇数索引都是y
        int x = 0;  // nums数组中，x的索引
        int y = n;  // nums数组中，y的索引
        for(int i = 0; i < rst.length; i++) {
            if(i % 2 == 0) {    // 偶数
                rst[i] = nums[x];
                x++;
            } else {    // 奇数
                rst[i] = nums[y];
                y++;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3)));
    }
}
