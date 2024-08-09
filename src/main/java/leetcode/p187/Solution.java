package leetcode.p187;

import java.util.*;

public class Solution {
    // 暴力
    // hash表，遍历所有10长度的字符串，放到hash表，如果一个字符串出现了超过1次
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> retSet = new HashSet<>();
        Set<String> set = new HashSet<>();
        for(int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i+10);
            if(set.contains(sub)) {
                retSet.add(sub);
            } else {
                set.add(sub);
            }
        }

        return new ArrayList<>(retSet);
    }
}
