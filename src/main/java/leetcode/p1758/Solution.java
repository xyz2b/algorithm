package leetcode.p1758;

public class Solution {
    public int minOperations(String s) {
        int zRst = s.charAt(0) == '0' ? 0 : 1;
        char before = '0';
        for(int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(cur == before) {
                zRst++;
                before = cur == '1' ? '0' : '1';
            } else {
                before = cur;
            }
        }

        int oRst = s.charAt(0) == '0' ? 1 : 0;
        before = '1';
        for(int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(cur == before) {
                oRst++;
                before = cur == '1' ? '0' : '1';
            } else {
                before = cur;
            }
        }
        return Math.min(zRst, oRst);
    }

    /**
     * 根据题意，经过多次操作，sss 可能会变成两种不同的交替二进制字符串，即：
     *  开头为 0，后续交替的字符串；
     *  开头为 1，后续交替的字符串。
     * 注意到，变成这两种不同的交替二进制字符串所需要的最少操作数加起来等于 s 的长度，
     * 我们只需要计算出变为其中一种字符串的最少操作数，就可以推出另一个最少操作数，然后取最小值即可。
     * */
    public int minOperations2(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != (char) ('0' + i % 2)) {
                cnt++;
            }
        }
        return Math.min(cnt, s.length() - cnt);
    }
}
