package leetcode.p890;

import java.util.*;

/**
 * 构造从字母到字母的 双映射，即 word 的每个字母需要映射到 pattern 的对应字母，并且 pattern 的每个字母也需要映射到 word 的对应字母。
 *
 * 我们可以编写一个函数 match(word,pattern)，仅当 word 中相同字母映射到 pattern 中的相同字母时返回 true。我们可以在遍历这两个字符串的同时，
 * 用一个数组记录 word 的每个字母 xx 需要映射到 pattern 的哪个字母上，如果 xx 已有映射，则需要检查对应字母是否相同。
 *
 * 如果 match(word,pattern) 和 match(pattern,word) 均为 true，则表示 word 与 pattern 匹配。
 * （word 中的同一字母必须映射到 pattern 中的同一字母上；pattern 中的同一字母也必须映射到 word 中的同一字母上）
 */
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int length = pattern.length();

        // 待匹配字符串中的 字符 到 匹配模式字符串中的 字符的映射
        int mp[] = new int[26];
        Arrays.fill(mp, -1);
        // 匹配模式字符串中的 字符 到 待匹配字符串中的 字符的映射
        int pm[] = new int[26];
        Arrays.fill(pm, -1);

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() != length) {
                continue;
            }

            int flag = 1;
            for (int j = 0; j < length; j++) {
                int m = words[i].charAt(j) - 'a';
                int p = pattern.charAt(j) - 'a';

                // 有 待匹配字符串中的字符 到 匹配模式字符串中的字符 的映射，即当前待匹配字符之前出现过
                if (mp[m] != -1) {
                    if (mp[m] != p) {   // 如果之前存储的当前待匹配字符对应的匹配模式中的字符，和当前匹配模式中的字符不一样，就该字符串代表不符合要求，直接退出当前字符串的匹配循环
                        /**
                         * p: 0 1 2 2
                         * m: 1 1 3 3
                         * 当匹配 m=1时，p=1，但是之前一位已经存在m=1,p=0的了，所以这里直接判断不符合，退出循环
                         * */
                        flag = 0;   // 标识该字符串是否符合匹配要求，0表示不符合匹配要求
                        break;
                    }
                } else {
                    if (pm[p] != -1) { // 有 匹配模式字符串中的字符 到 待匹配字符串中的字符 的映射，即当前匹配模式中的字符之前出现过
                        if (pm[p] != m) {   // 如果之前存储的当前匹配模式中的字符对应的待匹配字符，和当前待匹配字符不一样，就该字符串代表不符合要求，直接退出当前字符串的匹配循环
                            /**
                             * 举例
                             * p: 0 0 2 2
                             * m: 2 3 1 1
                             * 当匹配 p=0时，m=3，但是之前一位已经存在p=0,m=2的了，所以这里直接判断不符合，退出循环
                             * */
                            flag = 0;
                            break;
                        }
                    }
                    // 既没有m->p的映射，也没有p->m的映射，就是之前都没遇到过的字符，新增即可
                    pm[p] = m;
                    mp[m] = p;
                }
            }

            Arrays.fill(mp, -1);
            Arrays.fill(pm, -1);

            if (flag == 1) {
                result.add(words[i]);
            }
        }

        return result;
    }

    public List<String> findAndReplacePattern2(String[] words, String pattern) {
        List<String> ans = new ArrayList<String>();
        for (String word : words) {
            if (match(word, pattern) && match(pattern, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    public boolean match(String word, String pattern) {
        int map[] = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0; i < word.length(); ++i) {
            int x = word.charAt(i) - 'a', y = pattern.charAt(i) - 'a';
            if (map[x] == -1) {
                map[x] = y;
            } else if (map[x] != y) { // word 中的同一字母必须映射到 pattern 中的同一字母上
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String words[] = {"abc","deq","mee","aqq","dkd","ccc"};
        String p = "abb";

        List<String> r = new Solution().findAndReplacePattern(words, p);
        System.out.printf(r.toString());
    }
}