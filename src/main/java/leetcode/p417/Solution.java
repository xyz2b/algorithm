package leetcode.p417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private int[][] heights;
    private boolean[][] visited;
    private int R;
    private int C;
    private List<List<Integer>> rst = new ArrayList<>();
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.R = heights.length;
        this.C = heights[0].length;
        this.visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(!visited[i][j]) {
                    // 判断从(i,j)出发是否能到达P海和A海
                    if(dfs(i, j, true) && dfs(i, j , false)){
                        rst.add(new ArrayList<>(Arrays.asList(i, j)));
                    }
                }
            }
        }

        return rst;
    }

    // pOA为true为找寻流向P海的可能，为false为找寻流向A海的可能
    // 从heights[x][y]开始，寻找是否能够到达P海或A海
    private boolean dfs(int x, int y, boolean pOA) {
        if(pOA) {
            if(canFlowToP(x, y)) return true;
        } else {
            if(canFlowToA(x, y)) return true;
        }

        visited[x][y] = true;
        for(int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            if(inArea(nextx, nexty) && !visited[nextx][nexty] && heights[nextx][nexty] <= heights[x][y]) {
                if(dfs(nextx, nexty, pOA)) {
                    // 这里也需要回溯，因为从另一个起点进行路径搜寻时，要确保visited数组中都为false
                    // 如果这里不进行回溯，那么递归函数返回时，visited数组中不都为false，没有回溯完全
                    // 找寻到的路径上的节点也都需要回溯为false
                    visited[x][y] = false;
                    return true;
                }
            }
        }
        visited[x][y] = false;
        return false;
    }


    private boolean canFlowToP(int x, int y) {
        return x == 0 || y == 0;
    }

    private boolean canFlowToA(int x, int y) {
        return x == R - 1 || y == C - 1;
    }

    public static void main(String[] args) {
//        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        int[][] heights = {{2,1},{1,2}};
        Solution solution = new Solution();
        System.out.println(solution.pacificAtlantic(heights));
    }
}
