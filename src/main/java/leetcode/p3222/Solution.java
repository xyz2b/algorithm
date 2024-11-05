package leetcode.p3222;

public class Solution {
    public String losingPlayer(int x, int y) {
        // 115 只能是 1 枚 75 和 4 枚 10 的组合

        // 10的硬币有多少个4枚，即除以4，商是多少，再和75枚的硬币数量对比，取两者最小数目，即游戏能进行几次

        int s = y / 4;
        int max = Math.min(s, x);

        return max % 2 == 0 ? "Bob" : "Alice";
    }
}
