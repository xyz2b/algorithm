package leetcode.p1475;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Solution {
    public int[] finalPrices(int[] prices) {
        int[] rst = new int[prices.length];

        for(int i = 0; i < prices.length - 1; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                if(prices[j] <= prices[i]) {
                    rst[i] = prices[i] - prices[j];
                    break;
                } else {
                    rst[i] = prices[i];
                }
            }
        }
        rst[prices.length - 1] = prices[prices.length - 1];
        return rst;
    }

    // 单调栈（最小栈，最小元素）
    // 从后往前遍历
    // 遇到之前遍历的后面元素 大于 当前遍历的前面元素时，表明当前遍历的前面的元素后面不是小于它的元素，将之前遍历的后面元素弹出栈
    // 遇到之前遍历的后面元素 小于等于 当前遍历的前面元素时，表明距离当前当前遍历的前面元素 距离最近的且小于它的元素就是之前遍历的后面元素，计算结果，并把当前遍历的前面元素压入栈
    public int[] finalPrices2(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for(int i : solution.finalPrices2(new int[]{8,7,4,2,8,1,7,7,10,1})) {
            System.out.println(i);
        }
    }
}
