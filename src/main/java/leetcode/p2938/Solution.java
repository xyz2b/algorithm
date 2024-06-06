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
}
