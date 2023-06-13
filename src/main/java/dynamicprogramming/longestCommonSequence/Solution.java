package dynamicprogramming.longestCommonSequence;

import java.util.Arrays;

// LCS 最长公共子序列（子序列不需要连续）
// 给出两个字符串S1和S2，求这两个字符串的最长公共子序列的长度
// LCS(m, n): S1[0...m] 和 S2[0...n] 的最长公共子序列的长度
//  S1[m] == S2[n]: LCS(m, n) = 1 + LCS(m-1,n-1)
//  S1[m] != S2[n]: LCS(m, n) = max( LCS(m-1,n), LCS(m,n-1))
// 图例: https://e12g3g84dw.feishu.cn/docx/Fc4MdP1ZzoixppxRRXjc0jJNnib
public class Solution {

    public int lcs(String s1, String s2) {
        return lcs(s1, s2, s1.length() - 1, s2.length() - 1);
    }

    private int lcs(String s1, String s2, int s1Index, int s2Index) {
        if(s1Index < 0 || s2Index < 0) {
            return 0;
        }

        if(s1.charAt(s1Index) == s2.charAt(s2Index)) {
            return 1 + lcs(s1, s2, s1Index - 1, s2Index - 1);
        } else {
            return Math.max(lcs(s1, s2, s1Index - 1, s2Index), lcs(s1, s2, s1Index, s2Index - 1));
        }
    }

    private int[][] memo;
    public int lcs2(String s1, String s2) {
        memo = new int[s1.length()][s2.length()];
        for(int i = 0; i < s1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        return lcs2(s1, s2, s1.length() - 1, s2.length() - 1);
    }

    private int lcs2(String s1, String s2, int s1Index, int s2Index) {
        if(s1Index < 0 || s2Index < 0) {
            return 0;
        }

        if(memo[s1Index][s2Index] != -1) {
            return memo[s1Index][s2Index];
        }

        if(s1.charAt(s1Index) == s2.charAt(s2Index)) {
            memo[s1Index][s2Index] = 1 + lcs2(s1, s2, s1Index - 1, s2Index - 1);
        } else {
            memo[s1Index][s2Index] =  Math.max(lcs2(s1, s2, s1Index - 1, s2Index), lcs2(s1, s2, s1Index, s2Index - 1));
        }
        return memo[s1Index][s2Index];
    }

    public int lcs3(String s1, String s2) {
        if(s1.length() == 0 || s2.length() == 0) return 0;
        int[][] memo = new int[2][s2.length()];
        for(int i = 0; i < 2; i++) {
            Arrays.fill(memo[i], -1);
        }

        for(int s2Index = 0; s2Index < s2.length(); s2Index++) {
            memo[0][s2Index] = (s1.charAt(0) == s2.charAt(s2Index) ? 1 : 0);
        }

        for(int s1Index = 1; s1Index < s1.length(); s1Index++) {
            for(int s2Index = 0; s2Index < s2.length(); s2Index++) {
                if(s1.charAt(s1Index) == s2.charAt(s2Index)) {
                    if(s2Index < 1) {
                        memo[s1Index % 2][s2Index] = 1;
                    } else {
                        memo[s1Index % 2][s2Index] = 1 + memo[(s1Index - 1)%2][s2Index - 1];
                    }
                } else {
                    if(s2Index < 1) {
                        memo[s1Index % 2][s2Index] = Math.max(memo[(s1Index-1)%2][s2Index], 0);
                    } else {
                        memo[s1Index % 2][s2Index] = Math.max(memo[(s1Index-1)%2][s2Index], memo[s1Index%2][s2Index-1]);
                    }
                }
            }
        }

        return memo[(s1.length()-1)%2][s2.length()-1];

    }

    public static void main(String[] args) {
        String s1 = "AAACCGTGAGTTATTCGTTCTAGAA";
        String s2 = "";
        Solution solution = new Solution();
        System.out.println(solution.lcs3(s1, s2));
    }
}
