package leetcode.p130;

// 同p1020，飞地，找到不挨着边界的联通分量，将其中的值都替换掉
public class Solution {
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
                dfs(i, 0, false);
            }
            if(!visited[i][C-1] && board[i][C-1] == 'O') {
                dfs(i, C-1, false);
            }
        }
        for(int j = 1; j < C; j++) {
            if(!visited[0][j] && board[0][j] == 'O') {
                dfs(0, j, false);
            }
            if(!visited[R-1][j] && board[R-1][j] == 'O') {
                dfs(R-1, j, false);
            }
        }

        // 遍历非边界的顶点
        for(int i = 1; i < R-1; i++) {
            for (int j = 1; j < C-1; j++) {
                if(!visited[i][j] && board[i][j] == 'O') {
                    dfs(i, j, true);
                }
            }
        }
    }

    private void dfs(int x, int y, boolean isReplace) {
        visited[x][y] = true;
        if(isReplace) {
            board[x][y] = 'X';
        }
        for(int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && !visited[nextx][nexty] && board[nextx][nexty] == 'O') {
                dfs(nextx, nexty, isReplace);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public String toString(char[][] boards) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < boards.length; i++) {
            sb.append("[");
            for(int j = 0; j < boards[0].length; j++) {
                sb.append(boards[i][j]);
                if(j != boards[0].length - 1) {
                    sb.append(", ");
                }
            }
            if(i != boards.length - 1) {
                sb.append("]\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
//        char[][] boards = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        char[][] boards = {{'X'}};
        Solution solution = new Solution();
        solution.solve(boards);
        System.out.println(solution.toString(boards));
    }
}
