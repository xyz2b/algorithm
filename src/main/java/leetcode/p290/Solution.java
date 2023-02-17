package leetcode.p290;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 双向映射
    public boolean wordPattern(String pattern, String s) {
        String[] sSingle = s.split(" ");
        if(sSingle.length != pattern.length()) {
            return false;
        }

        String[] pTs = new String[26];
        Map<String, Integer> sTp = new HashMap<>();
        for(int i = 0; i < pattern.length(); i++) {
            int c = pattern.charAt(i) - 'a';
            String s1 = sSingle[i];

            if(pTs[c] != null) {
                if(!pTs[c].equals(s1)) {
                    return false;
                }

                if(sTp.get(s1) == null) {
                    sTp.put(s1, c);
                }
            } else {
                if(sTp.get(s1) == null) {
                    sTp.put(s1, c);
                    pTs[c] = s1;
                    continue;
                }
                if(sTp.get(s1) != c) {
                    return false;
                }
            }
        }
        return true;
    }
}
