package leetcode.p565;

import java.util.HashMap;

public class Solution2 {
    public int arrayNesting(int[] nums) {
        // 将加入集合的元素的索引位置的值置为nums的长度

        int ans = 0, length = nums.length;
        for(int i = 0; i < length; i++) {
            int cnt = 0;

            // 因为nums数组的值一定在[0, length-1]之间
            // 所以用nums数组的元素值当做索引去访问数组一定不会越界
            // 又因为这里会将加入集合的元素在nums数组中对应位置的值设置为数组长度
            // 当遍历到已加入集合元素的时，它的值就是数组长度
            while (nums[i] < length) {
                int num = nums[i];
                nums[i] = length;
                i = num;
                cnt++;
            }
            // 为什么i不用恢复原值，因为遍历到最后i都会回到原值才会退出上面的循环
            // 比如从索引为0开始，最后肯定会遍历到索引0，然后退出循环，因为nums数组的值一定在[0, length-1]之间并且不重复

            ans = Math.max(cnt, ans);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] data = new int[] {5,4,0,3,1,6,2};
        System.out.println(solution.arrayNesting(data));
    }
}
