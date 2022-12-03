package leetcode.p1796;

import java.util.PriorityQueue;

public class Solution {
    public int secondHighest(String s) {
        int[] nums = new int[10];

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c >= '0' && c <= '9') {
                nums[c - '0'] = 1;
            }
        }

        int rst = -1;
        int index = 0;
        for(int i = 9 ; i >= 0 ; i--) {
            if(nums[i] == 1) {
                index++;
            }
            if(index == 2) {
                rst = i;
                break;
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.secondHighest("url15"));
    }
}
