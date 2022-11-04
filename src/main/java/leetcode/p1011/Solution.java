package leetcode.p1011;

import java.util.Arrays;

public class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // 搜索的左右边界，船的装载能力
        // 最小为最大货物的重量(不然这个货物就没法运输)，最大为 所有货物的总重量
        int l = Arrays.stream(weights).max().getAsInt(), r = Arrays.stream(weights).sum();
        // 因为transferTime是{单调函数(只有满足单调性，才能使用二分搜索，二分搜索前提是有序)}，weight越大，用时越少，即船的载重量越大，运输花费的时间就越少
        // 所以可以对船的装载能力对应的运输时间进行二分搜索，找到 <= days的最大值
        while (l < r) {
            int mid = l + (r - l) / 2;
            if(transferTime(weights, mid) <= days) {
                r = mid;
            } else {    // transferTime(weights, mid) > days，不满足要求，需要增大载重量
                l = mid + 1;
            }
        }
        return l;
    }

    // 单调函数，船的载重k越大，用时越少
    private int transferTime(int[] weights, int k) {
        // cur 为当前传送带的载重；res 为最终的返回结果
        int cur = 0, res = 0;

        // 遍历 weights 中的每一个元素
        for(int weight: weights)
            // 如果当前的重量加上当前的货物没有超过 k，即没有超过船的载重量
            // 把当前货物重量加在 cur 上
            if(cur + weight <= k)
                cur += weight;
            else{
                // 否则的话，相当于从当前的货物开始，我们需要新的一天运输
                // res ++，同时，cur 更新为当前的重量
                res ++;
                cur = weight;
            }

        // 最后还要做一次 res ++，因为在这里 cur 肯定不为零，还记录着最后一天需要运送的货物重量
        // 最后一天，要加到 res 中，所以 res ++
        res ++;

        return res;
    }

    public static void main(String[] args) {
        int[] weights = new int[] {1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        Solution solution = new Solution();
        System.out.println(solution.shipWithinDays(weights, days));

        System.out.println(solution.transferTime(weights, 15));
    }
}
