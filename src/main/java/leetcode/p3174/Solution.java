package leetcode.p3174;

import java.util.Stack;

public class Solution {
    public String clearDigits(String s) {
        // 栈
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if(!stack.isEmpty()) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for(Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    public String clearDigits2(String s) {
        // 模拟栈
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
