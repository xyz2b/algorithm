package stringmatch.hash.p1147;

public class Solution {
    // 暴力
    public int longestDecomposition(String text) {

        // [0, len - 1], [s.length - len, s.length - 1]
        return longestDecomposition(text, 0, 0, text.length() - 1, text.length() - 1, false);
    }

    private int longestDecomposition(String text, int l1, int r1, int l2, int r2, boolean equal) {
        if(r1 == l2) {  // (a)(a)(a)
            return 1;
        } else if (r1 > l2) {
            if(equal) { // (elvto)(elvto)
                return 0;
            } else {    // (elvto)(cb)(elvto)
                return 1;
            }
        }

        if(isEqual(text, l1, r1, l2, r2)) {
            return 2 + longestDecomposition(text, r1 + 1, r1 + 1, l2 - 1, l2 - 1, true);
        } else {
            return longestDecomposition(text, l1, r1 + 1, l2 -1, r2, false);
        }
    }

    private boolean isEqual(String s, int l1, int r1, int l2, int r2) {
        for(int i = l1, j = l2; i <= r1 && j <= r2 ; i++, j++) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    // hash，渐进的计算hash，O(1)复杂度
    // 如果是在末尾或者开头加新字符
    // 在末尾加新字符：(hashcode * B + newchar) % M
    // 在开头加新字符: (newchar * B ^ (len - 1) + hashcode) % M
    private long MOD = (long)(1e9 + 7);
    private long[] pow26;
    public int longestDecomposition2(String text) {
        // 预处理
        pow26 = new long[text.length()];
        pow26[0] = 1;
        for(int i = 1; i < text.length(); i++) {
            pow26[i] = (pow26[i-1] * 26) % MOD;
        }

        // [0, len - 1], [s.length - len, s.length - 1]
        return longestDecomposition2(text, 0, text.length() - 1);
    }

    private int longestDecomposition2(String text, int left, int right) {
        if(left > right) {
            return 0;
        }

        long leftHash = 0;
        long rightHash = 0;

        // [left, i], [j, right]
        for(int i = left, j = right; i < j; i++, j--) {
            leftHash = (leftHash * 26 + text.charAt(i) - 'a') % MOD;
            rightHash = ((text.charAt(j) - 'a') * pow26[right - j] + rightHash) % MOD;
            if(leftHash == rightHash && isEqual(text, left, i, j, right)) {
                return 2 + longestDecomposition2(text, i+1, j-1);
            }
        }

        return 1;
    }

}
