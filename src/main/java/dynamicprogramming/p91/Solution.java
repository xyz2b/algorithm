package dynamicprogramming.p91;

import java.util.Arrays;

public class Solution {
    public int numDecodings(String s) {
        return decodingsNum(s, 0);
    }

    // 暴力解法 - 自上而下
    private int decodingsNum(String s, int index) {
        if(index == s.length()) {
            return 1;
        }

        if(s.charAt(index) == '0') {
            return 0;
        }

        int rst = 0;
        // index是一个有效编码
        rst += decodingsNum(s, index + 1);

        // index和index+1联合起来是一个有效的编码，1X和20-26
        if(index + 1 < s.length() && (s.charAt(index) == '1' || (s.charAt(index) == '2' && s.charAt(index + 1) <= '6'))) {
            rst += decodingsNum(s, index + 2);
        }

        return rst;
    }

    // 记忆化搜索 - 自上而下
    // s[index, s.length() - 1]子字符串，有多少种解码方法
    private int[] memo;

    public int numDecodings2(String s) {
        memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return decodingsNum2(s, 0);
    }

    // 暴力解法 - 自上而下
    private int decodingsNum2(String s, int index) {
        if(index == s.length()) {
            return 1;
        }

        if(s.charAt(index) == '0') {
            return 0;
        }

        if(memo[index] != -1) {
            return memo[index];
        }

        int rst = 0;
        // index是一个有效编码
        rst += decodingsNum2(s, index + 1);

        // index和index+1联合起来是一个有效的编码，1X和20-26
        if(index + 1 < s.length() && (s.charAt(index) == '1' || (s.charAt(index) == '2' && s.charAt(index + 1) <= '6'))) {
            rst += decodingsNum2(s, index + 2);
        }

        memo[index] = rst;

        return rst;
    }

    // 动态规划 - 自下而上
    public int numDecodings3(String s) {
        if(s.equals("0")) return 0;
        int[] memo = new int[s.length() + 1];

        // 求解s[0, s.length() - 1]的解码方法数量 = s[1, s.length() - 1] 和 s[2, s.length() - 2]的解码方法数量之和，当然是有条件的
        // s[0]不能为0能成功解码，才会进行后面s[1, s.length() - 1] 和 s[2, s.length() - 2]的解码
        // s[1, 2]不能大于26才能成功解码，才会进行后面s[2, s.length() - 2]的解码
        if(s.charAt(s.length() - 1) != '0') {
            memo[s.length() - 1] = 1;
        }

        for(int i = s.length() - 2; i >= 0; i--) {
            if(s.charAt(i) != '0') {
                memo[i] += memo[i + 1];
                if(i + 1 < s.length() && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6'))) {
                    memo[i] += (i + 2 >= s.length() ? 1 : memo[i + 2]);
                }
            }

        }

        return memo[0];
    }

    public static void main(String[] args) {
        String s = "10";
        Solution solution = new Solution();
        System.out.println(solution.numDecodings3(s));
    }
}
