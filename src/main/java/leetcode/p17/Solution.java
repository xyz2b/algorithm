package leetcode.p17;

import java.util.*;

public class Solution {
    // 暴力解法，遍历所有可能
    // 树形结构，递归
    //                  2
    //      a           b           c
    //      3           3           3
    //  d   e   f   d   e   f    d  e  f
    public List<String> letterCombinations(String digits) {
        return letter(digits, 0);
    }

    // 自底向上的递归
    // 返回digits[index, digits.length()-1]翻译所得来的字母字符串列表
    private List<String> letter(String digits, int index) {
        List<String> rst = new ArrayList<>();
        if(index == digits.length()) return rst;

        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for(int i = 0; i < letters.length(); i++) {
            List<String> list = letter(digits, index + 1);
            if(list.size() > 0) {
                for(String s1 : list) {
                    rst.add(letters.charAt(i) + s1);
                }
            } else {
                rst.add(String.valueOf(letters.charAt(i)));
            }
        }

        return rst;
    }

    private List<String> rst = new ArrayList<>();
    private static final String[] letterMap = new String[] {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };
    public List<String> letterCombinations2(String digits) {
        if(digits.equals("")) return rst;
        letter2(digits, 0, "");
        return rst;
    }

    // 自顶向下的递归
    // s中保存了digits[0,index-1]翻译所得来的字母字符串
    // 寻找和digits[index]匹配的字母，获得digits[0,index]翻译得到的解
    private void letter2(String digits, int index, String s) {
        System.out.println(index + " : " + s);
        if(index == digits.length()) {
            rst.add(s);
            System.out.println("get " + s + ", return");
            return;
        }

        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for(int i = 0; i < letters.length(); i++) {
            System.out.println("digits[" + index + "] = " + c + ", use " +  letters.charAt(i));
            letter2(digits, index + 1, s + letters.charAt(i));
        }
        System.out.println("digits[" + index + "] = " + c + " complete, return");
        return;
    }

    public static void main(String[] args) {
        String digits = "234";
        Solution solution = new Solution();
        for(String s : solution.letterCombinations2(digits)) {
            System.out.println(s);
        }
    }
}
