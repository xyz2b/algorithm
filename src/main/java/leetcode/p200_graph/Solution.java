package leetcode.p200_graph;

// 岛屿数量就是图的联通分量个数
public class Solution {
    private boolean[][] visited;
    private char[][] grid;
    private int R;
    private int C;
    // 有可能题目中对搜索顺序比较敏感，比如要求顺时针，逆时针等，那么这里的dirs数组的元素要按照对应的搜索顺序进行设置
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        R = grid.length;
        if(R == 0) return 0;
        C = grid[0].length;
        if(C == 0) return 0;
        this.grid = grid;
        visited = new boolean[R][C];

        int ccCount = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    dfs(i ,j);
                    ccCount++;
                }
            }
        }

        return ccCount;
    }

    // 从grid[x][y]的位置开始，进行floodfill
    // 保证(x,y)合法，且grid[x][y]是没有被访问过的陆地，递归终止条件被融入了if语句中
    private void dfs(int x, int y) {
        visited[x][y] = true;

        for(int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == '1') {
                dfs(nextx, nexty);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
