package leetcode.p1582;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int numSpecial(int[][] mat) {
        int rst = 0;
        // 存放所有为1的元素的 行索引(x) 对应 列索引(y)的列表
        Map<Integer, List<Integer>> xy = new HashMap<>();
        // 存放所有为1的元素的 列索引(y) 对应 行索引(x)的列表
        Map<Integer, List<Integer>> yx = new HashMap<>();

        // 把元素为1的行列索引存入对应的map中
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 1) {
                    List<Integer> y = xy.get(i);
                    if(y == null) {
                        y = new ArrayList<>();
                        y.add(j);
                        xy.put(i, y);
                    } else {
                        y.add(j);
                    }

                    List<Integer> x = yx.get(j);
                    if(x == null) {
                        x = new ArrayList<>();
                        x.add(i);
                        yx.put(j, x);
                    } else {
                        x.add(i);
                    }
                }
            }
        }

        for(Map.Entry<Integer, List<Integer>> entry : xy.entrySet()) {
            // 如果当前遍历的 行索引对应的列索引只有一个，说明在同一行中，只有这个位置元素是1，没有其他元素为1了
            if(entry.getValue().size() == 1) {
                // 取得当前遍历行索引对应的那唯一一个列索引，其对应的行索引只有一个，说明在同一列中，只有这个位置元素是1，没有其他元素为1了
                // 同时，这个为1的元素的行索引和当前遍历的行索引一样
                if(yx.get(entry.getValue().get(0)).size() == 1 && yx.get(entry.getValue().get(0)).get(0).equals(entry.getKey())) {
                    rst++;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][] {new int[] {0,0,0,0,0}, new int[] {1,0,0,0,0}, new int[] {0,1,0,0,0}, new int[] {0,0,1,0,0}, new int[] {0,0,0,1,1}};
        Solution solution = new Solution();
        System.out.println(solution.numSpecial(mat));
    }
}
