package dynamicprogramming.p322;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < coins.length; i++) {
            for(int j = 0; j < amount/coins[i]; j++) {
                list.add(coins[i]);
            }
        }

        Integer[] c = new Integer[list.size()];
        list.toArray(c);
        return coinChange(c, amount, c.length - 1, 0);
    }

    // 尝试[0..index]的硬币中取出部分面值和等于amount需要最小硬币数
    public int coinChange(Integer[] coins, int amount, int index, int count) {
        if(amount == 0) {
            return count;
        }

        if(index < 0 || amount < 0) {
            return -1;
        }

        int l = coinChange(coins, amount, index - 1, count);
        int r = coinChange(coins, amount - coins[index], index - 1, count + 1);

        if(l == -1 && r == -1) {
            return -1;
        } else if (l != -1 && r == -1) {
            return l;
        } else if(r != -1 && l == -1) {
            return r;
        } else {
            return Math.min(l, r);
        }
    }

    // 尝试[0..index]的硬币中取出部分面值和等于amount需要最小硬币数
    // 第一种情况，不要index的硬币，只看[0...index-1]是否能正好凑够a
    // 第二种情况，要index的硬币，看[0...index-1]是否能正好凑够a-c[index]，这时候硬币总数就多了1
    // 注意: 考虑不能正好凑够a的情况，此时是为-1，直接判断最小会有问题的
    // 注意: a=0时，需要0枚硬币凑够，而不是凑不够值为-1
    // f(index, a) = Math.min(f(index-1, a), f(index-1, a-c[index]) + 1)
    public int coinChange3(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }

        // 因为amount有限制，所以每一种面值的硬币可以最多取多少个是固定，从而转换为了传统的背包问题
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < coins.length; i++) {
            for(int j = 0; j < amount/coins[i]; j++) {
                list.add(coins[i]);
            }
        }

        if(list.size() == 0) {
            return -1;
        }

        Integer[] c = new Integer[list.size()];
        list.toArray(c);
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);

        // -2表示还未计算，-1表示面值不能正好凑够a，>=0的数值表示面值正好凑够a所需要的硬币最小数
        for(int a = 0; a <= amount; a++) {
            if(a == 0) {
                // 凑够面值总和等于0，所需的硬币最小数为0个
                memo[a] = 0;
            } else {
                memo[a] = (c[0] == a) ? 1 : -1;
            }
        }

        for(int index = 1; index < c.length; index++) {
            for(int a = amount; a >= c[index]; a--) {
                int rstNoIndex =  memo[a];
                int rstWithIndex = memo[a - c[index]];
                if (rstNoIndex != -1 && rstWithIndex != -1) {
                    memo[a] = Math.min(memo[a], memo[a - c[index]] + 1);
                } else if(rstNoIndex != -1){
                    memo[a] = rstNoIndex;
                } else if(rstWithIndex != -1) {
                    memo[a] = rstWithIndex + 1;
                } else {
                    memo[a] = -1;
                }
            }
        }

        return memo[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 1;

        Solution solution = new Solution();
        System.out.println(solution.coinChange3(coins, amount));
    }
}
