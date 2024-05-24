package leetcode.p1673;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.stream.IntStream;

public class Solution {
    // 单调栈
    public int[] mostCompetitive(int[] nums, int k) {
        // 如果栈里的元素个数和数组中剩余的元素个数之和大于k，说明还有元素可以继续删除，如果等于了，说明栈里+数组剩余的元素，最后只剩k个元素了，就是结果了
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < nums.length; i++) {
            // 记栈中的元素数目为 m，我们不断地进行操作直到不满足条件：如果 m>0 且 m+n−i>k 且单调栈的栈顶元素大于 nums[i]，
            // 那么说明栈顶元素可以被当前数字 nums[i] 替换，弹出单调栈的栈顶元素。
            while (!stack.isEmpty() && stack.size() + (n - i) > k && stack.peek() > nums[i]) {
                stack.pop();
            }

            stack.push(nums[i]);
        }

        while (stack.size() > k) {
            stack.pop();
        }

        int[] ret = new int[k];
        for(int i = k - 1; i >= 0; i--) {
            ret[i] = stack.pop();
        }
        return ret;
    }

    public int[] mostCompetitive_failed(int[] nums, int k) {
        int[] ret = new int[k];
        // [0, nums.length - k] 最小的元素
        // [minIndex + 1, nums.length - (k - 1)] 最小的元素


        int l = 0, r = nums.length - k;
        int index = 0;
        while (k > 0) {
            int minIndex = IntStream.range(l ,r + 1)
                    .reduce((i, j) -> nums[i] <= nums[j] ? i : j)
                    .getAsInt();
            ret[index] = nums[minIndex];

            k--;
            index++;
            l = minIndex + 1;
            r = nums.length - k;
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {3,5,2,6};
        int k = 2;
        Solution solution = new Solution();
        int[] ret = solution.mostCompetitive(nums, k);
        for(int i = 0; i < k; i++) {
            System.out.println(ret[i]);
        }
    }
}
