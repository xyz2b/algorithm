package leetcode.p219;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 滑动窗口 + 查找表
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // key为nums元素，value为该元素在nums数组中的索引
        Map<Integer, Integer> map = new HashMap<>();

        int l = 0, r = -1;
        while (l < nums.length) {
            if(r + 1 < nums.length && map.get(nums[r + 1]) == null) {   // 前面遍历过的数字不存在和r+1相同的数字，扩大窗口，移动r指针
                map.put(nums[r + 1], r + 1);
                r++;
            } else if (r + 1 < nums.length && map.get(nums[r + 1]) != null) {   // 前面遍历过的数字存在和r+1相同的数字
                int index = map.get(nums[r + 1]);
                if(Math.abs(r + 1 - index) <= k) {  // 满足题意
                    return true;
                } else {    // 两个相同的数字之间间距大于k，缩小窗口，移动l指针
                    // 更新这个数字的索引为当前的索引，即目前该数字出现的索引最大值
                    // （因为前面那个一样的数字和当前的数字索引差已经大于k了，后面再出现一样的数字和前面一样的数字的索引差只会更大，
                    //      所以前面那个数字就丢掉，更新该数字的索引为现在最最新的且是目前出现的最右边最大的索引）
                    map.put(nums[r + 1], r + 1);
                    // 更新左右指针
                    l++;
                    r++;    // 因为此时r+1索引处的数字是被遍历过了，所以需要也移动右指针
                }
            } else {    // r + 1 >= nums.length，r越界，缩小窗口，移动l指针
                l++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int k = 3;
        Solution solution = new Solution();
        System.out.println(solution.containsNearbyDuplicate(nums, k));
    }
}
