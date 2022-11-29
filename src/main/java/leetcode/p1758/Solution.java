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

    public static void main(String[] args) {

    }
}
