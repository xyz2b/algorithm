package leetcode.p77;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> rst = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> combine(int n, int k) {
        used = new boolean[n];
        combine(n, k, 0, new ArrayList<>());
        return rst;
    }

    // p中保留了index个元素的组合
    // 向这个排列的末尾添加第index+1个元素，获得一个有index+1个元素的排列
    private void combine(int n, int k, int index, List<Integer> p) {
        if(index == k) {
            rst.add(new ArrayList<>(p));
            return;
        }

        // 记录下当前层遍历了哪些元素，为了后面一层遍历完之后回溯used状态
        List<Integer> curLevelNums = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(!used[i - 1]) {
                p.add(i);
                used[i - 1] = true;
                curLevelNums.add(i);
                combine(n, k, index + 1, p);
                p.remove(p.size() - 1);
            }
        }
        // 整个一层遍历完了，再回溯这一层遍历过的元素的used状态
        for(int i : curLevelNums) {
            used[i - 1] = false;
        }
        return;
    }

    public static void main(String[] args) {
        int n = 1;
        int k = 1;
        Solution solution = new Solution();
        System.out.println(solution.combine(n, k));
    }
}
