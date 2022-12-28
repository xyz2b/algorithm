package leetcode.p1750;

public class Solution {
    public int minimumLength(String s) {
        int l = 0, r = s.length() - 1;

        char e = 0;
        while (l < r) {
            // 如果两边字符相等，就更新e
            if(s.charAt(l) == s.charAt(r)) {
                e = s.charAt(l);
                l++;
                r--;
            } else if(s.charAt(l) == e) {
                l++;
            } else if (s.charAt(r) == e) {
                r--;
            } else {
                break;
            }
        }

        // 最后剩了中间的一个字符
        // 如 bbb 最后会剩下中间一个 b，也是可以删除的
        if(s.charAt(r) == e) {
            return 0;
        }

        return  r - l + 1;
    }

    public static void main(String[] args) {
        String s = "bbb";
        Solution solution = new Solution();
        System.out.println(solution.minimumLength(s));
    }
}
