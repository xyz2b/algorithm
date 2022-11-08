package leetcode.p1684;

public class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        // 统计allowed中的字符
        int[] allowedChars = new int[26];
        for(int i = 0; i < allowed.length(); i++) {
            allowedChars[allowed.charAt(i) - 'a'] = 1;
        }

        int rst = 0;
        // 统计words中每个字符串的字符
        for(int i = 0; i < words.length; i++) {
            String s = words[i];
            int j = 0;
            for(; j < s.length(); j++) {
                char c = s.charAt(j);
                if(allowedChars[c - 'a'] == 0) {    // 有allowed中不存在的字符
                    break;
                }
            }
            if(j == s.length()) {   // words[i]字符串中的字符全部都存在与allowed字符串中
                rst++;
            }
        }
        return rst;
    }
}
