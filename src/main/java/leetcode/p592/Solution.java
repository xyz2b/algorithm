package leetcode.p592;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String fractionAddition1(String expression) {

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

    // 对于两个分数 x1/y1和x2/y2，他们相加的结果是 (x1*y2+x2*y1) / (y1*y2)
    public String fractionAddition(String expression) {
        long denominator = 0, numerator = 1; // 累加之后的分子和分母，可以看成x1和y1
        int index = 0, n = expression.length();

        while (index < n) {
            int denominator1 = 0, sign = 1;
            // 读取分子，可以看成x2
            if (expression.charAt(index) == '-' || expression.charAt(index) == '+') {   // 读取分子的符号
                sign = expression.charAt(index) == '-' ? -1 : 1;
                index++;
            }
            while (index < n && Character.isDigit(expression.charAt(index))) {  // 读取分子的数值
                denominator1 = denominator1 * 10 + expression.charAt(index) - '0';
                index++;
            }
            denominator1 = sign * denominator1;
            index++;

            // 读取分母，可以看成y2
            long numerator1 = 0;
            while (index < n && Character.isDigit(expression.charAt(index))) {  // 读取分母的数值，分母都是正的
                numerator1 = numerator1 * 10 + expression.charAt(index) - '0';
                index++;
            }

            // 相加之后的分子，即 (x1*y2+x2*y1)
            denominator = denominator * numerator1 + denominator1 * numerator;
            // 相加之后的分母，即 (y1*y2)
            numerator *= numerator1;
        }

        if(denominator == 0) {
            return "0/1";
        }
        long g = gcd(Math.abs(denominator), numerator); // 获取最大公约数
        return Long.toString(denominator / g) + "/" + Long.toString(numerator / g);
    }

    // 求最大公约数
    public long gcd(long a, long b) {
        long remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

    public static void main(String[] args) {
        String expression = "-10/3-11/2";
        Solution solution = new Solution();
        solution.fractionAddition1(expression);
    }
}
