package leetcode.p1034;

import java.util.ArrayDeque;
import java.util.Queue;

// 某个联通分量的边界
// 要么在整个gird的边界
// 要么顶点的上下左右不同色
public class SolutionBFS {
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
        bfs(row, col, vColor, color);

        return grid;
    }

    // [x,y]为顶点的联通分量，vColor是该联通分量的值，newColor是该联通分量的边界需要替换的值
    private void bfs(int x, int y, int vColor, int newColor) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;

        // 向该顶点的四个方向找寻
        // 这里其实相当于在遍历邻接顶点，只是这里还不能确定四周的点是否为邻接顶点，还需要判断其是否是邻接顶点(grid[x][y] == 1)
        while (!queue.isEmpty()) {
            int[] point = queue.remove();
            x = point[0];
            y = point[1];

            for(int d = 0; d < 4; d++) {
                int nextx = x + dirs[d][0];
                int nexty = y + dirs[d][1];

                // 某个联通分量的边界
                // 要么在整个gird的边界
                // 要么顶点的上下左右不同色
                if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == vColor) {
                    queue.add(new int[] {nextx, nexty});
                    visited[nextx][nexty] = true;
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
//        int[][] gird = {{1,1},{1,2}};
        int[][] gird = {{1,2,2},{2,3,2}};
//        int[][] gird = {{1,1,1},{1,1,1},{1,1,1}};
        int row = 0;
        int col = 1;
        int color = 3;
        SolutionBFS solution = new SolutionBFS();
        solution.colorBorder(gird, row, col, color);
        System.out.println(solution);
    }
}
