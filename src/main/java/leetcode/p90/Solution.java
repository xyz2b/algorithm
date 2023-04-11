package leetcode.p90;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> rst = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        subsets(nums, 0, new ArrayList<>());
        return rst;
    }

    // 当前已经找到的子集存储在c中，需要从startIndex开始搜索新的元素
    private void subsets(int[] nums, int startIndex, List<Integer> s) {
        rst.add(new ArrayList<>(s));

        // 给出的数字集合中有重复数字
        for(int i = startIndex; i < nums.length; i++) {
            // 因为数组中有重复的数字，所以需要去除重复
            // 这里就约定一个规则来去除重复: 连续相同的值，只有从左到右的第一个值能被使用
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            s.add(nums[i]);
            subsets(nums, i + 1, s);
            s.remove(s.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,2};
        Solution solution = new Solution();
        System.out.println(solution.subsetsWithDup(nums));
    }
}
