package leetcode.p216;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> rst = new ArrayList<>();
    // 每个数字 最多使用一次
    private boolean[] used;

    public List<List<Integer>> combinationSum3(int k, int n) {
        used = new boolean[10];
        combination(1, 0, k, n, new ArrayList<>());
        return rst;
    }

    // 当前已经找到c.size()个元素 其和为sum，它们存储在c中，需要从start开始添加新的元素，使sum趋近于target，同时c.size()元素个数等于k
    private void combination(int start, int sum, int k, int target, List<Integer> c) {
        // 递归终止条件
        if(c.size() == k && sum == target) {
            rst.add(new ArrayList<>(c));
            return;
        }
        // 递归终止条件，(和已经大于target)，或者 (元素数量大于等于k了，但是sum和依旧不等于target。元素数量等于k并且sum等于target在上面已经判断了)
        if(sum > target || c.size() >= k) {
            return;
        }

        // 给出的数字集合中，无重复数字，只有1-9
        for(int i = start; i <= 9; i++) {
            c.add(i);
            used[i] = true;
            combination(i + 1, sum + i, k, target, c);
            c.remove(c.size() - 1);
            used[i] = false;

            // 剪枝
            // 如果sum和当前i元素的和已经大于等于target了，就不需要再遍历i之后的元素了，sum和i之后的元素和肯定也大于target，因为i是从1到9递增的
            if(sum + i >= target) {
                return;
            }
            // 如果此时c中的元素个数已经大于等于k个了，但是和依旧不等于target，那也不需要再继续往深度遍历，再往集合里新增元素了
            if(c.size() >= k) {
                return;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int k = 3;
        int n = 7;
        Solution solution = new Solution();
        System.out.println(solution.combinationSum3(k, n));
    }
}
