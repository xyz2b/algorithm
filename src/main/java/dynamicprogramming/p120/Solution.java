package dynamicprogramming.p120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    private List<List<Integer>> triangle;
    // 记录第index层第i个元素的和
    private List<List<Integer>> memo;
    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        this.memo = new ArrayList<>();

        for(List<Integer> t : triangle) {
            List<Integer> l  = new ArrayList<>(t.size());
            for(int i = 0; i < t.size(); i++) {
                l.add(Integer.MAX_VALUE);
            }
            memo.add(l);
        }

        return min(0, 0);
    }

    // 记忆搜索 - 自上而下
    // 返回第index层，第i个元素的最小路径和
    private int min(int index, int i) {
        if(index == triangle.size()) {
            return 0;
        }

        int c = memo.get(index).get(i);
        if(c != Integer.MAX_VALUE) {
            return c;
        }

        int min = Integer.MAX_VALUE;
        // 求出 下一层 index+1层，第i 和 i+1个元素的最小路径和
        for(int x = 0; x <= 1; x++) {
            int childSum = min(index + 1, i + x);
            if(childSum < min) {
                min = childSum;
            }
        }
        // 然后加上当前节点元素，就是当前节点的最小路径和
        int n = triangle.get(index).get(i) + min;
        memo.get(index).set(i, n);
        return n;
    }

    // 动态规划 - 自下而上，从小数据到大数据
    public int minimumTotal2(List<List<Integer>> triangle) {
        List<List<Integer>>  memo = new ArrayList<>();

        for(List<Integer> t : triangle) {
            List<Integer> l  = new ArrayList<>(t);
            memo.add(l);
        }

        // (index, i)的最小和路径等于 (index + 1, i)最小路径和 与 (index + 1, i + 1)最小路径和中的较小值 + (index, i)的值
        for(int i = triangle.size() - 2; i >= 0; i--) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                int min = Math.min(memo.get(i + 1).get(j), memo.get(i + 1).get(j + 1));
                memo.get(i).set(j, min + triangle.get(i).get(j));
            }
        }

        return memo.get(0).get(0);
    }

    public static void main(String[] args) {
        int[][] t = {{2},{3,4},{6,5,7},{4,1,8,3}};
        List<List<Integer>> triangle = new ArrayList<>();
        for(int[] i : t) {
            List<Integer> n = new ArrayList<>();
            for(int x : i) {
                n.add(x);
            }
            triangle.add(n);
        }
        Solution solution = new Solution();
        System.out.println(solution.minimumTotal2(triangle));
    }
}
