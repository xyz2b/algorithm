package leetcode.p2860;

import java.util.Collections;
import java.util.List;

class Solution {
    public int countWays(List<Integer> nums) {
        int ret = 0;
        Collections.sort(nums);

        // 从一个人都不选，逐步到选所有人
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
}