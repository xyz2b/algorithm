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

    private int[] dirs = {-1, 1};

    private void bfs(String s) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(s);
        visited.add(s);
        distance.put(s, 0);

        while (!queue.isEmpty()) {
            String v = queue.poll();

            for(int i = 0; i < 4; i++) {
                char c = v.charAt(i);

                // 每一位都可以+1或-1，0-1=9，9+1=0
                for(int d = 0; d < 2; d++) {
                    char nextc;
                    if(c == '9' && dirs[d] == 1) {
                        nextc = '0';
                    } else if (c == '0' && dirs[d] == -1) {
                        nextc = '9';
                    } else {
                        nextc = (char) (c + dirs[d]);
                    }

                    StringBuilder sb = new StringBuilder(v);
                    sb.setCharAt(i, nextc);
                    String w = sb.toString();
                    if(!visited.contains(w) && !deadends.contains(w)) {
                        queue.offer(w);
                        visited.add(w);
                        distance.put(w, distance.get(v) + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        Solution solution = new Solution();
        System.out.println(solution.openLock(deadends, target));
    }
}
