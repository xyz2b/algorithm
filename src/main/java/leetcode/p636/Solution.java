package leetcode.p636;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] rst = new int[n];

        Stack<int[]> stack = new Stack<>();

        for(String log : logs) {
            String[] s = log.split(":");
            int func = Integer.parseInt(s[0]);
            int start = s[1].equals("start") ? 1 : 0;
            int timestamp = Integer.parseInt(s[2]);

            // 某一个func的start和end一定是成对的
            // 某一个func start之后，在其内又调用了了另一个func（start新的一个func）
            //      则被调用的func的end一定在调用其的func的end之前，不存在被调用的func在调用其的func之后执行完毕
            // 如果当前遍历的日志是start，就将其压入栈中，直到匹配到其对应的end才会弹出（类似括号匹配）
            if(start == 1) {    // start
                stack.push(new int[] {func, start, timestamp});
            } else {    // end， 匹配到对应的end，弹出其对应的start
                int[] startLog = stack.pop();

                // func执行时间 = end - start + 1
                rst[func] += timestamp - startLog[2] + 1;

                // 如果栈不为空，则弹出的func一定是在当前栈中的栈顶函数中被调用的，所以将弹出的func的执行时间从其调用函数的执行时间中去掉，即从此时栈顶函数的执行时间中去掉
                if (!stack.isEmpty()) {
                    int[] stackTop = stack.peek();
                    rst[stackTop[0]] -= timestamp - startLog[2] + 1;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        String[] data = {"0:start:0","1:start:2","1:end:5","0:end:6"};
        String[] data1 = {"0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"};
        String[] data2 = {"0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"};
        String[] data3 = {"0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"};
        String[] data4 = {"0:start:0","0:end:0"};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.exclusiveTime(1, Arrays.asList(data4))));
    }
}
