package leetcode.p1668;

public class Solution {
    public int maxRepeating(String sequence, String word) {
        // 先找到word在sequence中出现的第一个索引位置，再往后遍历，看会不会重复匹配word，将重复匹配的次数记录下来，找最大值
        // 然后继续找到下一个出现的索引位置(应该从上一个匹配子串的初始索引位置往后一个位置继续搜索word，因为如果从上一个匹配子串最后索引位置往后搜索，
        //  可能会漏掉上一个匹配字符串中后面一部分字符和其后面字符能够构成word字符串)，
        //  如：aaabaaaabaaabaaaabaaaabaaaabaaaaba中找aaaba， 上一次找到的起始索引位置为5，结束位置为9，如果从10开始再次搜索word，就会漏掉，从9位置开始的word字符串
        //      再往后遍历，看会不会重复匹配word，将重复匹配的次数记录下来，找最大值
        // 直到剩余的字符串中找不到和word相同的子串，退出

        int matchIndex = sequence.indexOf(word);
        int rst = 0;
        while (matchIndex != -1) {
            int count = 1;
            // index是sequence字符串中的索引
            int index = matchIndex + word.length();
            for(; index + word.length() - 1 <= sequence.length() - 1;) {
                // j是word字符串中的索引
                int j = 0;
                for(; j < word.length(); j++) {
                    if(word.charAt(j) != sequence.charAt(index)) {  // 匹配不上word
                        break;
                    }
                    index++;
                }
                if(j == word.length()) {    // sequence后续的字符匹配上了word，结果集+1，继续往sequence后面看是否还能匹配上word
                    count++;
                } else { // sequence后续的字符匹配不上word，直接从当前匹配到的初始索引即matchIndex，往后一个索引继续搜索
                    rst = Math.max(rst, count);
                    matchIndex = sequence.indexOf(word, matchIndex + 1);
                    break;
                }
            }

            if(index + word.length() - 1 > sequence.length() - 1) {
                rst = Math.max(rst, count);
                break;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        String sequence = "aaabaaaabaaabaaaabaaaabaaaabaaaaba";
        String word = "aaaba";
        Solution solution = new Solution();
        System.out.println(solution.maxRepeating(sequence, word));
    }
}
