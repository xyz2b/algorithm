package leetcode.p1624;

public class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int rst = -1;

        for(int i = 0; i < s.length(); i++) {
            for(int j = s.length() - 1; j > i; j--) {
                if(s.charAt(i) == s.charAt(j)) {
                    rst = Math.max(rst, j - i - 1);
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        String s = "cabbac";
        Solution solution = new Solution();
        System.out.println(solution.maxLengthBetweenEqualCharacters(s));
    }
}
