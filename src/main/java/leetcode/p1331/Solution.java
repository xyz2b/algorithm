package leetcode.p1331;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] temp = Arrays.copyOf(arr, arr.length);
        // 先排序
        Arrays.sort(temp);

        // map的key是数组中的元素，value是元素的序号
        // 如果遇到已经存在于map中的元素，就跳过
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        int index = 1;
        for(int i = 0; i < temp.length; i++) {
            if(!map.containsKey(temp[i])) {
                map.put(temp[i], index);
                index++;
            }
        }

        int[] rst = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            rst[i] = map.get(arr[i]);
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] arr = {37,12,28,9,100,56,80,5,12};
        Solution solution = new Solution();
        int[] rst = solution.arrayRankTransform(arr);
        for(int i : rst) {
            System.out.println(i);
        }
    }
}
