package leetcode.p49;

import java.util.*;

public class Solution {
    // 查找表（灵活选择键值），查找表的键是字母出现的次数的拼接字符串，值为出现字母以及次数相同的字符串集合
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();

        // key为每个小写字母出现的次数拼接而成，value为出现字母以及次数相同的字符串集合，即结果集
        Map<String, List<String>> maps = new HashMap<>();

        int[] count = new int[26];
        // 统计每个字符串中的字符数量
        for(String str : strs) {
            // 统计字符串str中各个字母的数量
            Arrays.fill(count, 0);
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                count[c - 'a']++;
            }

            // 构造hash的key，key为每个小写字母出现的次数拼接而成
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }

            String key = sb.toString();
            if(!maps.containsKey(key)) maps.put(key, new ArrayList<>());
            maps.get(key).add(str);
        }

        return new ArrayList<>(maps.values());
    }
}
