package leetcode.p79;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private char[][] board;
    private char[] word;
    private boolean[][] visited;
    private int R;
    private int C;

    private int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};


    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = new char[word.length()];
        word.getChars(0, word.length(), this.word, 0);
        R = board.length;
        C = board[0].length;
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(dfs(i, j, 0))
                    return true;
            }
        }
        return false;
    }

    // 从board[x][y]开始，寻找word[index,word.length()]
    private boolean dfs(int x, int y, int index) {
        char c = board[x][y];

        // 字符不等，直接返回false
        if(c != word[index]) {
            return false;
        }

        // 如果遍历到了word最后，所有字符都相等，返回true
        if(index == word.length - 1) {
            return true;
        }

        visited[x][y] = true;
        for(int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            // 在区域内，同时又没有被访问过
            if(inArea(nextx, nexty) && !visited[nextx][nexty]) {
                if(dfs(nextx, nexty, index + 1)) {
                    return true;
                }
            }
        }
        // 回溯
        // (x, y)此时需要回溯成未访问状态
        visited[x][y] = false;

        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        Solution solution = new Solution();
        System.out.println(solution.exist(board,word));
    }
}
