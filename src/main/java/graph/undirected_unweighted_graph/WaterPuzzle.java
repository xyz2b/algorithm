package graph.undirected_unweighted_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/**
 * 有两个水桶，一个桶5升，一个桶3升
 * 怎么利用这两个水桶，得到4升水
 *
 * 状态转移问题，类似p752，轮盘锁问题
 * 起始状态: (0,0)
 * 最终状态: (4,?)
 *
 * 状态变化
 * 1.(x,y) -> (5,y)、(x,3)
 * 2.(x,y) -> (0,y)、(x,0)
 * 3.(x,y) -> 将x的水倒给y
 * 4.(x,y) -> 将y的水倒给x
 *
 * 状态压缩
 * 因为水最多只有5
 * (x,y) -> 10*x+y
 * 最多为53
 * */
public class WaterPuzzle {
    private int[] pre;
    // 起始点
    private int s;
    // 终止点
    private int t;

    public WaterPuzzle() {
        pre = new int[54];
        for(int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }

        s = 0;

        // 从(0,0)开始，即0
        // 返回终点，即(4,?)
        t = bfs(s);
    }

    private int bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(s);
        pre[s] = s;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            int x = v / 10;
            int y = v % 10;

            ArrayList<Integer> nextvs = new ArrayList<>();
            // (x,y) -> (5,y)、(x,3)
            nextvs.add(5 * 10 + y);
            nextvs.add(x * 10 + 3);

            // (x,y) -> (0,y)、(x,0)
            nextvs.add(0 * 10 + y);
            nextvs.add(x * 10 + 0);

            // 将x的水倒给y
            // 能倒多少出去，或者是x(y全部放得下)，或者是3 - y(y只能放下部分)
            int xwater = Math.min(x, 3 - y);
            nextvs.add((x - xwater) * 10 + (y + xwater));

            // 将y的水倒给x
            int ywater = Math.min(5 - x, y);
            nextvs.add((x + ywater) * 10 + (y - ywater));

            for(int nextv : nextvs) {
                // 没被访问过
                if(pre[nextv] == -1) {
                    queue.add(nextv);
                    pre[nextv] = v;

                    if(nextv / 10 == 4) {
                        return nextv;
                    }
                }
            }
        }
        // 从s到达不了t，返回-1
        return -1;
    }

    public Iterable<int[]> path() {
        ArrayList<int[]> path = new ArrayList<>();
        if(t == -1) return path;

        int cur = t;
        while (cur != s) {
            int x = cur / 10;
            int y = cur % 10;
            path.add(new int[] {x, y});
            cur = pre[cur];
        }
        path.add(new int[] {s / 10, s % 10});

        Collections.reverse(path);
        return path;
    }


    public static void main(String[] args) {
        WaterPuzzle waterPuzzle = new WaterPuzzle();
        StringBuilder sb = new StringBuilder();

        for(int[] v : waterPuzzle.path()) {
            sb.append("[").append(v[0]).append(",").append(v[1]).append("]").append('\n');
        }

        System.out.println(sb.toString());
    }
}
