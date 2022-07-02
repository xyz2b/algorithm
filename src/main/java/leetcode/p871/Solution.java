package leetcode.p871;

import java.util.PriorityQueue;

public class Solution {
    // 为了得到最少加油次数，应该在确保每个位置都能到达的前提下，选择最大加油量的加油站加油。
    // 从左到右遍历数组 stations，对于每个加油站，首先判断该位置是否可以达到，然后将当前加油站的加油量添加到优先队列中。对于目的地，则只需要判断是否可以达到
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int rst = 0;

        // 记录经过的每个加油站的油量，按从大到小排序
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // prev是上一个达到的加油站距离起点的距离
        // fuel是当前剩余油量
        int prev = 0, fuel = startFuel;
        int n = stations.length;
        for (int i = 0; i <= n; i++) {
            // 到达当前加油站，当前已经行驶的距离（加油站距离起点的距离）；如果后面没有加油站了，就是直接达到目的地所行驶的距离（目的地距离起点的距离）
            int cur = i < n ? stations[i][0] : target;

            // 当前油量 减去 当前到达加油站和上一个到达的加油站的距离，就是剩余油量
            fuel -= cur - prev;

            // 如果剩余油量小于0，表示到不了当前加油站，所以需要在之前找个油最多的加油站加油，直到能够到达当前加油站 或者 之前所有加油站都加油了还是到不了当前加油站
            while (fuel < 0 && !queue.isEmpty()) {
                fuel += queue.poll();
                rst++;
            }

            // 之前所有加油站都加油了还是到不了当前加油站，就是无法到达目的地
            if (fuel < 0) {
                return -1;
            }

            // 将当前加油站的油量记录下来
            // 同时将当前加油站距离起点的距离记录下来，以用来计算下一个加油站到当前加油站的距离
            if (i < n) {
                queue.offer(stations[i][1]);
                prev = cur;
            }
        }

        return rst;
    }
}
