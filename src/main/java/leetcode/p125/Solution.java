package leetcode.p125;

public class Solution {
    // 双指针碰撞
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            char lc = s.charAt(l);
            char rc = s.charAt(r);
            if(!isNumberLetter(lc)) {
                l++;
            } else if (!isNumberLetter(rc)) {
                r--;
            } else { // isNumberLetter(lc) && isNumberLetter(rc)
                if(Character.toLowerCase(lc) == Character.toLowerCase(rc)) {
                    l++;
                    r--;
                } else {
                    return false;
                }
            }
        }

        // l == r 表示经过上面的循环之后，只剩一个字符了，除了这个字符其他字符都满足回文串的定义，则这个字符串必定是回文串
        return true;
    }

    // 根据ascii码值来判断字符是否是数字或字母
    private boolean isNumberLetter(char c) {
        return (c - '0' >= 0 && c - '0' <= 9) || (c - 'A' >= 0 && c - 'A' <= 25) || (c - 'a' >= 0 && c - 'a' <= 25);
    }

    public static void main(String[] args) {
        String s = "01 A, 10";
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(s));
    }
}
