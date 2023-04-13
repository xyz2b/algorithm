package leetcode.p52;

public class Solution {
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;

    public int totalNQueens(int n) {
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];

        return putQueen(n, 0);
    }

    // 重点在这里
    // 每一行只能放置一个皇后
    // 不能同列，使用col[y]表示某一列是否已经有皇后了
    // 不能同对角线，对角线个数为2n-1。右上到左下的对角线，使用dia1[x+y]表示该对角线上是否已经有皇后了。左上到右下的对角线，使用dia2[x-y+n-1]表示该对角线上是否已经有皇后了

    // 尝试在一个N皇后问题中，摆放第index行的皇后位置之后解的数量
    // 返回摆放index行到n-1行皇后位置之后符合要求的解的数量
    private int putQueen(int n, int index) {
        int count = 0;
        if(n == index) {
            count++;
            return count;
        }

        for(int i = 0; i < n; i++) {
            // 尝试将第index行的皇后摆放在第i列
            if(!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                count += putQueen(n, index + 1);
                // 回溯
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 4;
        Solution solution = new Solution();
        System.out.println(solution.totalNQueens(n));
    }
}

