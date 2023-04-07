package leetcode.p131;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    private List<List<String>> rst = new ArrayList<>();
    public List<List<String>> partition(String s) {
        partition(s, 0, 0, new ArrayList<>(), false);
        return rst;
    }

    // palindromes 是 s[0, l-1] 所构成的回文串 列表
    // isPalindrome是 s[0, l-1] 是否有构成回文串
    // s[l, r]所构成的回文串列表
    private void partition(String s, int l, int r, List<String> palindromes, boolean isPalindrome) {
        if(l >= s.length() || r >= s.length() || l > r) {
            if(isPalindrome) {
                rst.add(palindromes);
            }
            return;
        }
        for(int index = r; index < s.length(); index++) {
            List<String> list = new ArrayList<>(palindromes);
            if(isPalindrome(s, l, index)) { // s[l, r]是回文串，继续往下遍历，即遍历s[r+1, r+1]。如果s[l, r]不是回文串，则不继续往下遍历，扩展[l, r]，扩展成[l, r+1]，看它是不是回文串
                list.add(s.substring(l, index + 1));
                partition(s, index + 1, index + 1, list, true);
            }
        }
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if(s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        // l == r，奇数个字母，只剩中间那个字母，也是回文串
        return true;
    }

    public static void main(String[] args) {
        String s = "aaaa";
        Solution solution = new Solution();
        System.out.println(solution.partition(s));
    }
}
