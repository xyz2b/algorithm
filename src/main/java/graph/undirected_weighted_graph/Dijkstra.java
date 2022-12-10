package graph.undirected_weighted_graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    private WeightedGraph G;
    private int s;

    private int[] distance;
    private boolean[] visited;

    public class Node implements Comparable<Node> {
        public int v, distance;

        public Node(int v, int distance) {
            this.v = v;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node another) {
            return distance - another.distance;
        }
    }

    public Dijkstra(WeightedGraph G, int s) {
        this.G = G;
        this.s = s;

        this.distance = new int[G.V()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        visited = new boolean[G.V()];

        distance[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            int cur = pq.remove().v;
            // 已经确定是最短路径之后的顶点，直接丢掉
            if(visited[cur]) continue;

            visited[cur] = true;
            for(int w : G.adj(cur)) {
                if(!visited[w]) {
                    if(distance[cur] + G.getWeight(cur, w) < distance[w]) {
                        distance[w] = distance[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, distance[w]));
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
