package leetcode.p1103;

public class Solution {
    // 暴力
    public int[] distributeCandies(int candies, int num_people) {
        int[] ret = new int[num_people];
        ret[0] = 1;
        candies--;

        int i = 1;
        while (candies > 0) {
            if(candies - (i+1) >= 0) {
                ret[i%num_people] += (i+1);
            } else {
                ret[i%num_people] += candies;
            }
            candies -= (i+1);
            i++;
        }

        return ret;
    }

        // 等差数列，数学方法
    public int[] distributeCandies2(int candies, int num_people) {
        int n = num_people;
        // how many people received complete gifts
        int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
        int remaining = (int) (candies - (p + 1) * p * 0.5);
        int rows = p / n, cols = p % n;

        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            // complete rows
            d[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * n;
            // cols in the last row
            if (i < cols) {
                d[i] += i + 1 + rows * n;
            }
        }
        // remaining candies
        d[cols] += remaining;
        return d;
    }
}
