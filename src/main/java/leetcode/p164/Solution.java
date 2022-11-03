package leetcode.p164;

import java.util.Collections;
import java.util.LinkedList;

public class Solution {
    // 桶排序
    public int maximumGap(int[] nums) {
        if(nums.length <= 1) {
            return 0;
        }

        // 每个桶中的元素数量
        int c = 500;

        // 求元素的范围
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int e : nums) {
            max = Math.max(max, e);
            min = Math.min(min, e);
        }
        int range = max - min + 1;

        // 桶的数量
        int B = range / c + (range % c > 0 ? 1 : 0);

        // 创建桶
        LinkedList<Integer>[] buckets = new LinkedList[B];
        for(int i = 0; i < B; i++) {
            buckets[i] = new LinkedList<>();
        }

        // 将元素放到对应桶中
        for(int e : nums) {
            buckets[(e - min) / c].add(e);
        }

        // 对每个桶进行排序
        for(int i = 0; i < B; i++) {
            Collections.sort(buckets[i]);
        }

        // 将桶中的元素放回nums中
        int index = 0;
        for(int i = 0; i < B; i++) {
            for(int e : buckets[i]) {
                nums[index++] = e;
            }
        }

        // 遍历nums求相邻元素最大差值
        int rst = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            rst = Math.max(nums[i + 1] - nums[i], rst);
        }

        return rst;
    }
}
