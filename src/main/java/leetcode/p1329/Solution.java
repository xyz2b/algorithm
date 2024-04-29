package leetcode.p1329;

public class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for(int i = 0; i < m; i++) {
            insertSort(mat, new int[] {i, 0});
        }

        for(int j = 1; j < n; j++) {
            insertSort(mat, new int[] {0, j});
        }

        return mat;
    }

    void insertSort(int[][] mat, int[] start) {
        int m = mat.length;
        int n = mat[0].length;

        int[] nextIndex = next(start);
        while (!end(nextIndex, m, n)) {
            int[] preIndex = nextIndex;

            while (!end(preIndex, m, n)) {
                int[] prePreIndex = pre(preIndex);
                if(end(prePreIndex, m, n)) break;
                if(element(mat, prePreIndex) > element(mat, preIndex)) {
                    swap(mat, preIndex, prePreIndex
                    );
                }
                preIndex = pre(preIndex);
            }

            nextIndex = next(nextIndex);
        }
    }

    int element(int[][] mat, int[] index) {
        return mat[index[0]][index[1]];
    }

    void swap(int[][] mat, int[] index1, int[] index2) {
        int temp = mat[index1[0]][index1[1]];
        mat[index1[0]][index1[1]] = mat[index2[0]][index2[1]];
        mat[index2[0]][index2[1]] = temp;
    }

    int[] next(int[] index) {
        return new int[] {index[0] + 1, index[1] + 1};
    }

    int[] pre(int[] index) {
        return new int[] {index[0] - 1, index[1] - 1};
    }

    boolean end(int[] index, int m, int n) {
        return index[0] >= m || index[1] >= n || index[0] < 0 || index[1] < 0;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][] {{3,3,1,1},{2,2,1,2},{1,1,1,2}};
        int m = mat.length;
        int n = mat[0].length;
        Solution solution = new Solution();
        mat = solution.diagonalSort(mat);
        for(int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int j = 0; j < n; j++) {
                sb.append(mat[i][j]);
                sb.append(",");
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
    }

}
