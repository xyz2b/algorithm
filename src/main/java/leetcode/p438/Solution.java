package leetcode.p438;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] freq = new int[26];
        boolean[] existed = new boolean[26];
        for(int i = 0; i < p.length(); i++) {
            freq[p.charAt(i) - 'a']++;
            existed[p.charAt(i) - 'a'] = true;
        }

        List<Integer> rst = new ArrayList<>();

        int l = 0, r = -1;  // 滑动窗口s[l, r]
        int count = 0;
        while (l < s.length()) {
            if(r + 1 < s.length() && freq[s.charAt(r + 1) - 'a'] > 0) {
                freq[s.charAt(r + 1) - 'a']--;
                r++;
                count++;
            } else {
                if(existed[s.charAt(l) -'a']) { // l字符存在于p中，此时移动l需要将这个字符加回来
                    freq[s.charAt(l) - 'a']++;
                    l++;
                    count--;
                } else {    // l字符不存在于p中，所有包含l字符的字符串都不满足要求，所以直接将r置为当前l，l向后移动一位，相当于把l之前的字符全部丢掉，恢复到初始状态
                    r = l;
                    l++;
                }
            }

            if(count == p.length()) {
                rst.add(l);
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        String s = "abab", p = "ab";
        Solution solution = new Solution();
        for(int i : solution.findAnagrams(s, p)) {
            System.out.println(i);
        }
    }
}
