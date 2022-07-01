package leetcode.p1175;

public class Solution {
    private static final int MOD = (int) Math.pow(10, 9) + 7;
    // 质数的定义：大于1的自然数，除了1和它自身外，不能被其他比它小的自然数整除的数叫做质数
    public int numPrimeArrangements(int n) {
        // 计算n以内总共有多少质数
        int primeCount = 0;
        for (int i = 1; i <= n; i++) {
            primeCount += isPrime(i);
        }

        // 组合的数量 = 质数数量的阶乘 * 非质数数量的阶乘（质数在质数的索引上，非质数在非质数的索引上。质数在质数索引上的排列组合的数量 * 非质数在非质数索引上的排列组合的数量）
        // 比如质数在质数索引上排好了一个位置，那么剩余的非质数能够排列组合的数量就是非质数在非质数索引上的排列组合的数量，即非质数数量的阶乘
        //  同时质数在质数索引上的排列组合的数量是质数数量的阶乘
        // 比如n=5，那么总共有2 3 5三个质数，1 4两个非质数，[1,2,3,4,5]是一个符合的排列，那么非质数还有一种排列方式即[4,2,3,1,5]，质数总共有6种排列方式
        return (int) ((factorial(primeCount) * factorial(n - primeCount)) % MOD);
    }

    // 求n的阶乘
    private long factorial(int n) {
        long rst = 1;
        for (int i  = 1; i <= n; i++) {
            rst *= i;
            rst %= MOD;
        }
        return rst;
    }

    // 判断一个数是不是质数
    private int isPrime(int n) {
        // 2是最小的质数
        if (n < 3) {
            return n > 1 ? 1 : 0;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {   // 整除，表明n能够被比它小的自然数整数，所以不是质数
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 100;
        System.out.println(solution.numPrimeArrangements(n));
        System.out.println(solution.factorial(25));
        System.out.println(solution.factorial(100 - 25));

    }
}
