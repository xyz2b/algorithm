package leetcode.p2981;

import java.util.*;

public class Solution {
    /**
     * 因字符串仅含有小写字母，所以可以对每种字母单独处理。
     * 对于每一种字母，统计出每部分连续子串的长度，并储存在数组 chs 中。
     * 因题目要求出现至少三次，因此只要维护前三大的长度即可。每次往 chs 中添加元素时，可采用插入排序的方法使其有序。
     * 如果长度超过 3，则将末尾元素 pop 掉。
     * */
    public int maximumLength(String s) {
        int ret = -1;

        List<Integer>[] chs = new List[26];
        for(int i = 0; i < 26; i++) {
            chs[i] = new ArrayList<>();
        }

        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            count++;

            // 不满特殊字符串的条件或者是达到边界，在chs对应字母位置的List中添加特殊字符串的长度
            if(i + 1 == s.length() || s.charAt(i) != s.charAt(i + 1)) {
                int ch = s.charAt(i) - 'a';
                chs[ch].add(count);
                count = 0;

                // 采用插入排序，使特殊字符串长度的List有序（从大到小）
                // （每次新添加一个元素到List末尾时候，都执行该过程，因此在执行该过程之前，List是有序的，
                // 所以仅需要把新添加的元素放到指定有序的位置即可）
                for(int j = chs[ch].size() - 1; j > 0; j--) {
                    if(chs[ch].get(j) > chs[ch].get(j-1)) {
                        Collections.swap(chs[ch], j , j-1);
                    } else {
                        break;
                    }
                }

                // 仅保留List中前三大长度的元素即可
                if(chs[ch].size() > 3) {
                    chs[ch].remove(chs[ch].size() - 1);
                }
            }
        }

        // 计算结果
        /**
         * 三种情况
         * 1.最长的子串 chs[i].get(0) 可贡献出 3 个长为 chs[i].get(0)-2 的子串，需要满足 chs[i].get(0) > 2
         *      例如 aaaa，可以提供 三个 aa 的子串
         * 2.当 chs[i].get(0) 和 chs[i].get(1) 相等时，可贡献出 4 个长为 chs[i].get(0) - 1 的子串，不等时可由 chs[i].get(0)贡献出2个长为chs[i].get(1)的子串，再加上chs[i].get(1)本身一共3个，需要满足chs[i].get(0) > 1
         *      例如 aaaa 和 aaaa 可以提供 4 个 aaa 子串，第一个2个，第二个2个
         *      例如 aaaa 和 aaa 第一个可以提供2个aaa子串，第二个1个，总共三个
         * 3.可由 chs[0] 与 chs[1] 加上 chs[2] 本身贡献 3 个长为 chs[2] 的子串。
         *      例如 aaaa aaa aa 可以提供3个 aa 子串
         * */
        for(int i = 0; i < 26; i++) {
            if(chs[i].size() > 0 && chs[i].get(0) > 2) {
                ret = Math.max(ret, chs[i].get(0) - 2);
            }
            if(chs[i].size() > 1 && chs[i].get(0) > 1) {
                ret = Math.max(ret, Math.min(chs[i].get(0) - 1, chs[i].get(1)));
            }
            if(chs[i].size() > 2) {
                ret = Math.max(ret, chs[i].get(2));
            }
        }

        return ret;
    }
}
