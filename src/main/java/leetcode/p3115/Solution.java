package leetcode.p3115;

public class Solution {
    // 找到数组中第一个质数以及最后一个质数，距离就是最大的
    public int maximumPrimeDifference(int[] nums) {
        int first = -1, end = -1;

        for(int i = 0; i < nums.length; i++) {
            if(isPrime(nums[i])) {
                if(first == -1) {
                    first = i;
                }
                end = i;
            }
        }

        return end - first;
    }

    // 素数(质数)定义：只能被1和自身整除的数
    boolean isPrime(int n) {
        if(n == 1) { // 1不是质数
            return false;
        }
        /**
         * 换句话说，i 不需要遍历到 n，而只需要到 sqrt(n) 即可。为什么呢，我们举个例子，假设 n = 12。
         *
         * 12 = 2 × 6
         * 12 = 3 × 4
         * 12 = sqrt(12) × sqrt(12)
         * 12 = 4 × 3
         * 12 = 6 × 2
         * 可以看到，后两个乘积就是前面两个反过来，反转临界点就在 sqrt(n)。
         *
         * 换句话说，如果在 [2,sqrt(n)] 这个区间之内没有发现可整除因子，就可以直接断定 n 是素数了，因为在区间 [sqrt(n),n] 也一定不会发现可整除因子。
         * */
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
