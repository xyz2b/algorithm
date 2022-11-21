package leetcode.p785;

public class Solution {
    public boolean isBipartite(int[][] graph) {
        // 顶点数
        int V = graph.length;
        // colors数组即表示是否访问，又表示染色，-1表示未访问，0表示染成了蓝色，1表示染成了红色
        int[] colors = new int[V];
        for(int i = 0; i < V; i++) {
            colors[i] = -1;
        }
        for(int v = 0; v < V; v++) {
            if(colors[v] == -1) {
                if(!dfs(v, 0, colors, graph)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 在graph图中，从顶点v开始，判断图是否满足二分图的性质
    private boolean dfs(int v, int color, int[] colors, int[][] graph) {
        colors[v] = color;
        for(int w : graph[v]) {
            if(colors[w] == -1) { // 没被访问过，肯定也没被染色
                // 就将当前节点w染成和其上一个节点相反的颜色，因为颜色只有0、1，所以直接使用1-color取反
                if(!dfs(w, 1 - color, colors, graph))
                    return false;
            } else if (colors[w] == colors[v]) {    // 如果当前节点w的颜色和上一个节点v的颜色相同，表明不是二分图
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2,3},{0,2},{0,1,3},{0,2}};
        Solution solution = new Solution();
        System.out.println(solution.isBipartite(nums));
    }
}
