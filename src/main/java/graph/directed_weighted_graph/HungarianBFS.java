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
public class HungarianBFS {
    private Graph G;
    private int maxMatching;
    // matching[a] = b，表示顶点a和顶点b式匹配的，反过来b和a也是匹配的，即matching[b] = a
    private int[] matching;

    public HungarianBFS(Graph G) {
        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartition()) {
            throw new IllegalArgumentException("HungarianBFS only works for bipartite graph");
        }
        this.G = G;

        this.matching = new int[G.V()];
        Arrays.fill(matching, -1);

        int[] colors = bd.colors();
        // 遍历左侧所有非匹配点
        for(int v = 0; v < G.V(); v++) {
            if (colors[v] == 1) {
                // bfs返回从左侧非匹配点v出发是否有增广路径
                if(bfs(v)) maxMatching++;
            }
        }
    }

    private boolean bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        // 记录路径
        int[] pre = new int[G.V()];
        Arrays.fill(pre, -1);

        queue.add(v);
        pre[v] = v;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 遍历cur所有相临节点，一定都是右侧的点
            for(int next: G.adj(cur)) {
                if(pre[next] == -1) {   // 未访问的右侧的点
                    // 已匹配的点
                    if(matching[next] != -1) {
                        // 记录路径
                        pre[next] = cur;
                        pre[matching[next]] = next;
                        // 将和next匹配的左侧的点加入到队列中，继续进行bfs
                        queue.add(matching[next]);
                    } else { // 未匹配的点，找到增广路径了
                        pre[next] = cur;
                        // 增广路径
                        // 遍历路径pre，起始点v，终止点next
                        ArrayList<Integer> augPath = getAugPath(pre, v, next);
                        // 修改匹配点
                        // 增广路径上的匹配点：0-1，2-3，4-5，6-7等
                        for(int i = 0; i < augPath.size(); i += 2) {
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                            return true;
                        }
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
        HungarianBFS hungarian = new HungarianBFS(g);
        System.out.println(hungarian.maxMatching());

        Graph g2 = new Graph("network3.txt", false);
        HungarianBFS hungarian2 = new HungarianBFS(g2);
        System.out.println(hungarian2.maxMatching());
    }
}
