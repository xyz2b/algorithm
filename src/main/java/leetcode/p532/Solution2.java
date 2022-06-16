package leetcode.p532;

import java.util.HashSet;
import java.util.Set;

public class Solution2 {
    // 利用HashSet去重
    public int findPairs(int[] nums, int k) {
        Set<Integer> rst = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        // res == num - k，说明res和num距离为k
        // res == num + k，也说明res和num距离为k
        for (int i = 0; i < nums.length; i++) {
            if (visited.contains(nums[i] - k)) {    // 如果当前遍历的元素 - k 在 visited set中已经存在，即说明之前遍历的元素和当前遍历的元素距离为k，在结果数组中添加之前遍历的元素即可
                rst.add(nums[i] - k);
            }
            if (visited.contains(nums[i] + k)) {    // 如果当前遍历的元素 + k 在 visited set中已经存在，即说明之前遍历的元素和当前遍历的元素距离为k，在结果数组中添加当前元素即可
                rst.add(nums[i]);
            }
            visited.add(nums[i]);   // 已经遍历过的元素添加到visited set
        }

        // 为什么加入rst中的上面是num - k，下面是num
        // 因为遍历到num时，如果 visited 数组中，既有 num - k 又有 num + k，这样如果rst add的都是num，就会丢失一次满足的对数

        return rst.size();
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = {1, 1, 3, 3, 5, 6};
        int k = 2;
        System.out.println(solution.findPairs(nums, k));
    }
}
