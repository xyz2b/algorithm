package leetcode.p1034_graph;

// 某个联通分量的边界
// 要么在整个gird的边界
// 要么顶点的上下左右不同色
public class SolutionDFS {
    private int[][] grid;
    private int R;
    private int C;
    private boolean[][] visited;
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        if(grid == null) return null;
        R = grid.length;
        if(R == 0) return null;
        C = grid[0].length;
        if(C == 0) return null;
        this.grid = grid;
        visited = new boolean[R][C];

        int vColor = grid[row][col];
        dfs(row, col, vColor, color);

        return grid;
    }

    // [x,y]为顶点的联通分量，vColor是该联通分量的值，newColor是该联通分量的边界需要替换的值
    private void dfs(int x, int y, int vColor, int newColor) {
        visited[x][y] = true;

        for(int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            // 某个联通分量的边界
            // 要么在整个gird的边界
            // 要么顶点的上下左右不同色
            if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == vColor) {
                dfs(nextx, nexty, vColor, newColor);
            } else if (!inArea(nextx, nexty)) { // [x, y]在整个gird的边界，所以[x,y]的上下左右有某个方向的[nextx, nexty]越界
                grid[x][y] = newColor;
            } else if (grid[nextx][nexty] != vColor && !visited[nextx][nexty]) {    // [x,y]的上下左右不同色，所以[x,y]的上下左右有某个方向的[nextx, nexty]和[x,y]不同颜色。需要注意是未访问的节点，因为已访问的节点可能已经被染色了
                /**
                 * 比如
                 * [1,1,1]
                 * [1,1,1]
                 * [1,1,1]
                 * row = 1
                 * col = 1
                 * color = 2
                 * 当处理完[1,1]一个方向之后，它这个方向上的节点就会被染色
                 * 当再处理[1,1]另一个方向时，就会发现[1,1]和它上下左右不同色了，因为上下左右有某个已经染色的节点
                 * */
                grid[x][y] = newColor;
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < grid.length; i++) {
            sb.append("[");
            for(int j = 0; j < grid[0].length; j++) {
                sb.append(grid[i][j]);
                if(j != grid[0].length - 1) {
                    sb.append(", ");
                }
            }
            if(i != grid.length - 1) {
                sb.append("]\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] gird = {{1,1},{1,2}};
//        int[][] gird = {{1,3,3},{2,3,3}};
//        int[][] gird = {{1,1,1},{1,1,1},{1,1,1}};
        int row = 0;
        int col = 0;
        int color = 3;
        SolutionDFS solution = new SolutionDFS();
        solution.colorBorder(gird, row, col, color);
        System.out.println(solution);
    }
}
