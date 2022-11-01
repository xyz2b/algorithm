package leetcode.p1662;

public class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        // 字符串数组1index
        int sIndex1 = 0;
        // 字符串数组1中的字符串的字符index
        int cIndex1 = 0;

        // 字符串数组2index
        int sIndex2 = 0;
        // 字符串数组2中的字符串的字符index
        int cIndex2 = 0;

        while (sIndex1 < word1.length && sIndex2 < word2.length) {
            // 如果有对应的字符不相同，就返回false
            if(word1[sIndex1].charAt(cIndex1) != word2[sIndex2].charAt(cIndex2)) {
                return false;
            }

            // 递增字符索引
            cIndex1++;
            cIndex2++;

            // 如果字符索引1，大于等于当前遍历的字符串的长度，说明当前字符串遍历完了，继续从下一个字符串第一个字符开始遍历
            if(cIndex1 >= word1[sIndex1].length()) {
                sIndex1++;
                cIndex1 = 0;
            }
            if(cIndex2 >= word2[sIndex2].length()) {
                sIndex2++;
                cIndex2 = 0;
            }
        }

        // 如果两个字符串数组都遍历完了，其中的字符全部相同，就返回true
        return sIndex1 == word1.length && sIndex2 == word2.length;
    }

    public static void main(String[] args) {
        String[] word1 = new String[] {"abc", "d", "defg"};
        String[] word2 = new String[] {"abcddefg"};
        Solution solution = new Solution();
        System.out.println(solution.arrayStringsAreEqual(word1, word2));
    }
}
