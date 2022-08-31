package leetcode.p946;

import java.util.Stack;

public class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();

        // pushedIndex处的元素表明此刻需要执行push操作压入栈的元素
        int pushedIndex = 0;
        // poppedIndex处的元素表明此刻需要执行pop操作从栈中出栈的元素
        // 如果此时栈顶元素不是poppedIndex处的元素，会有两种情况
        //      1.pushed如果还有元素没有入栈，即表明需要pop的元素还没有入栈，此时就继续将pushedIndex处的元素压入栈即可
        //      2.pushed中元素如果都入栈了，则表明需要pop的元素已经入栈，但是位置不对，此时就表明不可行，此题无解
        int poppedIndex = 0;

        while (poppedIndex < popped.length) {
            // 如果当前栈不为空，并且栈顶元素和popped数组当前poppedIndex索引元素一样(表明此时栈顶元素正好是popped要执行出栈操作的元素)，就执行出栈操作，poppedIndex++
            if(!stack.empty() && stack.peek() == popped[poppedIndex]) {
                stack.pop();
                poppedIndex++;
            } else {    // 如果此时栈顶元素不是poppedIndex处的元素
                if(pushedIndex < pushed.length) {   // pushedIndex < pushed.length，说明pushed还有元素没有入栈，即表明需要pop的元素还没有入栈，此时就继续将pushedIndex处的元素压入栈即可
                    stack.push(pushed[pushedIndex]);
                    pushedIndex++;
                } else {    // pushedIndex >= pushed.length，说明pushed中元素都入栈了，则表明需要pop的元素已经入栈，但是位置不对，此时就表明不可行，此题无解
                    break;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushed = new int[] {1,2,3,4,5};
        int[] popped = new int[] {4,3,5,1,2};
        Solution solution = new Solution();
        System.out.println(solution.validateStackSequences(pushed, popped));
    }
}
