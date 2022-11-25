package leetcode.p752;

import java.util.*;

// 状态转移-->图
// bfs最短路径
public class Solution {
    private HashSet<String> visited;
    private HashMap<String, Integer> distance;

    private HashSet<String> deadends;
    private String source;
    private String target;

    // deadends不可达的顶点
    // target目标顶点
    // "0000"起始顶点
    public int openLock(String[] deadends, String target) {
        this.deadends = new HashSet<>(deadends.length);
        this.deadends.addAll(Arrays.asList(deadends));

        this.target = target;
        this.source = "0000";
        this.visited = new HashSet<>();
        this.distance = new HashMap<>();

        if(!this.deadends.contains(source)) {
            bfs(source);
        }

        return distance.get(target) != null ? distance.get(target) : -1;
    }

    private void bfs(String s) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(s);
        visited.add(s);
        distance.put(s, 0);

        while (!queue.isEmpty()) {
            String v = queue.poll();

        }
    }
}
