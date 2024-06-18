package leetcode.p522;

import java.util.Arrays;

public class Solution {
    // 一个字符串的子序列不是其他字符串的子序列，那么它本身也不是其他字符串的子序列，因为将其子序列加上其他字符也不可能是其他字符串的子序列
    // 所以该题目可以直接判断哪个字符串不是其他字符串的子序列，然后找到最长的那个（遍历）
    public int findLUSlength(String[] strs) {
        int rst = -1;
        for(int i = 0; i < strs.length; i++) {
            int flag = 1;
            for(int j = 0; j < strs.length; j++) {
                if (i != j) {
                    if (isSubStr(strs[i], strs[j])) {   // 判断strs[i]是不是strs[j]的子序列，如果是，直接退出内层循环，将flag置为0，标识strs[i]不是特殊序列，因为strs[i]是strs数组中某一个字符串的子序列
                        flag = 0;
                        break;
                    }
                }
            }
            if (flag == 1) {    // 如果内层循环结束，flag仍为1，标识当前外层循环的strs[i]不是strs数组中的任何字符串的子序列，所以其是特殊序列
                rst = Math.max(rst, strs[i].length());
            }
        }

        return rst;
    }


    // 判断s1是不是s2的子序列，顺序很重要
    private boolean isSubStr(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();

        int ptS1 = 0;
        int ptS2 = 0;
        while (ptS1 < s1Length && ptS2 < s2Length) {
            if (s1.charAt(ptS1) == s2.charAt(ptS2)) {   // 如果s1中有字符和s2中相同，就增加s1的指针
                ptS1++;
            }
            ptS2++; // s2的指针每轮循环都增加
        }

        return ptS1 == s1Length;    // 如果s1的指针遍历到s1的末尾了，说明s1中的字符在s2中都存在，且顺序一致
    }

    public String toString(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(data[i]).append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"aba","cdc","eae"};
        String[] strs2 = {"aaa","aaa","aa"};
        System.out.println(solution.findLUSlength(strs2));
    }
}
