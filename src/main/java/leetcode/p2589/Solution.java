package leetcode.p2589;

import java.util.Arrays;

/**
 首先将 tasks 按照 end 从小到大进行排序。使用 run 数组标记哪些时间点有任务有运行，从小到大遍历数组 tasks，假设当前遍历的元素为 tasks[i]=[starti,endi,durationi]
 ，统计 run 数组在时间段 [starti,endi] 内有运行的时间点数目 total：
    如果 total≥durationi，那么第 i 个任务可以放到先前运行的时间里运行。
    如果 total<durationi ，那么我们可以将第 i 个任务的 total 个时间放到先前运行的时间里运行，
        durationi−total 个时间从右到左依次放到区间 [starti,endi]内没有运行的时间点，从而保证后续的任务尽量利用先前任务的运行时间。
 最后返回 run 里的总运行时间。

 * */
public class Solution {
    public int findMinimumTime(int[][] tasks) {
        int n = tasks.length;
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int[] run = new int[tasks[n - 1][1] + 1];
        int res = 0;
        for (int i = 0; i < n; i++) {
            int total = 0;
            int start = tasks[i][0], end = tasks[i][1], duration = tasks[i][2];
            for (int j = start; j <= end; j++) {
                if(run[j] != 0) {
                    total++;
                }
            }
            if(total >= duration) {
                continue;
            } else {
                int less = duration - total;
                res += less;
                for (int j = end; j >= 0 && less > 0; j--) {
                    if (run[j] == 0) {
                        less--;
                        run[j] = 1;
                    }
                }
            }

        }
        return res;
    }
}
