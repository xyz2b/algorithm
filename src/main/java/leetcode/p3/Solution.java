package leetcode.p3;

import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    // 双指针
    public int lengthOfLongestSubstring(String s) {
        if(s.length() ==  0) {
            return 0;
        }
        boolean[] existed = new boolean[256];
        int rst = 0;
        for(int i = 0; i < s.length(); i++) {
            int max = 0;
            Arrays.fill(existed, false);
            for(int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if(existed[c]) {
                    break;
                } else {
                    max++;
                    existed[c] = true;
                }
            }
            rst = Math.max(rst, max);
        }

        return rst;
    }

    // 滑动窗口
    public int lengthOfLongestSubstring2(String s) {
        if(s.length() ==  0) {
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int rst = 0;
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        for(int l = 0, r = -1; l < s.length(); l++) {
            // 左指针向右移动一格，移除原来左指针指向的字符
            if(l != 0) {
                set.remove(s.charAt(l - 1));
            }

            // 不断移动右指针，同时记录右指针的字符，直到有重复字符出现
            // 右指针会停到最后一个不重复的字符处或者字符串最后一个字符
            // 右指针需要保留，在下次遍历下一个子串时候继续往后移动，直到对于下一个子串来说有重复字符出现
            // 对于下一个子串来说，[l+1, r]之间的字符是上一个子串去掉l位置的字符得到的，因为[l,r]之间是没有重复字符的，所以[l+1,r]之间肯定是没有重复字符的
            // 所以对于下一个子串，只需要继续从r+1往后遍历寻找是否存在重复字符即可
            while (r + 1 < s.length() && !set.contains(s.charAt(r + 1))) {
                set.add(s.charAt(r + 1));
                r++;
            }

            // 不重复的字符长度就是 r - l + 1
            rst = Math.max(rst, r - l + 1);
        }

        return rst;
    }

    // 滑动窗口
    public int lengthOfLongestSubstring3(String s) {
        if(s.length() ==  0) {
            return 0;
        }
        int[] freq = new int[256];
        int l = 0, r = -1; // 滑动窗口为s[l, r]
        int rst = 0;

        while (l < s.length()) {
            if(r + 1 < s.length() && freq[s.charAt(r+1)] == 0) {
                r++;
                freq[s.charAt(r)]++;
            } else {
                freq[s.charAt(l)]--;
                l++;
            }

            rst = Math.max(rst, r - l + 1);
        }

        return rst;
    }
}
