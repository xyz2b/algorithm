package leetcode.p324;

import java.util.Arrays;

// O(n)时间复杂度，O(n)空间复杂度
public class Solution {
    public void wiggleSort(int[] nums) {
        // 先用临时数组排个序，让大的元素都靠后
        int[] temp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(temp);

        int length = nums.length;

        // 从上面排序的数组最后往前，将比较大的元素，放到结果数组从头开始往后的所有奇数索引位置，所以此时第一个奇数索引位置的元素是数组中最大的元素
        // 因为题目要求就是奇数索引位置是比两边的偶数索引位置的元素要大
        int j = length - 1;
        for(int i = 1; i < length; i = i + 2, j--) {
            nums[i] = temp[j];
        }

        // 从排序数组中继续往前（比较大的元素已经被取出放在了奇数索引位置），将其他剩余元素，放到结果数组从头开始往后的所有偶数索引位置，所以此时第一个偶数索引位置的元素是除去奇数索引位置之外最大的元素
        // 因为上面放到结果数组中第一个奇数索引位置的元素是数组中最大的元素，所以这里放到结果数组中第一个偶数索引位置的元素一定比第一个奇数索引位置的元素小，
        // 第二个偶数索引位置的元素是小于等于第一个索引位置元素的，所以第二个偶数索引位置元素一定小于第一个奇数索引位置元素，
        // 以此类推
        for(int i = 0; i < length; i = i + 2, j--) {
            nums[i] = temp[j];
        }
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
        Solution solution = new Solution();
        int[] nums = {1,3,3,1,3,1};
        solution.wiggleSort(nums);
        System.out.println(solution.toString(nums));
    }
}
