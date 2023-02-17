package leetcode.p242;

public class Solution {
    // 如果两个字符串中的字符相同，频次也相同，就满足题意
    public boolean isAnagram(String s, String t) {
        int[] sFreq = new int[26];
        for(int i = 0; i < s.length(); i++) {
            sFreq[s.charAt(i) - 'a']++;
        }

        int[] tFreq = new int[26];
        for(int i = 0; i < t.length(); i++) {
            tFreq[t.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++) {
            if(sFreq[i] != tFreq[i]) {
                return false;
            }
        }

        return true;
    }
}
