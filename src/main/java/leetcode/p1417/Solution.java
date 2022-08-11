package leetcode.p1417;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 数字的数量 和 字母的数量差值最大只能为1（即数量差值为0或为1），才能满足要求
    public String reformat(String s) {

        List<Character> numbers = new ArrayList<>();
        List<Character> letters = new ArrayList<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                numbers.add(c);
            } else {
                letters.add(c);
            }
        }

        if(Math.abs(numbers.size() - letters.size()) > 1) {
            return "";
        }

        // numbers.size() >= letters.size()时，数字在偶数位置；numbers.size() < letters.size()时，字母在偶数位置
        List<Character> events = numbers.size() >= letters.size() ? numbers : letters;
        List<Character> odds = numbers.size() >= letters.size() ? letters : numbers;

        StringBuilder stringBuilder = new StringBuilder();
        int eventsIndex = 0, oddsIndex = 0;
        for(int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) { // 偶数位置
                stringBuilder.append(events.get(eventsIndex));
                eventsIndex++;
            } else {    // 奇数位置
                stringBuilder.append(odds.get(oddsIndex));
                oddsIndex++;
            }
        }

        return stringBuilder.toString();
    }

    // 双指针，将字符串变成字符数组，进行交换操作
    public String reformat2(String s) {
        int sumDigit = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sumDigit++;
            }
        }
        int sumAlpha = s.length() - sumDigit;
        if (Math.abs(sumDigit - sumAlpha) > 1) {
            return "";
        }
        // 如果数字的数量大于字母的数量，那么偶数位置放的就是数字
        boolean flag = sumDigit > sumAlpha;
        char[] arr = s.toCharArray();
        for (int i = 0, j = 1; i < s.length(); i += 2) {
            // 如果当前遍历的偶数位置不是数字而是字母，那么就在后面的奇数位置中找到第一个为数字的字符（奇数位置应该为字母），然后和当前遍历的偶数位置的字母进行交换
            if (Character.isDigit(arr[i]) != flag) {
                while (Character.isDigit(arr[j]) != flag) {
                    j += 2;
                }
                swap(arr, i, j);
            }
        }
        return new String(arr);
    }

    public void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }

    public static void main(String[] args) {
        String s = "ab123";
        Solution solution = new Solution();
        System.out.println(solution.reformat(s));
    }
}
