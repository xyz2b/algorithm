package leetcode.p1392;

import java.util.List;

public class Solution {
    // 暴力法
    public String longestPrefix(String s) {
        // [0, len - 1], [s.length - len, s.length - 1]

        // 从最长的慢慢减短
        for(int len = s.length() - 1; len > 0; len--) {
            if(isEqual(s, 0, len - 1, s.length() - len, s.length() - 1)) {
                return s.substring(0, len);
            }
        }

        return "";
    }

    private boolean isEqual(String s, int l1, int r1, int l2, int r2) {
        for(int i = l1, j = l2; i <= r1 && j <= r2 ; i++, j++) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    // hash法，渐进的计算hash，O(1)复杂度
    // 如果是在末尾或者开头加新字符
    // 在末尾加新字符：(hashcode * B + newchar) % M
    // 在开头加新字符: (newchar * B ^ (len - 1) + hashcode) % M
    // 但是在末尾和开头减字符，就不行，要用到除法，但是 ((hashcode - oldchar) / B) % M != ((hashcode - oldchar) % M) / B，所以hash法不适用于除法减字符
    // hash法只能处理 + * 相关的运算，/ 运算不行，所以要记录下所有长度字符串的hash值
    private long MOD = (long)(1e9 + 7);
    public String longestPrefix2(String s) {
        // 预处理
        long[] pow26 = new long[s.length()];
        pow26[0] = 1;
        for(int i = 1; i < s.length(); i++) {
            pow26[i] = (pow26[i-1] * 26) % MOD;
        }

        long[] preHash = new long[s.length()];
        long[] postHash = new long[s.length()];

        // [0, i]
        preHash[0] = s.charAt(0) - 'a';
        for(int i = 1; i < s.length(); i++) {
            preHash[i] = (preHash[i - 1] * 26 + (s.charAt(i) - 'a')) % MOD;
        }

        // [i, s.length-1]
        postHash[s.length() - 1] = s.charAt(s.length() - 1) - 'a';
        for(int i = s.length() - 2; i >= 0; i--) {
            postHash[i] = ((s.charAt(i) - 'a') * (pow26[s.length() - i - 1]) + postHash[i+1]) % MOD;
        }

        // [0, len - 1], [s.length - len, s.length - 1]
        // 从最长的慢慢减短
        for(int len = s.length() - 1; len > 0; len--) {
            if(preHash[len - 1] == postHash[s.length() - len] && isEqual(s, 0, len - 1, s.length() - len, s.length() - 1)) {
                return s.substring(0, len);
            }
        }
        return "";
    }

    // lps
    // 最长对称前缀，kmp中的一个过程
    public String longestPrefix3(String s) {
        int[] lps = getLps(s);

        return s.substring(0, lps[s.length() - 1]);
    }

    private int[] getLps(String s) {
        int[] lps = new int[s.length()];
        for(int i = 1; i < s.length(); i++) {
            int a = lps[i-1];
            while (a > 0 && s.charAt(i) != s.charAt(a)) {
                a = lps[a - 1];
            }
            if(s.charAt(i) == s.charAt(a)) lps[i] = a + 1;
        }
        return lps;
    }
}
