package leetcode.p349;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> rst = new ArrayList<>();

        // HashSet
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
