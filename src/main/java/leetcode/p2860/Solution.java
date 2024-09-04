package leetcode.p2860;

import java.util.Collections;
import java.util.List;

class Solution {
    public int countWays(List<Integer> nums) {
        int ret = 0;
        Collections.sort(nums);

        // 从一个人都不选，逐步到选所有人，k是选中的人数个数
        // 选中人数 要小于 没被选中 中的最小值，选中人数 要大于 被选中 中的最大值
        for(int selectNum = 0; selectNum <= nums.size(); selectNum++) {
            if(selectNum == 0) {    // 一个人都不选
                if(selectNum < nums.get(0)) {
                    ret++;
                }
            }

//            if(selectNum == 1) {    // 选一个人
//                for(int i = 0; i < nums.size(); i++) {
//                    int notSelectedMin = i == 0 ? nums.get(1) : nums.get(0);
//                    int selectedMax = nums.get(i);
//
//                    if(selectNum < notSelectedMin && selectNum > selectedMax) {
//                        ret++;
//                    }
//                }
//            }

            // 双指针，双指针中间的是被选中的学生
            if(selectNum > 0 && selectNum < nums.size()) { // 选一个人及以上，但是没有全部选
                int left = 0;
                int right = left + selectNum - 1;
                while (right < nums.size()) {
                    int notSelectedMin = left == 0 ? nums.get(right + 1) : nums.get(0);
                    int selectedMax = nums.get(right);
                    if(selectNum < notSelectedMin && selectNum > selectedMax) {
                        ret++;
                    } else if(selectNum <= selectedMax) {   // 如果选择的个数 小于等于 被选择的最大值，就没必要再往后推进了，因为后面的数值都比当前的大(排序过的)，往后移动，只会导致被选择的最大值越来越大
                        break;
                    } else { // selectNum >= notSelectedMin，如果选择的个数 大于等于 没被选择的最小值，也没必要往后推进了，因为没被选中的最小值，要么是索引为right + 1的位置(left等于0)，要么是索引为0的位置(left不等0)，往后推进，没被选中的最小值也不会变大
                        break;
                    }
                    left++;
                    right++;
                }
            }

            if(selectNum == nums.size()) { // 全部选
                if(selectNum > nums.get(nums.size() - 1)) {
                    ret++;
                }
            }

        }

        return ret;
    }


    /**
     * 根据题意可知，假设数组 nums 的长度为 n，此时设选中学生人数为 k，此时 k∈[0,n]，k 应满足如下：
     *     所有满足 nums[i]<k 的学生应被选中；
     *     所有满足 nums[i]>k 的学生不应被选中；
     *     不能存在 nums[i]=k 的学生；
     * 这意味着在确定当前已择中学生人数的前提下，则此时选择方案是唯一的，为方便判断，我们把 nums 从小到大排序。
     * 我们枚举选中的人数 k，由于 nums 已有序，此时最优分组一定是前 k 个学生被选中，剩余的 n−k 个学生不被选中，
     *  此时只需要检测选中的 k 个学生中的最大值是否满足小于 k，未被选中的学生中的最小值是否满足大于 k 即可，
     *  如果同时满足上述两个条件，则该分配方案可行，最终返回可行的方案计数即可，需要注意处理好边界 0 与 n。
     * */
    public int countWays2(List<Integer> nums) {
        int n = nums.size();
        int res = 0;
        Collections.sort(nums);
        // 从一个人都不选，逐步到选所有人，k是选中的人数个数
        for (int k = 0; k <= n; k++) {
            // 前 k 个元素的最大值是否小于 k（前k个元素就是被选择中的人，选中人数 要大于 被选中 中的最大值，如果不满足就是不符合条件）
            if (k > 0 && nums.get(k - 1) >= k) {
                continue;
            }
            // 后 n - k 个元素的最小值是否大于 k（后n-k个元素就是没被选中的，选中人数 要小于 没被选中 中的最小值，如果不满足就是不符合条件）
            if (k < n && nums.get(k) <= k) {
                continue;
            }
            res++;
        }
        return res;
    }

}