package leetcode.p1282;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {

        List<List<Integer>> rst = new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();

        // map的key是groupSizes中的元素（该元素就是之后分组的集合的大小），value是groupSizes中所有相同的数字对应的索引作为元素组成的数组
        // 即key就是之后分组的大小，value就是之后需要在这个分组大小集合内的索引
        for(int i = 0; i < groupSizes.length; i++) {
            List<Integer> group = map.get(groupSizes[i]);
            if (group == null) {
                group = new ArrayList<>();
                group.add(i);
                map.put(groupSizes[i], group);
            } else {
                group.add(i);
            }
        }

        // 按照map中的key的数字，对value进行个数切分（将value数组切分成多个key大小的集合）
        for(Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
            int count = e.getKey();
            List<Integer> index = e.getValue();

            int i = 0;
            while (i < index.size()) {
                List<Integer> indexes = new ArrayList<>();
                for(int j = 0; j < count; j ++) {
                    indexes.add(index.get(i));
                    i++;
                }
                rst.add(indexes);
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] data = new int[] {2,1,3,3,3,2};
        Solution solution = new Solution();
        System.out.println(solution.groupThePeople(data));
    }
}
