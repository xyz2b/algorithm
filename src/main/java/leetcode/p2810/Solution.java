package leetcode.p2810;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public String finalString(String s) {
        Deque<Character> deque = new ArrayDeque<>(s.length());

        // 当字符串进行反转后，在末尾添加字符等价于 不对字符串进行反转，并且在开头添加字符
        boolean head = false;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != 'i') {
                if(head) {
                    deque.addFirst(s.charAt(i));
                } else {
                    deque.addLast(s.charAt(i));
                }
            } else {
                head = !head;
            }
        }

        StringBuilder sb = new StringBuilder(s.length());
        if (head) {
            while (!deque.isEmpty()) {
                sb.append(deque.pollLast());
            }
        } else {
            while (!deque.isEmpty()) {
                sb.append(deque.pollFirst());
            }
        }

        return sb.toString();
    }
}