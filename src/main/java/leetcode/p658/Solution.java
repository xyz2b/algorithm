package leetcode.p658;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 滑动窗口
    // 因为是排完顺序的，后面和目标值的差距越小，说明前面和目标值的差距越大；后面和目标值的差距越大，说明前面和目标值的差距越小（因为是数组元素是递增）
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 窗口左边界
        int i = 0;
        // 窗口右边界
        int j = k - 1;

        // 始终保持窗口的大小为k，移动左右边界，从窗口后一个元素开始从左向右遍历数组元素
        for(int m = k; m < arr.length; m++) {
            // 当前遍历的元素 比 滑动窗口左边界的元素 与x的间隔要小，将窗口整体右移，将左边界间隔较大的元素踢出窗口，将当前遍历的元素加入到窗口中
            if (Math.abs(arr[m] - x) < Math.abs(arr[i] - x)) {
                i++;
                j++;
            } else if(arr[m] == arr[i]) {   // 但是如果当前遍历元素和左边界元素相同，即代表当前遍历元素和当前窗口中的元素都相同(因为是排完序的)，需要将窗口往前挪动一位，跳过最前面一个相同的元素，因为窗口只有这么大(跳过之后目前窗口中存放的也都是相同的元素)
                i++;
                j++;
            }
        }

        List<Integer> rst = new ArrayList<>(k);
        for(int n = i; n <= j; n++) {
            rst.add(arr[n]);
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {0,0,0,0,1,2,3,4,5,6};
        int[] arr1 = new int[] {0,0,1,2,3,3,3,4,7,7,8};
        int k = 3, x = 3;
        Solution solution = new Solution();
        List<Integer> rst = solution.findClosestElements(arr1, k, x);
        System.out.println(rst.toString());
    }
}
