package leetcode.p2741;

import java.util.Arrays;

public class Solution {
    static final int MOD = 1000000007;
    int[] nums;
    int n;

    public int specialPerm(int[] nums) {
        this.nums = nums;
        n = nums.length;

        int ret = 0;
        for(int i = 0; i < nums.length; i++) {
            int[] visited = new int[n];
            visited[i] = 1;
            ret = (ret + dfs(i, 1, visited)) % MOD;
        }

        return ret;
    }

    // 暴力解法
    // 加入第i个元素之后，组成特别队列的组合数，c为当前访问的是第几个元素
    public int dfs(int i, int c, int[] visited) {
        if(c == n) {    // 如果整个数组元素都访问一遍了，说明找到了一个符合条件的答案
            return 1;
        }

        int ret = 0;
        for(int j = 0; j < n; j++) {
            if(visited[j] == 1) {   // 已经访问过的元素不能再使用
                continue;
            }

            if(nums[i] % nums[j] != 0 && nums[j] % nums[i] != 0) {
                continue;
            }

            int[] nextVisited = Arrays.copyOf(visited, n);
            nextVisited[j] = 1;     // 每个支路都是单独的visited数组
            // 比如访问过了20，接下来要访问100、5、10，这些满足条件的数值，他们的visited应该都是单独的
            // 比如接下来要访问100，那么visited就是20和100的置位，递归之后，再循环访问5，visited就是20和5置位，以此类推
            ret = (ret + dfs(j, c + 1, nextVisited)) % MOD;
        }
        return ret;
    }


    // 记忆化搜索优化
    int[][] f;
    public int specialPerm2(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.f = new int[1 << n][n];
        for (int i = 0; i < 1 << n; i++) {
            Arrays.fill(f[i], -1);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 每次循环开始都考虑所有的元素
            res = (res + dfs2((1 << n) - 1, i)) % MOD;
        }
        return res;
    }

    // state某一位为1，表示该位索引对应到nums数组中的数值在考虑的范围内
    // 加入第i个元素之后，组成特别队列的组合数
    public int dfs2(int state, int i) {
        if (f[state][i] != -1) {    // 记忆
            return f[state][i];
        }
        if (state == (1 << i)) {    // 考虑的元素只有第i个这一个元素
            return 1;
        }
        f[state][i] = 0;
        for (int j = 0; j < n; j++) {
            if (i == j || (state >> j & 1) == 0) {  // 如果第j个元素不在该次的考虑范围内就略过
                continue;
            }
            if (nums[i] % nums[j] != 0 && nums[j] % nums[i] != 0) { // 如果不满足条件就略过
                continue;
            }
            // dfs2(state ^ (1 << i), j) 递归的是 加入第i个元素之前，并且加入第j个元素之后组成特别队列的组合数（没有考虑第i个元素时，考虑加入第j个元素得到的组合数）（从考虑的集合中把第i个元素去掉，即把state中第i位置为0）
            // 然后和之前加入第j元素（但没有加入元素i）之后的组合数叠加，即加入第i元素之后的总组合数
            f[state][i] = (f[state][i] + dfs2(state ^ (1 << i), j)) % MOD;
        }
        return f[state][i];
    }


    public static void main(String[] args) {
        int[] nums = {20,100,50,5,10,70,7};
        Solution solution = new Solution();
        System.out.println(solution.specialPerm2(nums));
    }
}
