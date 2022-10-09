package leetcode.p856;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<Integer>();
        st.push(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(0);
            } else {    // ')'
                int cur = st.pop();
                int score = 0;
                if(cur == 0) {    // 当前')'的前一个元素是'('，得分1
                    score = 1;
                } else {    // 当前')'与其匹配的'('中间隔了其他字符，此时得分为中间字符串的得分*2
                    score = cur * 2;
                }

                // 不管前面是'('还是')'，我们都可以归结到 'X()' 的相邻项累加规则，将其新得分累加到栈顶元素上，其中 '(' 仍采用累加规则，则利用我们将 '(' 定义为 0 的设定
                int top = st.pop() + score;
                st.push(top);
            }
        }
        return st.peek();
    }
}
