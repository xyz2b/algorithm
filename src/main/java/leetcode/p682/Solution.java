package leetcode.p682;

import java.util.Stack;

public class Solution {
    public int calPoints(String[] operations) {
        int ret = 0;

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < operations.length; i++) {
            if(operations[i].equals("C")) {
                ret -= stack.pop();
            } else {
                int curPoint = 0;
                if (operations[i].equals("+")) {
                    int p1 = stack.pop();
                    int p2 = stack.peek();
                    curPoint = p1 + p2;
                    stack.push(p1);
                } else if (operations[i].equals("D")) {
                    curPoint = stack.peek() * 2;
                } else {
                    curPoint = Integer.parseInt(operations[i]);
                }
                ret += curPoint;
                stack.push(curPoint);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String[] operations = {"36","28","70","65","C","+","33","-46","84","C"};
        Solution solution = new Solution();
        System.out.println(solution.calPoints(operations));
    }
}
