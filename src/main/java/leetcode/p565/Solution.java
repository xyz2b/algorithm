package leetcode.p565;

import java.util.HashMap;

public class Solution {
    public int arrayNesting(int[] nums) {
        // nums数组值 --> 索引的映射，nums中不包含重复元素
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for(int i = 0; i < nums.length; i ++) {
            map.put(nums[i], i);
        }

        // dp的索引表示构成集合S的nums中元素的索引，值表示是否已经被加入集合S（1表示已加入，0表示为加入），
        int[] dp = new int[nums.length];

        int rst = 0;
        for(int i = 0; i < nums.length; i++) {

            // 加入集合的元素数量
            int sLen = 1;
            int j = i;
            while (dp[j] == 0) {
                // 将nums中索引为j的值加入集合
                dp[j] = 1;
                // 找到索引对应的值
                int value = nums[j];
                // 以上面的值作为索引的项是否存在
                int index = map.getOrDefault(value, -1);
                if (index == -1) {
                    sLen = 0;
                    break;
                }
                // 存在，且没有被加入集合
                if (dp[value] == 0) {
                    // 将上面的值作为下一个遍历的索引
                    j = value;
                    sLen++;
                }
            }

            rst = Math.max(rst, sLen);
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] data = new int[] {5,4,0,3,1,6,2};
        System.out.println(solution.arrayNesting(data));
    }
}
