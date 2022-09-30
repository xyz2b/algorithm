package leetcode.interview0108;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    class Point {
        private int x;
        private int y;

        // hash表判断key是否相等，1.判断key的hashCode是否相等(重载hashCode)，2.判断key的值是否相同(重载equals)
        @Override
        public boolean equals(Object object) {
            if(object instanceof Point) {
                Point point = (Point) object;
                return point.x == x && point.y == y;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return (String.valueOf(x) + String.valueOf(y)).hashCode();
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void setZeroes(int[][] matrix) {
        // 存放已经访问过的节点的集合
        Set<Point> visited = new HashSet<>();
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                Point point = new Point(x, y);
                // 如果没访问过，就判断其是否等于0
                if(!visited.contains(point)) {
                    // 如果等于0，就将其上下左右一条线上的值都置为0
                    if(matrix[x][y] == 0) {
                        for(int i = 0; i < matrix.length; i++) {
                            // 如果本身这个节点就为0，就不需要处理，也不需要加入已访问过的节点的集合，因为之后还要处理。也可以在这里就处理了，但是这里没这么做
                            if(matrix[i][y] != 0) {
                                matrix[i][y] = 0;
                                visited.add(new Point(i, y));
                            }
                        }
                        for(int i = 0; i < matrix[0].length; i++) {
                            if(matrix[x][i] != 0) {
                                matrix[x][i] = 0;
                                visited.add(new Point(x, i));
                            }
                        }
                    }
                    visited.add(point);
                }
            }
        }
    }

    // 使用标记数组
    /**
     * 我们可以用两个标记数组分别记录每一行和每一列是否有零出现。
     *
     * 具体地，我们首先遍历该数组一次，如果某个元素为 0，那么就将该元素所在的行和列所对应标记数组的位置置为 true。最后我们再次遍历该数组，用标记数组更新原数组即可。
     *
     * */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    /**
     * 我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1) 的额外空间。但这样会导致原数组的第一行和第一列被修改，无法记录它们是否原本包含 0。
     *  因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含 0。
     *
     * 在实际代码中，我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，然后反过来使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可。
     *
     * */
    public void setZeros3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;

        // 判断第一行第一列是否有为0的元素
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }

        // 遍历除了第一行第一列的元素，如果发现有某个元素为0，就将其对应的那一行的第一列元素置为0，将其对应的那一列的第一行元素置为0，标识这一行这一列元素之后都要置为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // 根据第一行第一列的为0值，更新其他行其他列的值为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 处理第一行和第一列，如果第一行或第一列有为0的值，则第一行或第一列所有元素都为0
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
//        int[][] arrays = new int[][] {new int[]{0,1,2,0}, new int[]{3,4,5,2}, new int[]{1,3,1,5}};
        int[][] arrays = new int[][] {new int[]{1,1,1}, new int[]{1,0,1}, new int[]{1,1,1}};
        Solution solution = new Solution();
        solution.setZeroes(arrays);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < arrays.length; i++) {
            stringBuilder.append("[");
            for(int j = 0; j < arrays[0].length; j++) {
                stringBuilder.append(arrays[i][j] + ", ");
            }
            stringBuilder.append("]\n");
        }
        System.out.println(stringBuilder);
    }
}
