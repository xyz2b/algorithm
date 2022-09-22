package leetcode.p1640;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        // key是arr中的元素，value是该元素在arr中的索引
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        // 计算pieces中元素的总数量
        int piecesLengths = 0;
        boolean flag = true;
        for(int i = 0; i < pieces.length; i++) {
            piecesLengths += pieces[i].length;
            int index = -1;
            for(int j = 0; j < pieces[i].length; j++) {
                if(index == -1 && map.containsKey(pieces[i][j])) {      // 遍历pieces[i]中的第一个元素，判断是否在arr中，以及在arr中的位置，遍历pieces[i]中下一个元素时，直接和arr中下一个位置的元素进行比较
                    index = map.get(pieces[i][j]);
                    continue;
                } else if(index == -1 && !map.containsKey(pieces[i][j])) {    // pieces中存在arr中不存在的元素，无法链成arr，直接退出
                    flag = false;
                    break;
                }

                if(index + 1 >= arr.length) {   // 说明arr中的元素比pieces中的元素少，也无法将pieces链成arr，直接退出
                    flag = false;
                    break;
                }

                if(pieces[i][j] != arr[index + 1]) {    // pieces[i]中第一个元素之后的元素和arr中连续位置的元素存在不一致，直接退出
                    flag = false;
                    break;
                }

                index++;    // pieces[i]下一个元素和arr数组中紧挨着的下一个元素进行对比
            }

            if(!flag) {
                break;
            }
        }

        return flag && map.size() == piecesLengths; // arr长度不等于pieces中所有元素的个数也是不能满足条件的
    }

    public static void main(String[] args) {
        int[] arr = new int[] {91,4,64,78, 90};
        int[][] pieces = new int[][] {new int[]{91,4,64,78}, new int[]{90}};
        Solution solution = new Solution();
        System.out.println(solution.canFormArray(arr, pieces));
    }
}
