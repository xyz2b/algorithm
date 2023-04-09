package leetcode.p47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> rst = new ArrayList<>();
    private boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        generatePermutation(nums, 0, new ArrayList<>());
        return rst;
    }

    // p中保存了一个有index个元素的排列
    // 向这个排列的末尾添加第index+1个元素，获得一个有index+1个元素的排列
    private void generatePermutation(int[] nums, int index, List<Integer> p) {
        if(index == nums.length) {
            // 如果直接添加p到rst中，添加的是个引用，递归过程中还会修改p，所以需要copy一份值
            List<Integer> r = new ArrayList<>(p);
            rst.add(r);
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            // 要解决重复问题，我们只要设定一个规则，保证在填第 index 个数的时候重复数字只会被填入一次即可。(连带，比如111，如果排列要三个1在一起的话，只能从第一个1开始往后按顺序填，而不能从第二个或者第三个1开始，然后再反过来填入第一个1，这样就不会重复)
            // 而在本题解中，我们选择对原数组排序，保证相同的数字都相邻，然后每次填入的数一定是这个数所在重复数集合中「从左往右第一个未被填过的数字」，即如下的判断条件：
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) {
                continue;
            }
            // 将nums[i]添加到p中
            p.add(nums[i]);
            used[i] = true;
            generatePermutation(nums, index + 1, p);
            // 递归函数传递参数自动会恢复到原样，但是有些其他设置的变量，如p和used，也必须恢复到原样（状态回溯）
            // 将p和used恢复到原样，回溯
            p.remove(p.size() - 1);
            used[i] = false;
        }
        return;
    }
}
