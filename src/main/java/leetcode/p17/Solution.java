package leetcode.p17;

import java.util.*;

public class Solution {
    // 树型结构，递归
    private Map<Character, List<String>> maps = new HashMap<>();
    private int length;
    public List<String> letterCombinations(String digits) {
        maps.put('2', new ArrayList<>(Arrays.asList("a", "b", "c")));
        maps.put('3', new ArrayList<>(Arrays.asList("d", "e", "f")));
        maps.put('4', new ArrayList<>(Arrays.asList("g", "h", "i")));
        maps.put('5', new ArrayList<>(Arrays.asList("j", "k", "l")));
        maps.put('6', new ArrayList<>(Arrays.asList("m", "n", "o")));
        maps.put('7', new ArrayList<>(Arrays.asList("p", "q", "r", "s")));
        maps.put('8', new ArrayList<>(Arrays.asList("t", "u", "v")));
        maps.put('9', new ArrayList<>(Arrays.asList("w", "x", "y", "z")));
        length = digits.length();

        return letter(digits, 0);
    }

    // 返回从digits的index索引开始到结尾的数字对应的字母组合的列表
    private List<String> letter(String digits, int index) {
        List<String> rst = new ArrayList<>();
        if(index >= length) return rst;

        for(String s : maps.get(digits.charAt(index))) {
            List<String> list = letter(digits, index + 1);
            if(list.size() > 0) {
                for(String s1 : list) {
                    rst.add(s + s1);
                }
            } else {
                rst.add(s);
            }

        }
        return rst;
    }

    public static void main(String[] args) {
        String digits = "2";
        Solution solution = new Solution();
        for(String s : solution.letterCombinations(digits)) {
            System.out.println(s);
        }
    }
}
