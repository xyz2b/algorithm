package leetcode.p2798;


import java.util.Arrays;
import java.util.Random;

public class Solution {
    // 直接遍历
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int ret = 0;

        for(int i : hours) {
            if(i >= target) {
                ret++;
            }
        }
        return ret;
    }

    public int numberOfEmployeesWhoMetTarget2(int[] hours, int target) {
        if(hours.length == 0) return 0;
        if(hours.length == 1) return hours[0] >= target ? 1 : 0;

        Arrays.sort(hours);

        int index = -1;
        for(int i = 1; i < hours.length; i++) {
            // 有等于的情况
            if(hours[i - 1] == target) {
                index = i - 1;
                break;
            }

            // 没有的等于的情况，在中间
            if(hours[i-1] < target && hours[i] >= target) {
                index = i;
                break;
            }

            // 全部大于等于
            if(hours[i - 1] >= target) {
                index = i - 1;
                break;
            }
        }

        if(index == -1) return 0;
        return hours.length - index;
    }




}
