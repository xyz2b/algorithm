package graph.directed_weighted_graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// 无向带权图
public class WeightedGraph implements Cloneable {
    // 顶点数(vertex count)
    private int V;
    // 边数(edge count)
    private int E;
    // 邻接表
    // key为与顶点相连的顶点，value为这两个顶点所构成的边的权值
    private TreeMap<Integer, Integer>[] adj;
    private int inDegrees[];
    private int outDegrees[];
    private boolean directed;

    public WeightedGraph(String filename) {
        this(filename, false);
    }

    public WeightedGraph(String filename, boolean directed) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeMap[V];
            for(int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }
            E = scanner.nextInt();
            if(E < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }

            inDegrees = new int[V];
            outDegrees = new int[V];

            for(int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                int weight = scanner.nextInt();

                // 只处理简单图，无自环边，无平行边
                // 自环边
                if(a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                // 平行边
                // 实际在解决算法问题时，可能有些情况下有平行边，比如解决最短路径时，此时这条边上的权值就选取最小的那个权值
                if(adj[a].containsKey(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }
                adj[a].put(b, weight);
                outDegrees[a]++;
                inDegrees[b]++;
                if(!directed) {
                    adj[b].put(a, weight);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean directed() {
        return directed;
    }

    public void validateVertex(int v) {
        if(v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // 判断两个顶点之间是否有边，即是否相连
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    // 和顶点v相连的顶点集合
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
    }

    // 返回v--w两个顶点所构成边的权值
    public int getWeight(int v, int w) {
        if(hasEdge(v, w))
            return adj[v].get(w);
        throw new IllegalArgumentException(String.format("No edge %d--%d", v, w));
    }

    // 返回顶点v的度，即顶点v有多少个邻边，即顶点v有多少个相邻的顶点
    public int degree(int v) {
        if(directed) {
            throw new IllegalArgumentException("directed graph not support degree method");
        }
        validateVertex(v);
        return adj[v].size();
    }

    public int inDegree(int v) {
        if(!directed) {
            throw new IllegalArgumentException("undirected graph not support inDegree method");
        }
        validateVertex(v);
        return inDegrees[v];
    }

    public int outDegree(int v) {
        if(!directed) {
            throw new IllegalArgumentException("undirected graph not support outDegree method");
        }
        validateVertex(v);
        return outDegrees[v];
    }

    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        adj[v].remove(w);
        outDegrees[v]--;
        inDegrees[w]--;
        if(!directed) {
            adj[w].remove(v);
        }
    }

    @Override
    protected Object clone() {
        try {
            WeightedGraph cloned = (WeightedGraph) super.clone();
            cloned.adj = new TreeMap[V];
            for(int v = 0 ; v < V; v++) {
                cloned.adj[v] = new TreeMap<>();
                for(Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
        for(int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                sb.append(String.format("(%d: %d)", entry.getKey(), entry.getValue()));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("wg.txt", true);
        System.out.println(weightedGraph);
    }
}
