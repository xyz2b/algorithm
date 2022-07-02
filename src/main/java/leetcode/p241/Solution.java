package leetcode.p241;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    private static final int ADDITION = -1;
    private static final int SUBTRACTION = -2;
    private static final int MULTIPLICATION = -3;

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ops = new ArrayList<>();

        for(int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);

            // 数位，个位为0，十位为1
            int j = 0;
            // 解析出来的数值
            int v = 0;
            while (Character.isDigit(c)) {
                v += Character.getNumericValue(c) * (int) Math.pow(10, j);
                if (i-1 >= 0) {
                    c = expression.charAt(i - 1);
                } else {
                    break;
                }
                i--;
                j++;
            }
            ops.add(v);

            if (i <= 0) {   // 最后一个总是数字，不会再有运算符了
                break;
            }

            // 此时c为四则运算符
            if (c == '+') {
                ops.add(ADDITION);
            } else if (c == '-') {
                ops.add(SUBTRACTION);
            } else {
                ops.add(MULTIPLICATION);
            }

        }

        return ops;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String expression = "22*33-444*55555";
        List<Integer> rst = solution.diffWaysToCompute(expression);
        for(Integer i : rst) {
            System.out.println(i);
        }
    }
}
