package leetcode.p875;

import java.util.Arrays;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // 搜索的左右边界，最少一小时吃1根香蕉，最多一小时吃piles中最多的那一堆香蕉数
        // 搜索时，比较条件是将 h 和 以当前速率吃香蕉需要多少个小时 进行比较
        // 当前吃香蕉的速率的左右边界就是l, r
        int l = 1, r = Arrays.stream(piles).max().getAsInt();
        // 因为eatTime是{单调函数(只有满足单调性，才能使用二分搜索，二分搜索前提是有序)}，k越大，用时越少，即每个小时吃香蕉越多，用时就越少
        // 所以可以对船的装载能力对应的运输时间进行二分搜索，找到 <= days的最大值
        // 找到小于等于h的最大值
        while (l < r) {
            int mid = l + (r - l) / 2;

            if(eatTime(piles, mid) <= h) {  // 以mid速率吃香蕉的时间，小于等于 h，mid满足要求
                // mid也可能是解
                r = mid;
            } else {    // eatTime(piles, mid) > h，mid不满足要求
                // mid不是可能的解，已经超出了h
                l = mid + 1;
            }
        }

        return l;
    }

    private int eatTime(int[] piles, int k) {
        int time = 0;
        for(int j = 0; j < piles.length; j++) {
            time += piles[j] / k + (piles[j] % k > 0 ? 1 : 0);
        }
        return time;
    }

    private int searchLower(int[] piles, int l, int r, int h) {
        if (l >= r) {
            return l;
        }

        int mid = l + (r - l) / 2;
        if(h < eatTime(piles, mid)) {   // mid不是可能的解，已经超出了h
            return searchLower(piles, mid + 1, r, h);
        } else { // h >= eatTime(piles, mid)，向下再找是不是可能有更小的解，但是mid也可能是解
            return searchLower(piles, l, mid, h);
        }
    }

    public static void main(String[] args) {
        int[] piles = new int[] {3,6,7,11};
        int h = 8;
        Solution solution = new Solution();
        System.out.println(solution.minEatingSpeed(piles, h));
    }
}
