package leetcode.p71;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Solution {
    // 栈
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();

        // 分解出来目录名
        int index = 0;
        while (index < path.length()) {
            while (index < path.length() && path.charAt(index) == '/') {    // 丢弃/
                index++;
            }

            // 最后一个字符是/的情况，避免之后在stack中多压入一个""，导致最后多一个/
            if(index >= path.length()) {
                break;
            }

            StringBuilder sb = new StringBuilder();
            // 判断是"."还是".."还是普通字符串
            if(index < path.length() && path.charAt(index) == '.') {
                index++;
                if(index < path.length() && path.charAt(index) == '.') {
                    index++;

                    if(index < path.length() && path.charAt(index) == '/') { // ..
                        index++;
                        // ..，从栈中弹出前一个目录，丢弃，如果栈为空，则不做操作
                        if(!stack.isEmpty()) {
                            stack.pollLast();
                        }
                        continue;
                    } else if (index >= path.length()) {    // 结尾为..的情况
                        // ..，从栈中弹出前一个目录，丢弃，如果栈为空，则不做操作
                        if(!stack.isEmpty()) {
                            stack.pollLast();
                        }
                        break;
                    } else {
                        sb.append("..");
                    }

                } else if(index < path.length() && path.charAt(index) == '/') { // .
                    index++;
                    // .不做任何操作
                    continue;
                } else if (index >= path.length()) {    // 结尾为.的情况
                    // .不做任何操作
                    break;
                }else {
                    sb.append(".");
                }
            }

            while (index < path.length() && path.charAt(index) != '/') {
                sb.append(path.charAt(index));
                index++;
            }

            stack.offerLast(sb.toString());
        }

        // 最终路径为/的情况，此时stack是空的，不忘栈中压入一个空字符串，最后得到的字符串是空，而不是/
        if(stack.isEmpty()) {
            stack.push("");
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.simplifyPath("/a//b////c/d//././/.."));
    }
}
