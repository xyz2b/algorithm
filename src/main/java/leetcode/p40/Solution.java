package leetcode.p40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> rst = new ArrayList<>();
    // candidates 中的每个数字在每个组合中只能使用 一次，所以需要used数组记录使用状态
    private boolean[] used;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        used = new boolean[candidates.length];
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
            // 因为数组中有重复的数字，所以需要去除重复
            // 这里就约定一个规则来去除重复: 连续相同的值，只有从左到右的第一个值能被使用
            // 处理这里的判断逻辑以及used数组的使用，其余的逻辑和p39相同，p39数组没有重复数字(不需要这里的逻辑)，但是数组中的元素可以使用多次(不需要used数组)
            if(i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            // 已使用的略过
            if(used[i]) {
                continue;
            }

            c.add(candidates[i]);
            used[i] = true;
            combination(candidates, i, sum + candidates[i], target, c);
            // 回溯除去递归函数参数之外其他有改动且需要的变量，递归函数的参数会自己进行回溯(递归的性质)
            c.remove(c.size() - 1);
            used[i] = false;
            // 剪枝
            // 如果sum和当前i位置的元素的和已经大于等于target了，就不需要再遍历i索引之后的元素了，sum和i索引之后的元素和肯定也大于target，因为candidates是有序的
            if(sum + candidates[i] >= target) {
                return;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[] candidates = new int[] {10,1,2,7,6,1,5};
        int target = 8;
        Solution solution = new Solution();
        System.out.println(solution.combinationSum2(candidates, target));
    }
}
