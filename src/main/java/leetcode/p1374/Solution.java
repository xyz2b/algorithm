package leetcode.p1374;

public class Solution {
    // n分解成多个奇数和的问题

    // 先分出去一个1，如果剩下的数是奇数，就不用再分了
    // 如果剩下的数是个偶数，那么就再分一个1出去，剩下的数一定是奇数
    public String generateTheString(int n) {
        if (n == 1) {
            return "a";
        }

        int a = n - 1;
        int b = 0;
        int c = 0;
        if (a % 2 == 0) {
            b = a - 1;
            if (b % 2 == 0) {
                c = b - 1;
                a = 1;
                b = 1;
            } else {
                a = 1;
                c = 1;
            }
        } else {
            b = 1;
            c = 0;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < b; i++) {
            stringBuilder.append('a');
        }

        for(int i = 0; i < a; i++) {
            stringBuilder.append('b');
        }

        if(c != 0) {
            stringBuilder.append('c');
        }

        return stringBuilder.toString();
    }

    // 如果n是偶数，一定能分解成1和一个奇数相加
    // 如果n是奇数，一定能分解成1和1再和一个奇数相加
    public String generateTheString2(int n) {
        if(n == 1) {
            return "a";
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (n % 2 == 0) {
            stringBuilder.append("a");
            for(int i = 0; i < n - 1; i++) {
                stringBuilder.append("b");
            }
        } else {
            stringBuilder.append("a");
            stringBuilder.append("b");
            for(int i = 0; i < n - 2; i++) {
                stringBuilder.append("c");
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for(int i = 1; i <= 500; i ++) {
            System.out.println(solution.generateTheString(i));

        }
    }
}
