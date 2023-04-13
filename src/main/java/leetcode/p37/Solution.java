package leetcode.p37;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private char[][] board;
    private int R;

    // 第一维表示1-9哪个数字，第二维表示第几行 或 第几列 或第几个宫
    // 表示1-9的某个数字 在 board的第几行
    private boolean[][] row;
    // 表示1-9的某个数字 在 board的第几列
    private boolean[][] col;
    // 表示1-9的某个数字 在 board的第几个宫
    private boolean[][] palace;

    // 有没有找到解
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.R = board.length;

        row = new boolean[9][R];
        col = new boolean[9][R];
        // (R/3) * (R/3)个3*3的宫
        palace = new boolean[9][(R/3) * (R/3)];
        // 如何判断坐标(x,y)在哪个宫内（从左上开始第一个宫为0，右下结束最一个宫为(R/3)*(R/3)-1）： (x/3)*(R/3) + (y/3)

        // 先遍历一遍整个数组，将空白位置的索引找出来，同时标记已使用数字的位置
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < R; j++) {
                char c = board[i][j];
                if(c != '.') {
                    row[c - '1'][i] = true;
                    col[c - '1'][j] = true;
                    palace[c - '1'][(i/3)*(R/3) + (j/3)] = true;
                } else {
                    spaces.add(new int[]{i, j});
                }
            }
        }

        putNum(0);
    }

    // 尝试将数字1-9摆放在对应index的空白位置去（index是空白位置的索引，将空白位置的索引都放到了一个一维数组中了，就遍历这个一维数组即可遍历所有空白位置，而index就是这个一维数组的索引）
    private void putNum(int index) {
        // 遍历到了最后一个空白位置，找到解了
        if(index == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(index);
        int i = space[0], j = space[1];
        for(char c = '1'; c <= '9' && !valid; c++) {
            if(!row[c - '1'][i] && !col[c - '1'][j] && !palace[c - '1'][(i/3)*(R/3) + (j/3)]) {
                board[i][j] = c;
                row[c - '1'][i] = true;
                col[c - '1'][j] = true;
                palace[c - '1'][(i/3)*(R/3) + (j/3)] = true;
                putNum(index + 1);
                row[c - '1'][i] = false;
                col[c - '1'][j] = false;
                palace[c - '1'][(i/3)*(R/3) + (j/3)] = false;
//                board[i][j] = '.';
            }
        }

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
            sb.append("]");
            if(i != board.length - 1) {
                sb.append("\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        Solution solution = new Solution();
        solution.solveSudoku(board);
        System.out.println(solution);
    }
}
