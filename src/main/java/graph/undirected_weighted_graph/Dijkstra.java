package graph.undirected_weighted_graph;

import java.util.Arrays;

public class Dijkstra {
    private WeightedGraph G;
    private int s;

    private int[] distance;
    private boolean[] visited;

    public Dijkstra(WeightedGraph G, int s) {
        this.G = G;
        this.s = s;

        this.distance = new int[G.V()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        visited = new boolean[G.V()];

        distance[s] = 0;
        while (true) {
            int curdis = Integer.MAX_VALUE, cur = -1;
            for(int v = 0; v < distance.length; v++) {
                if(!visited[v] && distance[v] < curdis) {
                    curdis = distance[v];
                    cur = v;
                }
            }

            // 说明所有顶点都遍历完了，退出循环
            if(cur == -1) {
                break;
            }

            visited[cur] = true;
            for(int w : G.adj(cur)) {
                if(!visited[w]) {
                    if(distance[cur] + G.getWeight(cur, w) < distance[w]) {
                        distance[w] = distance[cur] + G.getWeight(cur, w);
                    }
                }
            }
        }
    }

    public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v) {
        G.validateVertex(v);
        return distance[v];
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("gw2.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        for(int v = 0; v < g.V(); v++) {
            System.out.print(dijkstra.distTo(v) + " ");
        }
        System.out.println();
    }
}
