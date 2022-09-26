package leetcode.interview1719;

import java.util.Arrays;

public class Solution {
    public int[] missingTwo(int[] nums) {
        // 排序
        Arrays.sort(nums);

        int[] rst = new int[2];

        int num = 1;    // 当前遍历的位置元素预期的值
        int index = 0;  // 结果集的索引，只有0、1取值

        int i = 0;  // 遍历数组的索引
        while (i < nums.length) {
            if(nums[i] != num) {    // 如果当前遍历位置的元素和预期的值不同，就表明该数组中缺少了预期的值，将其保存起来，并将预期的值+1，继续比较此时预期的值与当前遍历位置的元素(i不变)
                rst[index] = num;
                index++;
            } else {    // 如果当前遍历位置的元素和预期的值相同，就表明该数组中有了预期的值，就将预期值+1，继续比较此时预期的值与数组中下一个位置的元素(i++)
                i++;
            }
            num++;
        }


        if(rst[0] == 0) { // 如果结果集中，两个元素都不存在，表明缺少的两个元素是最后两个元素（即数组中最后的元素+1，+2得到的数字）
            int n = nums[i - 1];
            rst[0] = n + 1;
            rst[1] = n + 2;
        } else if (rst[1] == 0) {   // 如果结果集中第二个元素不存在，就表明缺少的那个元素是最后一个元素（即数组中最后的元素+1得到的数字）
            int n = nums[i - 1];
            rst[1] = n + 1;
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,5};
        Solution solution = new Solution();
        for(int i : solution.missingTwo(nums)) {
            System.out.println(i);
        }
    }
}
