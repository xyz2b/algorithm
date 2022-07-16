package leetcode.p745;

import java.util.HashMap;

public class WordFilter {
    // 用字典存储所有可能的前缀和后缀的组合
    // 字典的key是可能的组合，字典的value是在words数组中，能够构成这个组合的最大序号的字符串
    private HashMap<String, Integer> d;

    public WordFilter(String[] words) {
        d = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            int length = word.length();
            // 前缀的长度
            for(int prefixLength = 1; prefixLength <= length; prefixLength++) {
                // 后缀的长度
                for (int suffixLength = 1; suffixLength <= length; suffixLength++) {
                    // word字符串 前缀长度为prefixLength 和 后缀长度为suffixLength 的组合
                    d.put(word.substring(0, prefixLength) + "#" + word.substring(length - suffixLength), i);
                }
            }
        }
    }

    public int f(String pref, String suff) {
        return d.getOrDefault(pref + "#" + suff, -1);
    }

    public static void main(String[] args) {
        // 对于字符串"abc"，"abc"既是它的前缀也是它的后缀
        // 对于字符串"c"，"c"既是它的前缀也是它的后缀
        WordFilter wordFilter = new WordFilter(new String[] {"c", "i"});
        System.out.println(wordFilter.f("c", "c"));
    }
}
