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

    // 单调函数，weight越大，用时越少
    private int transferTime(int[] weights, int weight) {
        int days = 0;
        int w = weight;
        for(int i = 0; i < weights.length; i++) {
            if(w - weights[i] == 0) {   // 装上这批货船装满了，运输完之后，清空货仓，第二天继续装货
                days++; // 这里的days++加的是当前这批货物运走的一天
                w = weight;
            } else if (w - weights[i] <= 0) {   // 此时船剩余空间不足以装这批货了，需要等第二天船清空之后，然后再把这批货装上
                days++;
                w = weight - weights[i];
                if(i == weights.length - 1) {   // 因为这里的days++加的是上一批货物运走的那一天，如果最后一批货物走到这里，说明这批货物单独装船需要再花费一天
                    days++;
                }
            } else {    // 船没满，就直接装上这批货，再继续装下一批货
                w = w - weights[i];
                if(i == weights.length - 1) {   // 如果最后几批货物都装上了船，但是船还没满，就会走到这里，但是这一天的时间是没有加上的
                    days++;
                }
            }
        }
        return days;
    }

    public static void main(String[] args) {
        int[] weights = new int[] {1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        Solution solution = new Solution();
        System.out.println(solution.shipWithinDays(weights, days));

        System.out.println(solution.transferTime(weights, 15));
    }
}
