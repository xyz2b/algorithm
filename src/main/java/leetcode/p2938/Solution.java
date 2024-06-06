package leetcode.p2938;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public long minimumSteps(String s) {
        // 找到最后一个零，以及这个零前面1的位置

        // 存放最后一个零前面1的位置
        List<Integer> oneIndex = new ArrayList<>();

        int lastZero = -1;
        for(int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == '0' && lastZero == -1) {
                lastZero = i;
            }
            if(c == '1' && lastZero != -1) {
                oneIndex.add(i);
            }
        }

        long ret = 0;
        // 最后一个零位置和前面1的位置索引差就是将1移动到最后一个零的位置的步数
        // 移动完之后，最后一个零的位置会往前走一步，因为最后一个零的位置被1占据了，零就会往前移动一步
        for(int i = 0; i < oneIndex.size(); i++) {
            ret += lastZero - oneIndex.get(i);
            lastZero--;
        }
        return ret;
    }

    /**
     * 贪心
     * 交换完后的最终状态一定是形如 00001111，那么遍历字符串的时候每碰到一个 0 就贪心的将其往左交换直到它最终的位置。
     * 在遍历到这个 0 时，因为它左边的 0 已经都交换到最终位置了，所以它的左边是一串连续的 1，那么只要加上遍历时碰到 1 的个数即可。
     * */
    public long minimumSteps1(String s) {
        long ans = 0;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                sum++;
            } else {
                ans += sum;
            }
        }
        return ans;
    }
}
