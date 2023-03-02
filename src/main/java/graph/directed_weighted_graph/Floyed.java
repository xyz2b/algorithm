package graph.directed_weighted_graph;

// 求解任意两点之间的最短距离
import java.util.Arrays;

// 同无向图
/**
 * 初始，如果v-w有边，dis[Solution][w]=vw；dis[Solution][Solution]=0，否则dis[Solution][w]=正无穷
 *
 * 遍历所有的点对v-w，然后判断如果从v-w的路径上再多经过一个点t，得到的距离会不会比之前v-w的距离短
 *
 * 判断有没有负权环：只需要判断dis[Solution][Solution]是不是小于0即可，即自己到自己的距离是不是小于0，如果小于0，说明就有负权环
 * */
public class Floyed {
    private WeightedGraph G;
    // 存储两点之间的最短路径
    private int[][] distance;
    private boolean hashNegativeCycle;

    public Floyed(WeightedGraph G) {
        this.G = G;
        distance = new int[G.V()][G.V()];
        for(int i = 0; i < distance.length; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        for(int v = 0; v < G.V(); v++) {
            distance[v][v] = 0;
            for(int w : G.adj(v)) {
                distance[v][w] = G.getWeight(v, w);
            }
        }

        for(int t = 0; t < G.V(); t++) {
            for(int v = 0; v < G.V(); v++) {
                for(int w = 0; w < G.V(); w++) {
                    if(distance[v][t] != Integer.MAX_VALUE && distance[t][w] != Integer.MAX_VALUE &&
                            distance[v][t] + distance[t][w] < distance[v][w]) {
                        distance[v][w] = distance[v][t] + distance[t][w];
                    }
                }
            }
        }

        for(int v = 0; v < G.V(); v++) {
            if(distance[v][v] < 0) {
                hashNegativeCycle = true;
            }
        }
    }

    public boolean hashNegativeCycle() {
        return hashNegativeCycle;
    }

    public int distTo(int s, int t) {
        G.validateVertex(s);
        G.validateVertex(t);
        return distance[s][t];
    }

    public boolean isConnectedTo(int s, int t) {
        G.validateVertex(s);
        G.validateVertex(t);
        return distance[s][t] != Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("wg2.txt", true);
        Floyed floyed = new Floyed(g);
        if(!floyed.hashNegativeCycle) {
            for(int v = 0; v < g.V(); v++) {
                for(int w = 0; w < g.V(); w++) {
                    System.out.print(floyed.distTo(v, w) + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("exist negative cycle");
        }
    }
}
