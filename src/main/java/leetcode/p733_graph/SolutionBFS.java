package leetcode.p733_graph;

import java.util.ArrayDeque;
import java.util.Queue;

// 将image中[sr,sc]所在的联通分量的值都替换为color
public class SolutionBFS {
    private int[][] image;
    private int R;
    private int C;

    private boolean[][] visited;
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image == null) return null;
        R = image.length;
        if(R == 0) return null;
        C = image[0].length;
        if(C == 0) return null;
        this.image = image;
        visited = new boolean[R][C];

        int vColor = image[sr][sc];
        bfs(sr, sc, vColor, color);

        return image;
    }

    private void bfs(int x, int y, int vColor, int newColor) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        image[x][y] = newColor;

        // 向该顶点的四个方向找寻
        // 这里其实相当于在遍历邻接顶点，只是这里还不能确定四周的点是否为邻接顶点，还需要判断其是否是邻接顶点(grid[x][y] == 1)
        while (!queue.isEmpty()) {
            int[] point = queue.remove();
            x = point[0];
            y = point[1];

            for(int d = 0; d < 4; d++) {
                int nextx = x + dirs[d][0];
                int nexty = y + dirs[d][1];
                if(inArea(nextx, nexty) && !visited[nextx][nexty] && image[nextx][nexty] == vColor) {
                    queue.add(new int[] {nextx, nexty});
                    visited[nextx][nexty] = true;
                    image[nextx][nexty] = newColor;
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
        for(int i = 0; i < image.length; i++) {
            sb.append("[");
            for(int j = 0; j < image[0].length; j++) {
                sb.append(image[i][j]);
                if(j != image[0].length - 1) {
                    sb.append(", ");
                }
            }
            if(i != image.length - 1) {
                sb.append("]\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
//        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int[][] image = {{0,0,0},{0,0,0},{0,0,0}};
        int sr = 1;
        int sc = 1;
        int color = 2;
        SolutionBFS solution = new SolutionBFS();
        solution.floodFill(image, sr, sc, color);
        System.out.println(solution);
    }
}
