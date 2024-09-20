package leetcode.p815;

import java.util.*;

public class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;
        // 以公交车为节点，构建无向无权图，如果两条公交线路之间有相同的站点，即两个公交车之间存在边
        int n = routes.length;
        int[][] edges = new int[n][n];
        // 站点到公交车的映射
        Map<Integer, List<Integer>> rec = new HashMap<>();
        for(int i = 0; i < n; i++) {
            for(int site : routes[i]) {
                List<Integer> list = rec.getOrDefault(site, new ArrayList<Integer>());
                for(int l : list) {
                    edges[l][i] = edges[i][l] = 1;
                }
                list.add(i);
                rec.put(site, list);
            }
        }

        // 广度优先遍历求最短路径
        // 充当visited数组，以及 起始公交车(多个)到当前公交车(数组下标)的距离
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        // 由于可能存在多个起始和终止公交车，初始时将多个公交车都入队
        Queue<Integer> queue = new ArrayDeque<>();
        for(int l : rec.getOrDefault(source, new ArrayList<>())) {
            queue.offer(l);
            dis[l] = 1;
        }

        while (!queue.isEmpty()) {
            int l = queue.poll();
            for(int y = 0; y < n; y++) {
                // l 和 y 公交车之间存在边，并且y公交车暂时还没访问到
                if(edges[l][y] == 1 && dis[y] == -1) {
                    dis[y] = dis[l] + 1;
                    queue.offer(y);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for(int l : rec.getOrDefault(target, new ArrayList<>())) {
            if(dis[l] != -1) {
                ret = Math.min(ret, dis[l]);
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
