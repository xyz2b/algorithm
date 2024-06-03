package leetcode.p575;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int distributeCandies(int[] candyType) {
        int ret = 0;
        int n = candyType.length;

        Set<Integer> cnt = new HashSet<>();

        for(int type : candyType) {
            cnt.add(type);
        }

        return Math.min(cnt.size(), n / 2);
    }
}
