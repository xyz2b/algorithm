package leetcode.p1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> rst = new ArrayList<>();
        // 排序完之后，相邻的元素都挨在一起，最小的间隔都在相邻元素之间出现
        Arrays.sort(arr);

        // 最小间隔值
        int min = Integer.MAX_VALUE;
        // 一次遍历，判断相邻元素的间隔哪些是最小的
        for(int i = 0; i < arr.length - 1; i++) {
            int j = i + 1;

            int sub = arr[j] - arr[i];
            if(sub < min) { // 如果当前遍历的相邻元素的间隔小于当前最小间隔值，代表到目前为止找到的间隔最小的是当前结果，那就清空之前的结果集，将当前结果加入结果集，同时更新最小间隔值
                min = sub;
                rst.clear();
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i]);
                pair.add(arr[j]);
                rst.add(pair);
            } else if (sub == min) {    // 如果当前遍历的相邻元素的间隔等于当前最小间隔值，就将结果加入结果集
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i]);
                pair.add(arr[j]);
                rst.add(pair);
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4,2,1,3};
        Solution solution = new Solution();
        List<List<Integer>> rst = solution.minimumAbsDifference(arr);

        System.out.println(rst.toString());
    }
}
