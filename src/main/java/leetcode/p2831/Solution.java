package leetcode.p2831;

import com.sun.source.tree.BreakTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        // 分组，将不同元素的索引统计出来
        Map<Integer, List<Integer>> poses = new HashMap<>();
        for(int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            poses.putIfAbsent(num, new ArrayList<>());
            poses.get(num).add(i);
        }

        int rst = 0;
        // 滑动窗口
        for(List<Integer> pos : poses.values()) {
            // 可以利用滑动窗口的思路，只需枚举区间的右端点 j, 当区间[pos[i], pos[j]] 需要删除的元素大于 k 时我们再移动 i, 直到区间需要删除的元素小于等于 k
            // 慢慢缩小范围，直到数组元素直接的间隔小于等于k
            // 左端点i 右端点j
            for(int j = 0, i = 0; j < pos.size(); j++) {
                // pos.get(j) - pos.get(i) + 1就是相同元素中间总共有多少个元素
                // j - i + 1就是该间隔中有多少个相同的元素（因为pos中都是相同元素的索引，所以pos的索引就可以计算某个区间内相同元素的个数）

                // 第k=pos.get(j)和第n=pos.get(i)，这两个相同元素中间有多少个不同的元素，计算方式 这中间总共有多少个元素 - 这中间相同的元素个数，即 (k - n + 1) - (j - i + 1)
                while ((pos.get(j) - pos.get(i) + 1) - (j - i + 1) > k) {
                    i++;
                }
                rst = Math.max(rst, j - i + 1);
            }
        }
        return rst;
    }
}
