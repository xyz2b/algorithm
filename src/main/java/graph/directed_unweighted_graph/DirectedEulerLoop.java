package graph.directed_unweighted_graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

// 与无向图不同
/**
 * 欧拉回路
 * 从一个点出发，沿着边行走，经过每个边恰好一次，之后再回到出发点
 *
 * 欧拉回路存在的性质
 * 对于有向联通图
 * 每个点的出度和入度相同 <—> 图存在欧拉回路
 *
 * 欧拉路径
 * 从一个点出发，沿着边行走，经过每个边恰好一次，之后来到结束点
 * 结束点和起始点可以不一样
 *
 * 欧拉路径存在的性质
 * 对于有向联通图
 * 除了两个点之外每个点的出度与入度相同 <—> 图存在欧拉回路
 * 一个点是起始点，只需要有条路出去即可，出度比入度大1
 * 另一个点是终止点，只需要有条路进来即可，入度比出度大1
 *
 * 求欧拉路径也可以使用 Hierholzer 算法
 * 只不过起始点和终止点不能随意选择，需要选择出入度差值为1的两个点作为起始点(出度-入度=1)和终止点(入度-出度=1)，其他点的出度和入度都是相等
 *
 * */
public class DirectedEulerLoop {
    private Graph G;

    // 可以压缩状态，一条边使用两个顶点的组合数字来表示
    // 边是否访问
    private boolean[][] visited;
    // 上一条边
    private int[][][] pre;
    private int[] end;
    private int[] start;

    public DirectedEulerLoop(Graph G) {
        this.G = G;
    }

    public boolean hasEulerLoop() {
//        CCDFS cc = new CCDFS(G);
//        if(cc.count() > 1) return false;

        for(int v = 0; v < G.V(); v++) {
            if(G.inDegree(v) != G.outDegree(v)) return false;
        }
        return true;
    }

    // Hierholzer算法
    public Iterable<Integer> path() {
        ArrayList<Integer> path = new ArrayList<>();
        if(!hasEulerLoop()) return path;

        Graph g = (Graph) G.clone();

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            // 始终关注栈顶的点
            int curv = stack.peek();
            if(g.outDegree(curv) != 0) {   // 从 curv 点出发还有边没有走，就随便选一个边走，然后将这个边从图中删掉，将边的另一个点压入栈中
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                stack.push(w);
            } else {    // 从 curv 点出发所有边都走过了，那么就回退到上一个节点
                path.add(stack.pop());
            }
        }

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        DirectedEulerLoop el = new DirectedEulerLoop(g);
//        for(int[] p :el.path()) {
//            System.out.println("(" + p[0] + ", " + p[1] + ")");;
//        }
        System.out.println(el.path());

        Graph g2 = new Graph("ug2.txt", true);
        DirectedEulerLoop el2 = new DirectedEulerLoop(g2);
        System.out.println(el2.path());
    }
}
