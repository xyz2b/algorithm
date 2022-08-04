package leetcode.p1403;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> rst = new ArrayList<>();

        int n = nums.length;
        Arrays.sort(nums);
        int j = n - 1;
        rst.add(nums[j]);

        int sum = 0;
        int i = 0;
        // j初始化时指向最大的元素
        // i初始化时指向最小的元素

        // 因为排过序，所以数组后面的元素比前面的元素大
        // 先从数组最后选最大的元素作为结果集，
        //  然后从开头将比较小的元素进行累加
        //  如果累加和大于等于了所选择的元素，就将累加和去掉所选择的元素（前面累加和去掉了所选择的元素，后面再累加所有的元素只要小于新选定的元素即可加入结果集），同时将当前选择元素的前一个元素加入结果集，将其作为下次比较的对象
        //  如果累加和小于所选择元素，那么继续往后累加较小的元素
        while (i < j) {
            // 如果累加和已经超过当前遍历的比较大的元素
            if (sum + nums[i] >= nums[j]) {
                // 将当前累加和减去比较的这个比较大的元素
                sum -= nums[j];
                // 将j指向前一个元素，比当前遍历这个比较大的元素，小一点的元素
                j--;
                rst.add(nums[j]);
            }

            sum += nums[i];
            i++;
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] nums = {4,4,7,6,7};
        Solution solution = new Solution();
        List<Integer> rst = solution.minSubsequence(nums);
        for(int i : rst) {
            System.out.println(i);
        }
    }
}
