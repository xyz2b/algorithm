package leetcode.p915;

public class Solution {
    public int partitionDisjoint(int[] nums) {
        // 左数组中最大元素索引
        int maxIndex = 0;
        // nums中分割左右数组的索引（为左数组中最后一个元素）
        int splitIndex = 0;
        // 遍历索引
        int i = 1;
        // 暂存下比当前maxIndex处元素大的最大元素索引，如果此元素后面还有小于此时maxIndex处的元素，如果以此位置(flag)直接为分割线就不满足条件，就需要移动分隔边界到小于max的元素处，以及更新max为此时最大元素的索引，即flag
        int flag = -1;
        while (i < nums.length) {
            if(nums[i] < nums[maxIndex]) {    // nums[i] < nums[maxIndex]，小于最大的元素
                splitIndex = i;
                i++;
                if(flag != -1) {
                    maxIndex = flag;
                    flag = -1;
                }
            } else {    // nums[i] >= nums[maxIndex]，大于等于最大的元素
                // 先记录下来比当前maxIndex处元素大的最大元素索引，直到后面遍历碰到比当前maxIndex处元素小的元素时再进行更新
                if(flag == -1) {
                    flag = i;
                } else if(nums[i] > nums[flag]) {
                    flag = i;
                } else { // nums[flag] >= nums[i]，此时flag不需要更新，因为当前遍历元素没有上次记录的flag处的元素大

                }

                i++;
            }
        }

        return splitIndex + 1 == nums.length ? 1 : splitIndex + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {24,11,49,80,63,8,61,22,73,85};
        Solution solution = new Solution();
        System.out.println(solution.partitionDisjoint(nums));
    }
}
