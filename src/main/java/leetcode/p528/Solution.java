package leetcode.p528;

import java.util.Random;

public class Solution {
    // 根据任务的权重决定给其发放几张ticket票据，权重为几就发放几张ticket票据
    // tickets数组的索引为任务序号，值为该任务所具有的票据起始号码，该任务所具有的票据结束号码是下一个任务票据的起始号码-1
    // tickets数组的值其实就是计算任务权重数组w的前缀和
    // tickets数组会比权重数组长度大一，因为它最后一个元素存储的是最后一个任务票据的结束号码+1，同时数组最后一个元素也是发放的票据总数，该数组最后一个元素不对应到任何一个任务
    private int[] tickets;
    private Random rnd;

    // w数组的索引是任务编号，值为对应任务的权重
    public Solution(int[] w) {
        tickets = new int[w.length + 1];

        // 计算w的前缀和
        tickets[0] = 0;
        for(int i = 0; i < w.length; i++) {
            tickets[i + 1] = tickets[i] + w[i];
        }

        rnd = new Random();
    }

    public int pickIndex() {
        // 票据的总数
        int ticketsNum = tickets[tickets.length - 1];
        // 随机选择一个ticket，[0, ticketsNum)
        int nextTicket = rnd.nextInt(ticketsNum);

        // tickets是单调递增的，所以可以二分搜索法在其中找寻符合某个条件的值
        // 二分搜索法在tickets数组中找到 <= nextTicket的最大值 其对应的索引 就是选中的任务索引
        // 所选择的ticket落到了该任务所具有的ticket的范围内，所以该任务就具有上面选中的ticket
        return searchLower(tickets, 0, tickets.length - 1, nextTicket);

    }

    private int searchLower(int[] arr, int l, int r, int t) {
        if (l > r) {
            return r;
        }

        int mid = l + (r - l) / 2;
        if(t < arr[mid]) {
            return searchLower(arr, l, mid - 1, t);
        } else if (t > arr[mid]) {
            return searchLower(arr, mid + 1, r, t);
        } else { // arr[t] == arr[mid]
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,14,1,7};
        Solution solution = new Solution(nums);
        System.out.println(solution.pickIndex());
    }
}
