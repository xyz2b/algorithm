package leetcode.p3185;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // hash表法：同两数之和，用map统计遍历的数字出现的次数，然后对于遍历的每个数，使用target减去它，看余数是否出现在map中，出现就是满足题意
    public long countCompleteDayPairs(int[] hours) {
        // hours[i]+hours[j] 能够被 24 整除，只需 hours[i] 除以 24 的余数与 hours[j] 除以 24 的余数之和能够被 24 整除。
        // (hours[i] % 24 + hours[j] % 24) % 24 == 0
        // 等价于
        // (24 - hours[j] % 24) % 24 == hours[i] % 24
        // hash表法：使用一个长度为 24 的数组 cnt 记录每个余数的出现次数，从而快速查询能够与 hours[i] 成对的元素数量。
        int[] cnt = new int[24];
        long ret = 0;
        for(int j = 0; j < hours.length; j++) {
            ret += cnt[(24 - hours[j] % 24) % 24];
            cnt[hours[j] % 24]++;
        }
        return ret;
    }
}
