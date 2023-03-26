package leetcode.p150;

import java.util.Stack;

public class Solution {
    // 栈
    // 栈顶元素反映了在嵌套的层次关系中，最近的需要匹配的元素
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for(int i = 0; i < tokens.length; i++) {
            String s = tokens[i];

            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());

                int rst = 0;
                if(s.equals("+")) {
                    rst = num1 + num2;
                } else if (s.equals("-")) {
                    rst = num1 - num2;
                } else if (s.equals("*")) {
                    rst = num1 * num2;
                } else {    // "/"
                    rst = num1 / num2;
                }
                stack.push(String.valueOf(rst));
            }  else {
                stack.push(s);
            }
        }

        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.evalRPN(new String[] {"4","13","5","/","+"}));
    }
}
