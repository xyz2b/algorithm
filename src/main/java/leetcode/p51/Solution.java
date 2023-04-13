package leetcode.p51;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<String>> rst = new ArrayList<>();
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;

    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];

        putQueen(n, 0, new ArrayList<>());

        return rst;
    }

    // 重点在这里
    // 每一行只能放置一个皇后
    // 不能同列，使用col[i]表示某一列是否已经有皇后了
    // 不能同对角线，对角线个数为2n-1。右上到左下的对角线，使用dia1[i+j]表示该对角线上是否已经有皇后了。左上到右下的对角线，使用dia2[i-j+n-1]表示该对角线上是否已经有皇后了

    // 尝试在一个N皇后问题中，摆放第index行的皇后位置，row数组值表示皇后放置的列号，索引表示皇后放置的行号
    private void putQueen(int n, int index, List<Integer> row) {
        if(n == index) {
            rst.add(generateBoard(n, row));
            return;
        }

        for(int i = 0; i < n; i++) {
            // 尝试将第index行的皇后摆放在第i列
            if(!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                row.add(i);
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                putQueen(n, index + 1, row);
                // 回溯
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.remove(row.size() - 1);
            }
        }
    }

    private List<String> generateBoard(int n, List<Integer> row) {
        List<String> rst = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int col = row.get(i);
            for(int j = 0; j < n; j++) {
                if(j == col) {
                    sb.append('Q');
                } else {
                    sb.append(".");
                }
            }
            rst.add(sb.toString());
        }
        return rst;
    }

    public static void main(String[] args) {
        int n = 4;
        Solution solution = new Solution();
        System.out.println(solution.solveNQueens(n));
    }
}
