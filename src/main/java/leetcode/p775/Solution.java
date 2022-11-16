package leetcode.p775;

public class Solution {
    // 一个局部倒置一定是一个全局倒置，因此要判断数组中局部倒置的数量是否与全局倒置的数量相等，只需要检查有没有非局部倒置就可以了。
    // 这里的非局部倒置指的是 nums[i]>nums[j]，其中 i<j−1。
    public boolean isIdealPermutation(int[] nums) {
        for(int i = 0; i < nums.length - 2; i++) {
            for(int j = i + 2; j < nums.length; j++) {
                if(nums[i] > nums[j])
                    return false;
            }
            if(nums[i+1] < nums[i]) {   // 提前结束条件：如果当前i没有非局部倒置，那么如果i+1元素比i小，那么i+1肯定也没有非局部倒置，直接跳过i+1的处理
                i++;
            }
        }
        return true;
    }

    /**
     * 进一步的，对于每一个 nums[i] 判断是否存在一个 j>i+1 使得 nums[i]>nums[j] 即可。
     * 这和检查 nums[i]>min(nums[i+2],…,nums[n−1]) 是否成立是一致的（有一个最小值能够让nums[i]大于它就是存在非局部倒置，取最小值就是让nums[i]大于它更容易满足）。
     * 因此我们维护一个变量 minSuffix=min(nums[i+2],…,nums[n−1])，
     * 倒序遍历 [0,n−3] 中的每个 i, 如有一个 i 使得 nums[i]>minSuffix 成立，返回 false，若结束遍历都没有返回 false，则返回 true。
     * */
    public boolean isIdealPermutation2(int[] nums) {
        int n = nums.length, minSuff = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] > minSuff) {
                return false;
            }
            minSuff = Math.min(minSuff, nums[i + 1]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {0,2,3,1,4};
        Solution solution = new Solution();
        System.out.println(solution.isIdealPermutation(nums));
    }
}
