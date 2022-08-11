package leetcode.p640;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String solveEquation(String equation) {
        // 存储变量的系数
        List<Integer> coefficients = new ArrayList<>();
        // 存储整数值
        List<Integer> integers = new ArrayList<>();

        int index = 0;
        // 遇到等号后，flag为1，之后为分解方程右边，变量系数取负加入，整数原符号加入；flag为0，为分解方程左边，变量系数原符号加入，整数取负加入
        int flag = 0;
        while (index < equation.length()){
            // 符号
            if(equation.charAt(index) == '=') {
                flag = 1;
                index++;
                continue;
            }

            // 变量系数的符号，等号左边，就取原符号即可；等号右边，需要对其取反
            int coefficientSign = 1;
            // 整数的符号，等号左边，需要对齐取反；等号右边，就取原符号即可
            int integerSign = -1;
            if(flag == 1) {
                coefficientSign = -1;
                integerSign = 1;
            }

            if(equation.charAt(index) == 'x') { // 开头就是x变量的情况
                coefficients.add(coefficientSign);
                index++;
                continue;
            }

            if(equation.charAt(index) == '-' || equation.charAt(index) == '+') {    // -、+
                coefficientSign = equation.charAt(index) == '-' ? -coefficientSign : coefficientSign;
                integerSign = equation.charAt(index) == '-' ? -integerSign : integerSign;
                index++;

                if (equation.charAt(index) == 'x') {   // -x或+x的情况
                    coefficients.add(coefficientSign);
                    index++;
                    continue;
                }
            }

            // 此时后面遇到的一定是数字
            // 解析数字
            int num = 0;
            // 循环解析数字，知道遇到不是数字的字符
            while (index < equation.length() && Character.isDigit(equation.charAt(index))) {
                num = num * 10 + equation.charAt(index) - '0';
                index++;
            }

            // 解析到不是数字的字符如果是x，就表明是变量系数
            if(index < equation.length() && equation.charAt(index) == 'x') {
                coefficients.add(coefficientSign * num);
                index++;
            } else {    // 解析到不是数字的字符如果是-、+、=，就表明是整数
                integers.add(integerSign * num);
            }
        }

        int coefficientsSum = 0;
        for(int i : coefficients) {
            coefficientsSum += i;
        }

        int integersSum = 0;
        for(int i : integers) {
            integersSum += i;
        }

        if(coefficientsSum == 0 && integersSum == 0) {
            return "Infinite solutions";
        } else if (coefficientsSum == 0 && integersSum != 0) {
            return "No solution";
        } else {
            return "x=" + integersSum / coefficientsSum;
        }
    }

    public static void main(String[] args) {
        String equation = "x=x+2";
        Solution solution = new Solution();
        System.out.println(solution.solveEquation(equation));
    }
}
