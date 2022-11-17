package leetcode.p792;

import java.util.ArrayDeque;
import java.util.Queue;

// 多指针
/**
 * 同样我们还可以在朴素方法的基础上进行优化，因为朴素方法中是每一个单词分别和字符串 s 进行匹配，这样对于每一次匹配都需要从头开始遍历字符串 s，这增加了额外的时间开销。
 * 所以我们考虑将字符串数组 words 中的全部字符串和字符串 s 同时进行匹配——同样对于每一个需要匹配的字符串我们用一个指针来指向它需要匹配的字符，
 * 那么在遍历字符串 s 的过程中，对于当前遍历到的字符如果有可以匹配的字符串，那么将对应的字符串指针往后移动一单位即可。
 * 那么当字符串 s 遍历结束时，字符串数组中全部字符串的匹配情况也就全部知道了。
 * */
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        // 对应到26个英文小写字母的队列数组，每个队列对应一个英文小写字母
        // 队列的值是一个只有两个元素的数组，数组第一个值表示该小写字母属于words中第几个字符串，第二个值表示该小写字母在该字符串中的索引位置
        Queue<int[]>[] p = new Queue[26];
        for (int i = 0; i < 26; ++i) {
            p[i] = new ArrayDeque<int[]>();
        }
        // 将words数组中每个字符串的第一个字符，存入到对应的字母队列中
        for (int i = 0; i < words.length; ++i) {
            p[words[i].charAt(0) - 'a'].offer(new int[]{i, 0});
        }
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int len = p[c - 'a'].size();
            // 如果当前遍历的字母对应的队列中有元素，表示匹配到了具有当前s中字符的words中的字符串，可能有多个，一一处理
            while (len > 0) {
                // t[0]表示该小写字母属于words中第几个字符串
                // t[1]表示该小写字母在该字符串中的索引位置
                int[] t = p[c - 'a'].poll();
                // 表示遍历到了words中t[0]索引位置的字符串的末尾，表示匹配到了一个子串，直接结果+1即可
                if (t[1] == words[t[0]].length() - 1) {
                    ++res;
                } else {
                    // 如果不是末尾，因为当前所以位置的字符已经被匹配到了，就自增该字符串的索引位置，指向该字符串的下一个字符
                    ++t[1];
                    // 将该字符串下一个索引位置的字符放入对应的队列
                    p[words[t[0]].charAt(t[1]) - 'a'].offer(t);
                }
                // 处理完队列中的一个字符串需要将待处理的长度-1
                --len;
            }
        }
        return res;
    }
}