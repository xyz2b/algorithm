package leetcode.p2105;

public class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int ret = 0;
        int n = plants.length;

        int a = 0;
        int aLess = capacityA;

        int b = n - 1;
        int bLess = capacityB;


        while (a <= b) {
            if(a == b) {
                if(aLess < plants[a] && bLess < plants[b]) {
                    ret++;
                }
                break;
            }

            if(aLess < plants[a]) {
                aLess = capacityA - plants[a];
                ret++;
            } else {
                aLess = aLess - plants[a];
            }
            a++;

            if(bLess < plants[b]) {
                bLess = capacityB - plants[b];
                ret++;
            } else {
                bLess = bLess - plants[b];
            }
            b--;
        }

        return ret;
    }
}
