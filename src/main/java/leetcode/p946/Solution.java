package leetcode.p946;

import java.util.Stack;

public class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();

        int pushedIndex = 0;
        int poppedIndex = 0;

        while (poppedIndex < popped.length) {
            // 如果当前栈不为空，并且栈顶元素和popped数组当前poppedIndex索引元素一样，就出栈，poppedIndex++（因为popped是出栈元素集合）
            if(!stack.empty() && stack.peek() == popped[poppedIndex]) {
                stack.pop();
                poppedIndex++;
            } else {
                if(pushedIndex < pushed.length) {   // pushed还有元素时，当栈顶元素不是当前poppedIndex索引元素，就将pushed当前pushedIndex索引元素入栈（因为pushed是入栈元素集合）
                    stack.push(pushedIndex);
                    pushedIndex++;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushed = new int[] {1,2,3,4,5};
        int[] popped = new int[] {4,5,3,2,1};
        Solution solution = new Solution();
        System.out.println(solution.validateStackSequences(pushed, popped));
    }
}
