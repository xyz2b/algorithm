package leetcode.p349;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> rst = new ArrayList<>();

        // HashSet会更快，增删改查是O(1)，但是HashSet会失去顺序性，但是TreeSet可以保持顺序性，同时保持增删改查O(logn)的时间复杂度
        /**
         * 数据顺序性
         * 1.数据集中的最大值和最小值
         * 2.某个元素的前驱和后继
         * 3.某个元素的floor和ceil
         * 4.某个元素的排位rank
         * 5.选择某个排位的元素select
         * */
        Set<Integer> record = new TreeSet<>();

        for(int num : nums1) {
            record.add(num);
        }

        for(int num : nums2) {
            if(record.contains(num)) {
                rst.add(num);
                record.remove(num);
            }
        }

        int[] r = new int[rst.size()];
        for(int i = 0; i < r.length; i++) {
            r[i] = rst.get(i);
        }
        return r;
    }
}
