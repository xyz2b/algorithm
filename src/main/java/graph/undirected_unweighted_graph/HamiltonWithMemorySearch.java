package graph.undirected_unweighted_graph;

import java.util.Arrays;

public class HamiltonWithMemorySearch {
    private int[][] grid;
    private int R, C;

//    private boolean[][] visited;

    private int start, end;

    // 记住(visited, v)这个二元组对应的值
    // 该题中记忆的是 以v为顶点 且visited 所对应的 HamiltonPath 的数量
    private int[][] memo;

    private int[][] dirs = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        // 还有多少未访问的顶点，初始化为R*C
        int left = R * C;

        // visited可能的情况就是 2 ^ 顶点数
        // 顶点数 = R * C
        memo = new int[1 << (R * C)][R * C];
        for(int i = 0 ; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(grid[i][j] == 1) {
                    start = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) {
                    end = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    // 为-1的顶点为障碍不可访问
                    left--;
                }
            }
        }

        // 状态压缩，使用int的一位标识某个顶点是否已经访问过
        int visited = 0;
        return dfs(start, visited, left);
    }

    private int dfs(int v, int visited, int left) {
        // 二元组(visited, v)已经出现过了，说明之前遍历过了，直接返回即可
        if(memo[visited][v] != -1) return memo[visited][v];

        int x = v / C, y = v % C;
//        visited[x][y] = true;
        visited += (1 << v);
        left--;
        if(left == 0 && v == end) {
//            visited[x][y] = false;
            visited -= (1 << v);
            // 记忆住结果
            memo[visited][v] = 1;
            return 1;
        }

        int res = 0;
        for(int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            if(inArea(nextx, nexty) && grid[nextx][nexty] == 0 && (visited & (1 << v)) == 0 /* !visited[nextx][nexty] */) {
                int nextv = nextx * C + nexty;
                res += dfs(nextv, visited, left);
            }
        }

//        visited[x][y] = false;
        visited -= (1 << v);
        // 记忆住结果
        memo[visited][v] = res;
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
