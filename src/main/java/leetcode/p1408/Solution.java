package leetcode.p1408;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> stringMatching(String[] words) {

        // 按照长度排序，如果是一个字符是另一个字符串的子串，那么该字符的长度一定小于等于另一个字符串
        Arrays.sort(words, (x, y) -> x.length() - y.length());

        List<String> rst = new ArrayList<>();
        // 从头往后遍历，如果遇到当前遍历的字符串是后面某个字符串的子串，那就加入结果集并遍历下一个字符串
        for(int i = 0; i < words.length - 1; i++) {
            for(int j = i + 1; j < words.length; j++) {
                if (words[j].contains(words[i])) {
                    rst.add(words[i]);
                    break;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        String[] data = {"leetcoder","leetcode","od","hamlet","am"};
        Solution solution = new Solution();
        solution.stringMatching(data);
    }
}
