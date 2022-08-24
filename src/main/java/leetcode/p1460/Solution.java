package leetcode.p1460;

import java.util.Arrays;

public class Solution {
    // 判断两个数组中的元素是否完全相同，完全相同则可以通过翻转子数组使两个数组相等，不完全相同则不能
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);

        for(int i = 0; i < target.length; i++) {
            if (target[i] != arr[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] target = new int[]{1,2,3,4};
        int[] arr = new int[]{2,4,1,3};

        Solution solution = new Solution();
        System.out.println(solution.canBeEqual(target, arr));
    }
}
