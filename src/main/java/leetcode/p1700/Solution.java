package leetcode.p1700;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    /**
     * 假设喜欢吃圆形三明治的学生数量为 s0，喜欢吃方形三明治的学生数量为 s1。
     * 根据题意，我们可以知道栈顶的三明治能否被拿走取决于队列剩余的学生中是否有喜欢它的，因此学生在队列的相对位置不影响整个过程，我们只需要记录队列剩余的学生中 s0 和 s1 的值。
     * 我们对整个过程进行模拟，如果栈顶的元素为 0 并且 s0 > 0，我们将 s0 减 1；如果栈顶的元素为 1 并且 s1 > 0，我们将 s1 减 1；否则终止过程，并返回 s0 + s1
     * */
    public int countStudents(int[] students, int[] sandwiches) {
        int s1 = Arrays.stream(students).sum();
        int s0 = students.length - s1;

        for(int i = 0; i < sandwiches.length; i++) {
            if (sandwiches[i] == 0 && s0 > 0) { // s0等于0 说明已经没有学生喜欢吃0的三明治
                s0--;
            } else if(sandwiches[i] == 1 && s1 > 0) {   // s1等于0 说明已经没有学生喜欢吃1的三明治
                s1--;
            } else {    // s0 <= 0 && s1 <= 0 说明剩余的三明治没有学生喜欢吃
                break;
            }
        }
        return s0 + s1; // 返回剩余的学生即可
    }

    public int countStudents2(int[] students, int[] sandwiches) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = sandwiches.length - 1; i >= 0; i--) {
            stack.push(sandwiches[i]);
        }
        for(int i = 0; i < students.length; i++) {
            queue.offer(students[i]);
        }

        int flag = 0;
        while (!stack.isEmpty()) {
            if(stack.peek().equals(queue.peek())) {
                stack.pop();
                queue.poll();
                // 队列首的学生喜欢吃栈顶的三明治，将flag置0
                flag = 0;
            } else {
                // 队列首的学生不喜欢吃栈顶的三明治，flag++
                queue.offer(queue.poll());
                flag++;
            }

            // 说明剩余的学生轮了一圈没有一个喜欢吃栈顶的三明治
            if(flag == queue.size()) {
                return queue.size();
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] students = new int[] {1,1,1,0,0,1};
        int[] sandwiches = new int[] {1,0,0,0,1,1};
        Solution solution = new Solution();
        System.out.println(solution.countStudents(students, sandwiches));
    }
}
