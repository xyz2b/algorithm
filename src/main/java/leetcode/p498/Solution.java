package leetcode.p498;

public class Solution {

    /**
     *  ----------> y
       | 1  2  3  4
       | 5  6  7  8
       | 9 10 11 12
       x
     * */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] rst = new int[m * n];

        int xMax = m - 1;   // 行索引x的最大值
        int yMax = n - 1;   // 列索引y的最大值

        int direction = 0; // 遍历方向，0 斜上方，1 斜下方
        int x = 0, y = 0;
        for(int i = 0; i < rst.length; i++) {   // 遍历的循环条件是所有元素
            // 每次访问一个对应索引的元素，添加到结果集中
            rst[i] = mat[x][y];

            // 根据遍历方向(direction)，以及是否达到边缘位置(x: 上下边缘、y: 左右边缘)，来决定下一次访问的索引(x, y)，以及下一次遍历的方向(direction)
            if (direction == 0) {   // 斜上方向遍历，每次行索引x减 1，列索引y加 1，直到矩阵的边缘为止（这种方向遍历，y可能到达右边缘，x可能达到上边缘）
                if (y + 1 > yMax) { // y已经遍历到右边缘，此时x不可能会遍历到下边缘，因为方向是斜上方向遍历，x是往上边缘移动的，所以此时x不可能会遍历到下边缘，所以里面逻辑在将x往下边缘移动前不需要判断x是否达到下边缘
                    x = x + 1;      // 将x往下边缘移动
                    direction = 1;  // 斜上方向遍历，已经遍历到边缘，下一次遍历的方向是斜下方
                    continue;
                } else if(x - 1 < 0) {  // x已经遍历到上边缘，y可能遍历到右边缘，也可以没到，所以里面需要判断
                    if (y + 1 > yMax) { // y已经遍历到右边缘
                        x = x + 1;
                    } else {            // y没有遍历到右边缘
                        y = y + 1;
                    }
                    direction = 1;  // 斜上方向遍历，已经遍历到边缘，下一次遍历的方向是斜下方
                    continue;
                }
                y = y + 1;
                x = x - 1;
            } else {    // 斜下方向遍历，每次行x索引加 1，列索引y减 1，直到矩阵的边缘为止（这种方向遍历，y可能到达左边缘，x可能达到下边缘）
                if (y - 1 < 0) {    // y遍历到左边缘，此时x可能遍历到下边缘，也可能没有，所以里面需要判断
                    if(x + 1 > xMax) { // x遍历到下边缘
                        y = y + 1;
                    } else {            // x没有遍历到下边缘
                        x = x + 1;
                    }
                    direction = 0;  // 斜下方向遍历，已经遍历到边缘，下一次遍历的方向是斜上方
                    continue;
                } else if (x + 1 > xMax) {  // x遍历到下边缘，此时y不可能会遍历到右边缘，因为方向是斜下方向遍历，y是往左边缘移动的，所以此时y不可能会遍历到右边缘，所以里面逻辑在将y往右边缘移动前不需要判断y是否达到右边缘
                    y = y + 1;      // 将y往右边缘移动
                    direction = 0;  // 斜下方向遍历，已经遍历到边缘，下一次遍历的方向是斜上方
                    continue;
                }

                y = y - 1;
                x = x + 1;
            }
        }

        return rst;
    }

    public String toString(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(data[i]).append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] mat = {{1, 2, 3}};
        int[] rst = solution.findDiagonalOrder(mat);
        System.out.println(solution.toString(rst));;
    }
}
