package graph.undirected_weighted_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 松弛操作（Relaxation）
 * 假设dis[v]是从s到v的经过边数不超过k的最短距离
 *  if(dis[a] + ab < dis[b])
 *      dis[b] = dis[a] + ab
 * 找到从s到b的经过边数不超过k+1的最短距离
 *  if(dis[b] + ba < dis[a])
 *      dis[a] = dis[b] + ba
 * 找到从s到a的经过边数不超过k+1的最短距离
 *
 * Bellman-Ford算法：
 * 初始dis[s] = 0，其余dis值为正无穷
 * 对所有边进行一次松弛操作，则求出了到所有点，经过的边数最多为1的最短路
 * 对所有边再进行一次松弛操作，则求出了到所有点，经过的边数最多为2的最短路
 * ...
 * 对所有边进行V-1次松弛操作，则求出了到所有点，经过的边数最多为V-1的最短路
 *
 * 本质上Dijkstra也是做松弛操作，只是它可以提前结束，直接确定到某一点的最短路径(没有负权边)
 *
 *
 * 负权环
 * 当存在负权环的时候，没有最短路径
 * 对于无向图，有负权边等同于有负权环
 * */
public class BellmanFord {
    private WeightedGraph G;
    private int s;
    private int[] dis;
    private int[] pre;
    private boolean hashNegativeCycle;

    public BellmanFord(WeightedGraph G, int s) {
        this.G = G;
        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        // 遍历V-1次 所有边 进行松弛操作
        for(int i = 0; i < G.V() - 1; i++) {
            // 遍历所有边
            for(int v = 0; v < G.V(); v++) {
                for(int w : G.adj(v)) {
                    // 松弛操作
                    if(dis[v] + G.getWeight(v, w) < dis[w]) {
                        dis[w] = dis[v] + G.getWeight(v, w);
                        pre[w] = v;
                    }
                }
            }
        }

        // 最后再对所有边进行一次松弛操作，如果还能更新dis[w]，说明有负权环
        for(int v = 0; v < G.V(); v++) {
            for(int w : G.adj(v)) {
                // 松弛操作
                if(dis[v] + G.getWeight(v, w) < dis[w]) {
                    hashNegativeCycle = true;
                }
            }
        }
    }

    public boolean hashNegativeCycle() {
        return hashNegativeCycle;
    }

    public int disTo(int t) {
        G.validateVertex(t);
        return dis[t];
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return dis[t] != Integer.MAX_VALUE;
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> path = new ArrayList<>();
        if(!isConnectedTo(t)) return path;

        int cur = t;
        while (cur != s) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(s);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {

    }
}
