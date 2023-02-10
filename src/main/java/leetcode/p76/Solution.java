package leetcode.p76;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {
    // 统计t中的字符频率
    Map<Character, Integer> freq = new HashMap<>();
    // 统计s[l, r]窗口中出现的t中的字符频率
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {
        for(int i = 0; i < t.length(); i++) {
            freq.put(t.charAt(i), freq.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 图解: https://assets.leetcode-cn.com/solution-static/76/76_fig1.gif
        int l = 0, r = -1;  // 滑动窗口s[l, r]。窗口中不包含t所有元素，r右移；窗口中包含t所有元素，l右移
        int ansL = -1, ansR = -1, len = Integer.MAX_VALUE;
        while (l < s.length()) {
            if(isContainAll()) {    // 窗口中包含t所有元素，l右移
                if(r - l + 1 < len) {   // 满足条件，记录结果
                    len = r - l + 1;
                    ansL = l;
                    ansR = r + 1;
                }

                // 窗口中包含t所有元素，l右移
                if(freq.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                l++;
            } else {    // 窗口中不包含t所有元素，r右移
                r++;
                if(r < s.length() && freq.containsKey(s.charAt(r))) {
                    cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
                } else if(r >= s.length()) {    // r越界了，但是l还没越界，只能l右移，同时此时窗口内不包含t中的所有字符，此时l再往右移，包含的字符数只会减少，所以直接退出循环即可
                    break;
                }
            }
        }

        return ansL != -1 ? s.substring(ansL, ansR) : "";
    }

    private boolean isContainAll() {
        Iterator<Map.Entry<Character, Integer>> iter = freq.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Character, Integer> entry = iter.next();
            Character key = entry.getKey();
            Integer val = entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "a", t = "a";
        Solution solution = new Solution();
        System.out.println(solution.minWindow(s, t));
    }
}
