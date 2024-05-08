package leetcode.p2079;

public class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int ret = 0;
        int less = capacity;
        for(int i = 0; i < plants.length; i++) {
            if(less >= plants[i]) {
                less = less - plants[i];
                ret++;
            } else {
                ret += i;
                less = capacity;
                ret += (i + 1);
                less = less - plants[i];
            }
        }
        return ret;
    }
}
