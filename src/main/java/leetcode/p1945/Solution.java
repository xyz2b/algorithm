package leetcode.p1945;

public class Solution {
    public int getLucky(String s, int k) {
        // 至少转换一次，所以先预处理下s，转换一次，因为直接变成数字过长，long存储不下，所以直接先进行一次转换，进行一次转换之后的数字长度int可以存储下
        // 之后再进行后续多次转换
        int num = 0;
        for(int i = 0; i < s.length(); i++) {
            int n = s.charAt(i) - 'a' + 1;
            // n在本题中最多两位
            while (n != 0) {
                int p = n % 10;
                n = n / 10;
                num += p;
            }
        }

        for(int i = 1; i < k; i++) {
            int sum = 0;
            while (num != 0) {
                int p = num % 10;
                num = num / 10;
                sum += p;
            }
            num = sum;
        }

        return num;
    }
}
