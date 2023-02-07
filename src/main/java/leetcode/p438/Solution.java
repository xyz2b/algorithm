package leetcode.p438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] freq = new int[26];
        for(int i = 0; i < p.length(); i++) {
            freq[p.charAt(i) - 'a']++;
        }
        int[] temp = Arrays.copyOf(freq, freq.length);

        List<Integer> rst = new ArrayList<>();

        int l = 0, r = -1;
        int count = 0;
        while (l < s.length()) {
            if(r + 1 < s.length() && temp[s.charAt(r + 1) - 'a'] > 0) {
                temp[s.charAt(r + 1) - 'a']--;
                r++;
                count++;
            } else {
                temp = Arrays.copyOf(freq, freq.length);
                l++;
            }

            if(count == p.length()) {
                count = 0;
                rst.add(l);
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        Solution solution = new Solution();
        for(int i : solution.findAnagrams(s, p)) {
            System.out.println(i);
        }
    }
}
