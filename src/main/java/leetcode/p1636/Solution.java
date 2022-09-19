package leetcode.p1636;

import java.util.*;

public class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer> list = new ArrayList<>(nums.length);
        for(int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }

        Collections.sort(list, (a, b) -> {
            int ca = map.get(a), cb = map.get(b);
            return ca == cb ? b - a : ca - cb;
        });

        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }

}
