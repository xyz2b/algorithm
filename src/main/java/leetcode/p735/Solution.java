package leetcode.p735;

import java.util.Stack;

// 使用栈辅助
// 如果栈为空，就将当前遍历元素压入栈
// 当栈不为空时，遍历下一个元素时和栈顶元素比较 如果符号相反并且栈顶的元素是正数，就处理，然后处理完继续和栈顶比较，直到遇到符号相同的元素、栈空或栈顶元素是负数当前遍历元素是正数的情况，就将当前元素压入栈，继续遍历下一个元素
public class Solution {
    // 负 -- 正 不会相撞，负的往左，正的往右
    // 正 -- 负 才会相撞
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < asteroids.length;) {
            int asteroid = asteroids[i];
            if (stack.isEmpty()) {
                stack.push(asteroid);
                i++;
                continue;
            }

            int top = stack.peek();
            // 两个数异或，如果两个数符号相反（最高位不同），得到的值最高位会为1，小于0
            // 两行星反向
            if ((top ^ asteroid) < 0) {
                // 会相撞的情况下
                // 如果当前遍历行星爆炸，就遍历下一个行星和栈顶行星比较
                // 如果当前遍历行星不会爆炸，那就用当前遍历行星和栈顶行星继续比较
                if (top > 0) {  // 正(栈顶行星) -- 负(当前遍历的行星) 才会相撞
                    // 如果当前遍历的行星大于栈顶的行星，则将栈顶的行星弹出（爆炸），当前遍历行星不会爆炸。然后继续用当前遍历的行星 和 栈顶的行星做比较
                    if(Math.abs(asteroid) > Math.abs(top)) {
                        stack.pop();
                    } else if (Math.abs(asteroid) == Math.abs(top)) { // 两个行星质量相同，栈顶的行星弹出（爆炸）。当前遍历的行星也会爆炸，就遍历下一个行星，继续和栈顶行星比较
                        stack.pop();
                        i++;
                    } else {    // 如果当前遍历的行星小于栈顶的行星，就继续遍历下一个行星。因为栈顶行星大于它，栈顶行星不会爆炸。当前遍历的行星会爆炸，就遍历下一个行星，继续和栈顶行星比较
                        i++;
                    }
                } else {    // 负(栈顶行星) -- 正(当前遍历的行星) 不会相撞，不会相撞就将当前遍历的行星入栈
                    stack.push(asteroid);
                    i++;
                }
            } else {    // 两行星同向，直接入栈当前遍历行星
                stack.push(asteroid);
                i++;
            }
        }

        int rst[] = new int[stack.size()];
        int i = rst.length - 1;
        while (!stack.isEmpty()) {
            int top = stack.pop();
            rst[i] = top;
            i--;
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] asteroids = new int[] {8,-8};
        int[] rst = solution.asteroidCollision(asteroids);
        for(int i : rst) {
            System.out.println(i);
        }
    }
}
