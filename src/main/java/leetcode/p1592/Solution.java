package leetcode.p1592;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 找到有几个单词(wordNums)，几个空格(spaceNums)
    // spaceNums / (wordNums - 1)   商表示每个单词之间的间隔空格数，余数表示结尾多余的空格数（需要考虑特殊情况，比如只有一个单词，或者只有两个单词的情况）
    public String reorderSpaces(String text) {
        int wordNums = 0, spaceNums = 0;
        List<String> words = new ArrayList<>();

        for(int i = 0; i < text.length();) {
            if(text.charAt(i) == ' ') {
                spaceNums++;
                i++;
            } else {
                wordNums++;
                int wordStartIndex = i;
                while (i < text.length() && text.charAt(i) != ' ') {
                    i++;
                }
                int wordEndIndex = i;
                words.add(text.substring(wordStartIndex, wordEndIndex));
            }
        }

        int wordSpaceNums = 0;
        int endSpaceNums = 0;
        if (wordNums == 1) {    // 如果只有一个单词，那么所有空格都放到该单词的末尾
            endSpaceNums = spaceNums;
        } else if (wordNums == 2) { // 如果只有两个单词，那么所有空格都放到这两个单词中间
            wordSpaceNums = spaceNums;
        } else if(wordNums > 2) {   // 如果有超过两个单词，那么就需要计算单词间的空格数，以及多余的空格数放到末尾
            wordSpaceNums = spaceNums / (wordNums - 1);
            endSpaceNums = spaceNums % (wordNums - 1);
        }

        StringBuilder stringBuilder = new StringBuilder();
        int lastWordIndex = wordNums - 1;
        for(int i = 0; i < words.size(); i++) {
            stringBuilder.append(words.get(i));
            if(i == lastWordIndex) {
                break;
            }
            for(int j = 0; j < wordSpaceNums; j++) {
                stringBuilder.append(" ");
            }
        }
        for(int i = 0; i < endSpaceNums; i++) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String text = "hello   world";
        Solution solution = new Solution();
        System.out.println(solution.reorderSpaces(text));
    }
}
