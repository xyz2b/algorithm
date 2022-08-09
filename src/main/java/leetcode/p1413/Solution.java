package leetcode.p1413;

public class Solution {
    public int minStartValue(int[] nums) {
        // 选定的起始数值
        int start = 1;
        // 当前的累加和
        int sum = 1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] + sum >= 1) {
                // 如果当前累加和加上当前遍历数字，大于等于1，表示之前选定的start可以满足需要，直接得到新的累加和即可
                sum += nums[i];
            } else {
                // 如果当前累加和加上当前遍历数字，小于1，表示之前选定的start不能满足要求，需要将start变大，变为多大呢，就变为能正好使当前累加和加上当前遍历数字的和等于1即可
                // 即将之前选定的start增加 (1 减去 当前累加和加上当前遍历数字的和) 这么多，就可以使当前累加和加上当前遍历数值的和为1
                start += 1 - (nums[i] + sum);
                // 变大start就是为了使当前累加和加上当前遍历数字的和等于1，所以直接将当前累加和置为1即可
                sum = 1;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,-2,6,-3};
        Solution solution = new Solution();
        System.out.println(solution.minStartValue(nums));
    }
}
