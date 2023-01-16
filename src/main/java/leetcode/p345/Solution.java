package leetcode.p345;

public class Solution {
    // 双指针碰撞
    public String reverseVowels(String s) {
        char[] rst = new char[s.length()];

        int l = 0, r = s.length() - 1;
        while (l <= r) {
            char lc = s.charAt(l);
            char rc = s.charAt(r);
            if(!isVowel(lc)) {
                rst[l] = lc;
                l++;
            } else if (!isVowel(rc)) {
                rst[r] = rc;
                r--;
            } else {    // isVowel(lc) && isVowel(rc)   反转
                rst[l] = rc;
                rst[r] = lc;
                l++;
                r--;
            }
        }

        return new String(rst);
    }


    private boolean isVowel(char c) {
        return c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U';
    }

    public static void main(String[] args) {
        String s = "leetcode";
        Solution solution = new Solution();
        System.out.println(solution.reverseVowels(s));
    }
}
