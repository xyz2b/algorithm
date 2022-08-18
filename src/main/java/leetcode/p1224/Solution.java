package leetcode.p1224;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxEqualFreq(int[] nums) {
        int rst = 0;

        // 统计nums中每个元素的数量
        Map<Integer, Integer> count = new HashMap<>();
        // 出现次数(key)相同的不同元素数量(value)
        Map<Integer, Integer> freq = new HashMap<>();
        // 相同元素最大出现次数
        int maxFreq = 0;
        for (int i = 0; i < nums.length; i++) {
            // 之前应记录过该数字，当前遍历时又出现了，就需要将该数字对应的次数加一(count)
            // 同时需要将该数字之前出现次数(key)对应的value减一(freq)，因为该数字出现次数多了一次，要记录到出现次数+1(key)对应的值中
            if (count.getOrDefault(nums[i], 0) > 0) {
                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
            }

            // 当前数字出现次数+1
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            // 更新 相同元素最大出现次数
            maxFreq = Math.max(maxFreq, count.get(nums[i]));
            // 出现次数(key)相同的不同元素数量(value)
            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);


            /**
             * 1.最大出现次数 maxFreq=1：那么所有数的出现次数都是一次，随意删除一个数既可符合要求。（maxFreq == 1）
             * 2.所有数的出现次数都是 maxFreq 或 maxFreq−1，并且最大出现次数的数只有一个（freq.get(maxFreq) == 1，出现次数为maxFreg的元素只有一个）：删除一个最大出现次数的数，那么所有数的出现次数都是 maxFreq−1。
             *      如何判断所有数的出现次数都是maxFreg或maxFreg-1，可以通过 出现这两类次数的元素数量总和是否等于当前遍历的元素总数量(i+1) 来判断
             *      因为freq中存储的是 出现次数(key)相同的不同元素数量(value)，所以取 freq.get(maxFreq) * maxFreq 就是出现次数为maxFreq的元素总数量，freq.get(maxFreq)是出现maxFreg次的不同元素数量，maxFreg是每个元素出现的次数，乘积就是出现次数为maxFreg的元素总数
             *      同理，freq.get(maxFreq - 1) * (maxFreq - 1) 就是出现次数为maxFreq-1的元素总数量
             * 3.除开一个数，其他所有数的出现次数都是 maxFreq，并且该数的出现次数为 1（freq.get(1) == 1，出现一次的元素只有一个）：直接删除出现次数为 1 的数，那么所有数的出现次数都是 maxFreq。
             *      如何判断除去一个数之后 所有数的出现次数都是maxFreg，即判断出现次数为maxFreg的元素总数量 为 (当前遍历元素总数 - 1 == i)，即(freq.get(maxFreq) * maxFreq == i
             * */
            boolean ok = maxFreq == 1 ||
                    ((freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1)) == (i + 1) && freq.get(maxFreq) == 1) ||
                    ((freq.get(maxFreq) * maxFreq == i) && freq.get(1) == 1);

            if (ok) {
                // i+1为当前遍历的元素总数
                rst = Math.max(rst, i + 1);
            }
        }
        return rst;
    }
}
