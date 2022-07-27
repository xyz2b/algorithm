package leetcode.p592;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String fractionAddition(String expression) {

        List<Integer> numerators = new ArrayList<>();
        List<Integer> denominators = new ArrayList<>();

        // 切分字符串
        for(int i = 0; i < expression.length();) {
            if(expression.charAt(i) == '-' || expression.charAt(i) == '+') {    // 分子的符号（如果第一个分子是正数，则第一个字符就是数字，不在这个逻辑中）
                StringBuilder stringBuilder = new StringBuilder();

                if(expression.charAt(i) == '-') {   // 此时分子为负
                    stringBuilder.append('-');
                } else {    // 此时分子为正
                    stringBuilder.append('+');
                }

                i++;
                // 之后的是分子，直到读取到/
                while (Character.isDigit(expression.charAt(i))) {
                    stringBuilder.append(expression.charAt(i));
                    i++;
                }

                numerators.add(Integer.parseInt(stringBuilder.toString()));
            } else if (expression.charAt(i) == '/') {
                i++;
                StringBuilder stringBuilder = new StringBuilder();
                // 之后的是分母，直到读取到-或+
                // 这里需要处理边界条件，遍历到字符结尾的情况
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    stringBuilder.append(expression.charAt(i));
                    i++;
                }
                denominators.add(Integer.parseInt(stringBuilder.toString()));
            } else { // 开头字符是数字（第一个分子是正数）
                StringBuilder stringBuilder = new StringBuilder();
                // 之后的是分子，直到读取到/
                while (Character.isDigit(expression.charAt(i))) {
                    stringBuilder.append(expression.charAt(i));
                    i++;
                }
                numerators.add(Integer.parseInt(stringBuilder.toString()));
            }
        }

        // 分母求最小公倍数

        // 分子 乘以 最小公倍数/分母，然后再进行加减操作

        // 得出来的分数，求分子和分母的最大公约数，分子分母 除以 最大公约数

        for(int i : numerators) {
            System.out.println(i);
        }
        System.out.println();
        for(int i : denominators) {
            System.out.println(i);
        }

        return "";
    }

    public static void main(String[] args) {
        String expression = "-10/3-11/2";
        Solution solution = new Solution();
        solution.fractionAddition(expression);
    }
}
