package leetcode.p2813;

import java.util.*;


/**
 * 贪心
 * 首先将二维整数数组 items 按照 profit 从大到小进行排序。当子序列为前 kkk 个项目时，子序列的利润总和 total_profit 最大，但是 distinct_categories 不一定为最大。
 * 考虑第 k+1 个项目时，如果将它与已选的 k 个项目之一进行替换，那么显然只能使 distinct_categories 增加，是否替换有几种情况：
 *  - 如果 k+1 个项目与已选的 k 个项目的已有类别相同，那么选择它后，distinct_categories 不会增加，但是 total_profit 可能会减少，总体优雅度不会增加，所以不选择该项目。
 *  - 如果 k+1 个项目与已选的 k 个项目的已有类别都不相同，那么选择它后，对应的替换项目有两种情况：
 *    - 如果对应的替换项目的类别只出现一次，那么替换后，distinct_categories 不变，总体优雅度也不会增加，所以不选择该项目。
 *    - 如果对应的替换项目的类别出现两次以上，取利润最小的项目进行替换，那么替换后，distinct_categories 会增加，总体优雅度有可能增加，所以可以选择该项目。
 *      （如果现在不选择该项目，后续出现类似的情况时，因为利润是从大到小排序的，总体优雅度不会更大。）
 * 经过以上分类讨论后，我们知道每次考虑新增一个项目时，只有一种情况可能使总体优雅度更大。
 * 在求解过程中，我们可以使用栈来维护在已选的 kkk 个项目中，类别出现两次以上且利润非最大的所有项目，同时因为项目已经按照利润从大到小排序，
 * 所以栈顶元素为利润类别出现两次以上且利润最小的项目。求得以上所有可能的子序列的优雅度，取最大值为结果。
 * */
class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (item0, item1) -> item1[0] - item0[0]);
        Set<Integer> categorySet = new HashSet<Integer>();
        long profit = 0, res = 0;
        Deque<Integer> st = new ArrayDeque<Integer>();
        for (int i = 0; i < items.length; i++) {
            if (i < k) {
                profit += items[i][0];
                // 栈中只存放出现过两次及以上类别对应的非最大利润（最大利润是第一次加入set中的，后面set中存在该类别的利润都加入到栈中）
                if (!categorySet.add(items[i][1])) {
                    st.push(items[i][0]);
                }
            } else if (!st.isEmpty() && categorySet.add(items[i][1])) {
                profit += items[i][0] - st.pop();
            }
            res = Math.max(res, profit + (long)categorySet.size() * categorySet.size());
        }
        return res;
    }
}

