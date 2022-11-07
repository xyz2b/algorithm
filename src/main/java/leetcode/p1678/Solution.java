package leetcode.p1678;

import java.util.Stack;

// 括号匹配问题，栈
public class Solution {
    public String interpret(String command) {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            if(c == 'G') {
                stringBuilder.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == 'a' || c == 'l') {
                stack.push(c);
            } else { // (c == ')')
                char c1 = stack.peek();
                if(c1 == '(') {
                    stringBuilder.append('o');
                } else {
                    stringBuilder.append("al");
                    while (stack.peek() != '(') {   // 弹出所有al
                        stack.pop();
                    }
                }
                // 弹出'('
                stack.pop();
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String command = "(al)G(al)()()G";
        Solution solution = new Solution();
        System.out.println(solution.interpret(command));
    }
}
