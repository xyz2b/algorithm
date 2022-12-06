package leetcode.p1805;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numDifferentIntegers(String word) {
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        // 发现的数字为0
        boolean zero = true;
        // 发现了为'0'的数字字符
        boolean findZero = false;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c == '0') {
                findZero = true;
                // 发现的数字不为0，表示此时的'0'字符在数字的中间，而不是开头
                if(!zero) {
                    sb.append(c);
                }
            } else if(c >= '1' && c <= '9') {
                sb.append(c);
                zero = false;
                findZero = false;
            } else {
                // 出现了'0'的数字字符，并且包含'0'数字字符的这个数字是0，将0加入set
                if(findZero && zero) {
                    set.add("0");
                }
                if(sb.length() > 0) {
                    set.add(sb.toString());
                    sb.delete(0, sb.length());
                }
                zero = true;
                findZero = false;
            }
        }

        // 结尾数字是0的情况
        if(findZero && zero) {
            set.add("0");
        }
        // 结尾是非0数字的情况
        if(sb.length() > 0) {
            set.add(sb.toString());
        }
        return set.size();
    }

    /**
     * 对于每个字符串中的整数部分，使用指针 p1 指向整数部分的第一个字符，指针 p2 指向整数部分最后一个字符的下一个位置。
     * 为了去除前导零，如果 p2−p1>1 且 word[p1]=‘0’，我们将 p1 前移一位，即 p1=p1+1。
     * 将区间 [p1,p2)对应的字符串插入到哈希集合中，最终字符串中不同整数的数目等于哈希集合的元素数目。
     * */
    public int numDifferentIntegers2(String word) {
        Set<String> set = new HashSet<String>();
        int n = word.length(), p1 = 0, p2;
        while (true) {
            // 直到找到数字字符
            while (p1 < n && !Character.isDigit(word.charAt(p1))) {
                p1++;
            }
            // 超出字符串返回了，直接退出
            if (p1 == n) {
                break;
            }

            // 双指针过程
            p2 = p1;
            // 直到找到非数字字符，p1指向数字第一个字符，p2指向数字最后一个字符的下一个位置
            while (p2 < n && Character.isDigit(word.charAt(p2))) {
                p2++;
            }
            while (p2 - p1 > 1 && word.charAt(p1) == '0') { // 去除前导 0
                p1++;
            }
            set.add(word.substring(p1, p2));
            p1 = p2;
        }
        return set.size();
    }

    public static void main(String[] args) {
        String s = "sh8s0";
        Solution solution = new Solution();
        System.out.println(solution.numDifferentIntegers(s));
    }
}