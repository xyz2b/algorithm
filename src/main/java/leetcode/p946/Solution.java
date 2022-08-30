package leetcode.p946;

import java.util.Stack;

public class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();

        int pushedIndex = 0;
        int poppedIndex = 0;

        while (poppedIndex < popped.length) {
            if(!stack.empty() && stack.peek() == popped[poppedIndex]) {
                stack.pop();
                poppedIndex++;
            } else {
                if(pushedIndex < pushed.length) {
                    stack.push(pushedIndex);
                    pushedIndex++;
                }
            }
        }

        return stack.isEmpty();
    }
}
