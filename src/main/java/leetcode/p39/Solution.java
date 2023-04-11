package leetcode.p39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> rst = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        combination(candidates, 0, 0, target, new ArrayList<>());
        return rst;
    }

    // 当前已经找到和为sum的元素存储在c中，需要从index开始添加新的元素，使sum趋近于target
    private void combination(int[] candidates, int index, int sum, int target, List<Integer> c) {
        // 递归终止条件
        if(sum == target) {
            rst.add(new ArrayList<>(c));
            return;
        }
        // 递归终止条件
        if(sum > target) {
           return;
        }

        for(int i = index; i < candidates.length; i++) {
            c.add(candidates[i]);
            combination(candidates, i, sum + candidates[i], target, c);
            c.remove(c.size() - 1);
            // 剪枝
            // 如果sum和当前i位置的元素的和已经大于等于target了，就不需要再遍历i索引之后的元素了，sum和i索引之后的元素和肯定也大于target，因为candidates是有序的
            if(sum + candidates[i] >= target) {
                return;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[] candidates = new int[] {2,3,6,7};
        int target = 7;
        Solution solution = new Solution();
        System.out.println(solution.combinationSum(candidates, target));
    }
}
