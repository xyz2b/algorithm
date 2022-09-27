package leetcode.interview0102;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        for(int i = 0; i < s2.length(); i++) {
            if(map.get(s2.charAt(i)) != null) {
                int count = map.get(s2.charAt(i)) - 1;
                if(count < 0) {
                    return false;
                } else {
                    map.put(s2.charAt(i), count);
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
