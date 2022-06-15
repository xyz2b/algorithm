package leetcode.p719;

import java.util.ArrayList;
import java.util.Comparator;

public class Solution3 {
    public int smallestDistancePair(int[] nums, int k) {
        int length = nums.length;

        ArrayList<Integer> rst =  new ArrayList<>();

        int numZero = 0;
        int numFirst = 1;
        for (int i = 0; i < length - 1; i ++) {
            for (int j = i + 1; j < length; j++) {
                int sub = Math.abs(nums[j] - nums[i]);
                if (sub == 0) {
                    numZero++;
                } else if (sub == 1) {
                    numFirst++;
                } else {
                    rst.add(sub);
                }
            }
        }

        rst.sort(Comparator.naturalOrder());

        if (k <= numZero) {
            return 0;
        } else if (k <= numZero + numFirst) {
            return 1;
        } else {
            return rst.get(k - numFirst - numZero);
        }
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] data = {0,2,2,1,1,1,0,2,2,0,1,2,2,1,2,1,1,0,0,0,1,0,0,2,2,0,1,0,1,1,1,1,0,2,0,1,2,2,0,0,2,1,2,0,2,2,0,0,1,2,2,2,1,2,0,0,0,2,2,0,2,1,2,2,1,0,2,2,1,1,1,2,0,2,2,0,0,1,0,2,0,1,1,1,1,1,1,1,2,0,2,2,2,1,1,2,2,0,1,2,1,2,0,2,0,0,2,2,0,0,1,0,2,2,1,0,2,1,0,2,2,2,0,1,0,2,1,0,2,0,1,1,2,0,0,1,1,2,2,0,2,1,2,1,2,2,1,0,0,0,0,0,2,0,1,2,0,0,0,2,1,2,2,0,2,2,0,0,1,0,0,0,2,0,2,1,0,0,0,2,1,0,1,0,2,1,2,2,0,0,2,0,2,1,0,2,1,1,0,0};
        int k = 10000;
        System.out.println(solution.smallestDistancePair(data, k));
    }
}
