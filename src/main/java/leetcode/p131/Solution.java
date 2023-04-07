package leetcode.p131;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    private List<List<String>> rst = new ArrayList<>();
    public List<List<String>> partition(String s) {
        partition(s, 0, 0, new ArrayList<>());
        return rst;
    }

    private void partition(String s, int l, int r, List<String> palindromes) {
        if(l >= s.length() || r >= s.length() || l > r) {
            rst.add(palindromes);
            return;
        }

        for(int i = 0; i < r - l; i++) {
            List<String> list = new ArrayList<>(palindromes);
            if(isPalindrome(s, l, r + i)) {
                list.add(s.substring(l, r + 1));
                partition(s, l + 1, r + 1, list);
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
        String s = "aab";
        Solution solution = new Solution();
        System.out.println(solution.partition(s));
    }
}
