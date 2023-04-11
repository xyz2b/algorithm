package leetcode.p78;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> rst = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subsets(nums, 0, new ArrayList<>());
        return rst;
    }

    // 当前已经找到的子集存储在c中，需要从startIndex开始搜索新的元素
    private void subsets(int[] nums, int startIndex, List<Integer> s) {
        rst.add(new ArrayList<>(s));

        // 给出的数字集合中，无重复数字
        for(int i = startIndex; i < nums.length; i++) {
            s.add(nums[i]);
            subsets(nums, i + 1, s);
            s.remove(s.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        Solution solution = new Solution();
        System.out.println(solution.subsets(nums));
    }
}
