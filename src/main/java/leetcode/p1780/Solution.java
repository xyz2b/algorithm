package leetcode.p1780;

public class Solution {
    public boolean checkPowersOfThree(int n) {
        // 如果有需要的话（原始数字不能整除3），最开始有机会先减去一个1
        // 连续除以3，直到除到1，结束循环
        // 整除一次3，如果需要的话之后可以有机会减一次1，但是不能连续减去1

        if(n % 3 > 0) {
            n = n - 1;
            if(n % 3 > 0) {
                return false;
            }
        }

        int subOne = 0;
        // 不能连续减2次1，这个标志标识是否之前一次已经减去1了
        boolean beforeSubOne = false;
        while (n > 1) {
            if(n % 3 > 0) {
                if(subOne - 1 >= 0 && !beforeSubOne) {
                    subOne--;
                    beforeSubOne = true;
                    n = n - 1;
                } else {
                    return false;
                }
            } else {
                n = n / 3;
                // 如果除以3之后的结果等于2，也是不行的，除以的结果等于1结束循环，大于等于3继续循环，等于2是不满足题意的，因为分解出来了一个2，不是3的幂次方
                if(n == 2) {
                    return false;
                }
                subOne++;
                beforeSubOne = false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkPowersOfThree(21));
    }
}
