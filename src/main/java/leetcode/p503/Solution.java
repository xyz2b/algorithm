package leetcode.p503;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// 单调栈
public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        // 可以把这个循环数组「拉直」，即复制该序列的前 n−1 个元素拼接在原序列的后面。这样我们就可以将这个新序列当作普通序列来处理
        int[] temp = new int[nums.length * 2 - 1];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = nums[i % nums.length];
        }

        // 单调栈
        int[] rst = new int[temp.length];
        Arrays.fill(rst, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < temp.length; i++) {
            // 如果遇到nums中的元素大于栈顶元素的，就将栈顶元素出栈，直到栈顶元素大于当前遍历的数组中的元素
            while (!stack.isEmpty() && temp[i] > temp[stack.peek()]) {
                // rst中存储的是 nums中索引为stack.pop()的元素 下一个 大于它的元素nums[i]
                rst[stack.pop()] = temp[i];
            }
            stack.push(i);
        }

        return Arrays.copyOf(rst, nums.length);
    }


    // 我们不需要显性地将该循环数组「拉直」，而只需要在处理时对下标取模即可。
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {5,4,3,2,1};
        Solution solution = new Solution();
        for(int i : solution.nextGreaterElements(nums)) {
            System.out.println(i);
        }
    }
}
