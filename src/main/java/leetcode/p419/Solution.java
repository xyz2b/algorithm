package leetcode.p419;

class Solution {
    // 我们遍历矩阵中的每个位置 (i,j) 且满足 board[i][j]=’X’，并将以 (i,j) 为起点的战舰的所有位置均设置为空位，从而我们即可统计出所有可能的战舰。
    public int countBattleships(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    for (int k = j + 1; k < col && board[i][k] == 'X'; ++k) {
                        board[i][k] = '.';
                    }
                    for (int k = i + 1; k < row && board[k][j] == 'X'; ++k) {
                        board[k][j] = '.';
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 我们可以通过枚举每个战舰的左上顶点即可统计战舰的个数。假设矩阵的行数为 row，矩阵的列数 col，矩阵中的位置 (i,j) 为战舰的左上顶点，需满足以下条件：
     *  满足当前位置所在的值 board[i][j]=’X’；
     *  满足当前位置的左侧为空位，即board[i][j−1]=’.’；
     *  满足当前位置的上方为空位，即board[i−1][j]=’.’；
     * 我们统计出所有战舰的左上顶点的个数即为所有战舰的个数。
     * */
    public int countBattleships2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    ans++;
                }
            }
        }
        return ans;
    }
}
