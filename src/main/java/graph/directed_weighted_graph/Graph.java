package graph.directed_weighted_graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

// 图的邻接表表示法
// 暂时只支持无向无权图
public class Graph implements Cloneable {
    // 顶点数(vertex count)
    private int V;
    // 边数(edge count)
    private int E;
    // 邻接表
    private TreeSet<Integer>[] adj;
    private boolean directed;
    private int inDegrees[];
    private int outDegrees[];
    public Graph(String filename) {
        this(filename, false);
    }

    public Graph(String filename, boolean directed) {
        this.directed = directed;
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeSet[V];
            for(int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
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

                // 只处理简单图，无自环边，无平行边
                // 自环边
                if(a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                // 平行边
                // 实际在解决算法问题时，可能有些情况下有平行边，比如解决最短路径时，此时这条边上的权值就选取最小的那个权值
                if(adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }
                adj[a].add(b);
                if(directed) {
                    outDegrees[a]++;
                    inDegrees[b]++;
                }
                if(!directed) {
                    adj[b].add(a);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Graph(TreeSet<Integer>[] adj, boolean directed) {
        this.adj = adj;
        this.V = adj.length;
        this.directed = directed;
        this.E = 0;

        this.inDegrees = new int[V];
        this.outDegrees = new int[V];
        for(int v = 0; v < V; v ++) {
            for(int w : adj[v]) {
                inDegrees[v]++;
                outDegrees[w]++;
                E++;
            }
        }

        if(!directed) E /= 2;
    }

    public Graph reverseGraph() {
        TreeSet<Integer>[] radj = new TreeSet[V];
        for (int v = 0; v < V; v++) {
            radj[v] = new TreeSet<>();
        }

        for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                radj[w].add(v);
            }
        }

        return new Graph(radj, directed);
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
        return adj[v].contains(w);
    }

    // 和顶点v相连的顶点集合
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
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

        if(adj[v].contains(w)) {
            E--;
            if(directed) {
                outDegrees[v]--;
                inDegrees[w]--;
            }
        }

        adj[v].remove(w);
        if(!directed) {
            adj[w].remove(v);
        }
    }

    @Override
    protected Object clone() {
        try {
            Graph cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[V];
            for(int v = 0 ; v < V; v++) {
                cloned.adj[v] = new TreeSet<>();
                for(int w : adj(v)) {
                    cloned.adj[v].add(w);
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
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph("ug.txt", true);
        System.out.println(graph);
        for(int v = 0; v < graph.V(); v++) {
            System.out.println(graph.inDegree(v) + " " + graph.outDegree(v));
        }
    }
}
