package leetcode.p324;

import java.util.Arrays;

// TODO: O(1)空间复杂度，未完成
public class Solution2 {
    public void wiggleSort(int[] nums) {
        // 先排个序，让大的元素都靠后
        Arrays.sort(nums);

        int length = nums.length;

        // 数组中奇数位置有几个
        int q = length / 2;

        // 从数组中从后往前数第q元素的位置
        int i = length - q;

        // 从数组中i元素开始往后，将其依次交换到数组奇数索引位置
        // 为啥要从第i个元素往后开始交换，为了避免被交换的元素和待交换的元素出现重叠
        for(int j = 1; j < length; j = j + 2, i++) {
            swap(nums, j, i);
        }
    }

    private void swap(int[] data, int src, int dst) {
        int temp = data[src];
        data[src] = data[dst];
        data[dst] = temp;
    }

    public String toString(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(data[i]).append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = {1,3,2,2,3,1};
        solution.wiggleSort(nums);
        System.out.println(solution.toString(nums));
    }
}
