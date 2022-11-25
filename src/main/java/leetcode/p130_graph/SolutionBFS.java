package leetcode.p130_graph;

import java.util.ArrayDeque;
import java.util.Queue;

// 同p1020，飞地，找到不挨着边界的联通分量，将其中的值都替换掉
public class SolutionBFS {
    private char[][] board;
    private int R;
    private int C;
    private boolean[][] visited;
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public void solve(char[][] board) {
        if(board == null) return;
        R = board.length;
        if(R == 0) return;
        C = board[0].length;
        if(C == 0) return;
        this.board = board;
        visited = new boolean[R][C];

        // 可能会挨着边界的顶点，分布于[x, 0]、[x, C-1]、[0, y]、[R-1, y]上
        // 可以先将这些边界的顶点的联通分量给遍历完，然后再去遍历非边界的顶点，剩下的联通分量就是飞地了
        for(int i = 0; i < R; i++) {
            if(!visited[i][0] && board[i][0] == 'O') {
                bfs(i, 0, false);
            }
            if(!visited[i][C-1] && board[i][C-1] == 'O') {
                bfs(i, C-1, false);
            }
        }
        for(int j = 1; j < C; j++) {
            if(!visited[0][j] && board[0][j] == 'O') {
                bfs(0, j, false);
            }
            if(!visited[R-1][j] && board[R-1][j] == 'O') {
                bfs(R-1, j, false);
            }
        }

        // 遍历非边界的顶点
        for(int i = 1; i < R-1; i++) {
            for (int j = 1; j < C-1; j++) {
                if(!visited[i][j] && board[i][j] == 'O') {
                    bfs(i, j, true);
                }
            }
        }
    }

    private void bfs(int x, int y, boolean isReplace) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        if(isReplace) {
            board[x][y] = 'X';
        }

        // 向该顶点的四个方向找寻
        // 这里其实相当于在遍历邻接顶点，只是这里还不能确定四周的点是否为邻接顶点，还需要判断其是否是邻接顶点(grid[x][y] == 1)
        while (!queue.isEmpty()) {
            int[] point = queue.remove();
            x = point[0];
            y = point[1];

            for(int d = 0; d < 4; d++) {
                int nextx = x + dirs[d][0];
                int nexty = y + dirs[d][1];
                if(inArea(nextx, nexty) && !visited[nextx][nexty] && board[nextx][nexty] == 'O') {
                    queue.add(new int[] {nextx, nexty});
                    visited[nextx][nexty] = true;
                    if(isReplace) {
                        board[nextx][nexty] = 'X';
                    }
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
        for(int i = 0; i < board.length; i++) {
            sb.append("[");
            for(int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
                if(j != board[0].length - 1) {
                    sb.append(", ");
                }
            }
            if(i != board.length - 1) {
                sb.append("]\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
//        char[][] boards = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        char[][] boards = {{'X'}};
        SolutionBFS solution = new SolutionBFS();
        solution.solve(boards);
        System.out.println(solution);
    }
}
