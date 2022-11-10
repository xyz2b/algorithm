package graph.undirected_unweighted_graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// 无向无权图的领接矩阵表示方法
// 给出图的方式如g.txt，第一行表示顶点数和边数，后面行表示存在边的两个顶点，将g.txt转成领接矩阵
// 只讨论简单图，无自环边(主对角线的元素都为0)，无平行边(元素非0即1)，无向(关于主对角线对称)
public class AdjMatrix {
    // 顶点数(vertex count)
    private int V;
    // 边数(edge count)
    private int E;
    // 邻接矩阵
    private int[][] adj;

    public AdjMatrix(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            E = scanner.nextInt();
            if(E < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new int[V][V];
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
                if(adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
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
        return adj[v][w] == 1;
    }

    // 和顶点v相连的顶点集合
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    // 返回顶点v的度，即顶点v有多少个邻边，即顶点v有多少个相邻的顶点
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }
}
