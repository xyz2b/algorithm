package leetcode.p79;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private char[][] board;
    private char[] word;
    private boolean[][] visited;
    private int R;
    private int C;

    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    private boolean rst;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = new char[word.length()];
        word.getChars(0, word.length(), this.word, 0);
        R = board.length;
        C = board[0].length;
        visited = new boolean[R][C];

        dfs(0, 0, new ArrayList<>());
        return rst;
    }

    // 返回board[x,y]
    private void dfs(int x, int y, List<Character> s) {
        visited[x][y] = true;
        char c = board[x][y];
        if(c == word[s.size()]) {
            s.add(board[x][y]);
        }

        for(int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];

            dfs(nextx, nexty, s);
        }

    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
