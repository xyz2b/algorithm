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


    public List<List<Integer>> combine2(int n, int k) {
        combine2(n, k, 1, new ArrayList<>());
        return rst;
    }

    // 求解C(n,k)，当前已经找到的组合存储在c中，需要从start开始搜索新的元素
    private void combine2(int n, int k, int start, List<Integer> c) {
        if(c.size() == k) {
            rst.add(new ArrayList<>(c));
            return;
        }

        // 回溯法的剪枝
        // c中还有k - c.size()个空位，所以[i,n]中至少要有k - c.size()个元素
        // i最多为 n - (k - c.size()) + 1
        for(int i = start; i <= n - (k - c.size()) + 1; i++) {
            c.add(i);
            // start之前包含start的元素已经遍历过了，不需要再遍历了，存在start之前包含start元素的组合已经都尝试过了，所以在下一次寻找过程中，只需要从i+1开始往后寻找
            combine2(n, k, i + 1, c);
            // 回溯
            c.remove(c.size() - 1);
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
