package graph.directed_weighted_graph;

import java.util.*;

public class MaxFlow {
    private WeightedGraph network;
    private WeightedGraph rG;
    private int maxFlow = 0;
    private int s, t;

    public MaxFlow(WeightedGraph network, int s, int t) {
        if(!network.directed())
            throw new IllegalArgumentException("MaxFlow only works in directed graph");

        if(network.V() < 2)
            throw new IllegalArgumentException("the network should has at least two vertices");

        network.validateVertex(s);
        network.validateVertex(t);
        if(s == t)
            throw new IllegalArgumentException("s and t should be different");


        this.network = network;
        this.s = s;
        this.t = t;

        // 创建残量图
        this.rG = new WeightedGraph(network.V(), true);
        for(int v = 0; v < network.V(); v++) {
            for(int w : network.adj(v)) {
                rG.addEdge(v, w, network.getWeight(v, w));
                rG.addEdge(w, v, 0);
            }
        }

        while (true) {
            // bfs寻找增广路径
            ArrayList<Integer> augPath = getAugmentingPath();

            // 没有新的增广路径了
            if(augPath.size() == 0) break;

            int f = Integer.MAX_VALUE;
            // 计算增广路径上的最小值
            for(int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);

                f = Math.min(f, rG.getWeight(v, w));
            }

            maxFlow += f;

            // 根据增广路径更新残量图rG
            // 需要判断是正向边，还是逆向边，因为更新规则不同
            // 如果这个边在原来的流量图network中存在，那么就是正向边，不存在就是逆向边
            for(int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);

                if (network.hasEdge(v, w)) {
                    rG.updateWeight(v, w, rG.getWeight(v, w) - f);
                    rG.updateWeight(w, v, rG.getWeight(w, v) + f);
                } else {
                    rG.updateWeight(w, v, rG.getWeight(w, v) + f);
                    rG.updateWeight(v, w, rG.getWeight(v, w) - f);
                }
            }

        }

    }

    private ArrayList<Integer> getAugmentingPath() {
        // bfs
        Queue<Integer> queue = new ArrayDeque<>();
        int[] pre = new int[network.V()];
        Arrays.fill(pre, -1);

        queue.add(s);
        pre[s] = s;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if(cur == t) break;

            for(int next : rG.adj(cur)) {
                if(pre[next] == -1 && rG.getWeight(cur, next) > 0) {
                    pre[next] = cur;
                    queue.add(next);
                }
            }
        }

        ArrayList<Integer> augPath = new ArrayList<>();
        // 没有新的增广路径
        if(pre[t] == -1) return augPath;

        // 根据pre数组得出增广路径
        int cur = t;
        while (cur != s) {
            augPath.add(cur);
            cur = pre[cur];
        }
        augPath.add(s);

        Collections.reverse(augPath);
        return augPath;
    }

    public int result() {
        return maxFlow;
    }

    // Solution-w的最大流量
    public int flow(int v, int w) {
        if(!network.hasEdge(v, w))
            throw new IllegalArgumentException(String.format("No edge %d--%d", v, w));

        // Solution-w的最大流量就是残量图中逆向边w-v的值
        return rG.getWeight(w, v);
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("network2.txt", true);
        MaxFlow mf = new MaxFlow(g,0 ,3);
        System.out.println(mf.result());
    }
}
