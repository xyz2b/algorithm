package graph.undirected_unweighted_graph;

import java.util.ArrayList;

/**
 * 二分图检测
 * 通过染色实现，相邻两个节点需要染成不同的颜色
 * 顶点v可以分成不相交的两个部分
 * 所有的边的两个端点隶属于不同部分
 *
 * 处理匹配问题
 * 一部分是学生
 * 一部分是课程
 * 两部分之间的连线代表某个学生喜欢某一个课程
 * */
public class BipartitionDetection {
    private Graph G;
    private boolean[] visited;
    private int[] colors;

    private boolean isBipartition = true;


    public BipartitionDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        // 1为红色，0为蓝色
        colors = new int[G.V()];
        for(int i = 0; i < colors.length; i++) {
            colors[i] = -1;
        }
        // 因为需要所有连通分量都要满足二分图性质，所以这里需要从所有顶点都进行遍历
        for(int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if(!dfs(v, 0)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    // 从顶点v开始，判断图是否满足二分图的性质
    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : G.adj(v)) {
            if(!visited[w]) {   // 未访问过的肯定未染色，已染色的肯定是访问过的
                if(!dfs(w, 1 - color))  // w节点和其上一个节点v的颜色相反(color为w上一个节点v的颜色)，因为只有0、1两种取值，就可以直接使用1-color来取相反的值
                    return false;
            } else if(colors[w] == colors[v]){
                // 如果v的下一个节点w的颜色和v的颜色相同，表明不是二分图
                return false;
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }

    // 返回二分图的两个部分
    public Iterable<Integer>[] parts() {
        ArrayList<Integer>[] parts = new ArrayList[2];
        if(!isBipartition()) return parts;

        ArrayList<Integer> firstPart = new ArrayList<>();
        ArrayList<Integer> secondPart = new ArrayList<>();

        for(int v = 0; v < colors.length; v++) {
            if(colors[v] == 0) {
                firstPart.add(v);
            } else {   // colors[v] == 1
                secondPart.add(v);
            }
        }
        parts[0] = firstPart;
        parts[1] = secondPart;
        return parts;
    }

}
