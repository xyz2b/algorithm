package graph.directed_weighted_graph;

// 匈牙利算法 求最大匹配
// 匹配边，左边点和右边点匹配

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// 从左侧非匹配的点出发
// 从右向左的边，永远走匹配边
// 匹配边和非匹配边交替出现：交替路
// 终止于另外一个非匹配点：增广路径
// 有增广路径，意味着最大匹配数可以加一
public class HungarianDFS {
    private Graph G;
    private int maxMatching;
    // matching[a] = b，表示顶点a和顶点b式匹配的，反过来b和a也是匹配的，即matching[b] = a
    private int[] matching;
    private boolean[] visited;

    public HungarianDFS(Graph G) {
        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartition()) {
            throw new IllegalArgumentException("HungarianBFS only works for bipartite graph");
        }
        this.G = G;

        this.matching = new int[G.V()];
        Arrays.fill(matching, -1);
        this.visited = new boolean[G.V()];

        int[] colors = bd.colors();
        // 遍历左侧所有非匹配点
        for(int v = 0; v < G.V(); v++) {
            if (colors[v] == 0) {
                Arrays.fill(visited, false);
                // dfs返回从左侧非匹配点v出发是否有增广路径
                if(dfs(v)) maxMatching++;
            }
        }
    }

    // 从v点出发是否存在增广路径
    // dfs的参数一定是左侧的点
    private boolean dfs(int v) {
        // v一定是左侧的顶点
        visited[v] = true;
        // 遍历所有相邻的顶点，即相邻的右侧的顶点
        for(int u : G.adj(v)) {
            if(!visited[u]) {   // 未访问的右侧的点
                visited[u] = true;  // 注意此时右侧的点u是已经访问过了，记得标记
                 if (matching[u] == -1) {    // 右侧的点为未匹配的点，找到增广路径了，更新匹配点
                    // u为未匹配的点
                    // v为u的左侧的点
                    // v-u是匹配边
                    matching[u] = v;
                    matching[v] = u;
                    return true;
                } else { // 右侧的点为已匹配的点
                    // 和v匹配的左侧的点u，继续进行dfs
                    if(dfs(matching[u])) { // 存在增广路径，更新匹配点
                        // 由于以左侧点matching[u]为顶点存在增广路径
                        // 需要需要更新匹配点
                        // 增广路径: v - u - matching[u] - xxxx
                        // matching[u] - u 是以前的匹配边，matching[u]是左边的点，u是右侧的点
                        // 所以新的匹配边为 v - u
                        matching[u] = v;
                        matching[v] = u;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private ArrayList<Integer> getAugPath(int[] pre, int start, int end) {
        ArrayList<Integer> path = new ArrayList<>();

        int cur = end;
        while (cur != start) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(start);
        // 这里不做反向的原因是，找到增广路径之后修改匹配点时，正序逆序都一样
        return path;
    }

    public int maxMatching() {
        return maxMatching;
    }

    public static void main(String[] args) {
        Graph g = new Graph("network.txt", false);
        HungarianDFS hungarian = new HungarianDFS(g);
        System.out.println(hungarian.maxMatching());

        Graph g2 = new Graph("network3.txt", false);
        HungarianDFS hungarian2 = new HungarianDFS(g2);
        System.out.println(hungarian2.maxMatching());
    }
}
