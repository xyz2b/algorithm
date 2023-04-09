package leetcode.p46;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            List<Integer> combination = new ArrayList<>();
            permute(nums, i, new int[nums.length], combination);
        }
        return rst;
    }

    // combination存储的是 nums 中某个数字开头的排列组合
    // 寻找以nums[index]开头的还未访问过的排列组合
    // visited表示nums中i位置的元素是否访问过
    public void permute(int[] nums, int index, int[] visited, List<Integer> combination) {
        List<Integer> r = new ArrayList<>(combination);
        r.add(nums[index]);
        visited[index] = 1;

        boolean allVisited = true;
        for(int i = 0; i < nums.length; i++) {
            if(visited[i] == 0) {
                permute(nums, i, visited, r);
                allVisited = false;
            }
        }
        if(allVisited && r.size() > 0) {
            rst.add(r);
        }
        visited[index] = 0;
    }


    private List<List<Integer>> rst = new ArrayList<>();
    private boolean[] used;
    public List<List<Integer>> permute2(int[] nums) {
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
            // nums索引为i的元素，暂时还没使用过，即不在p中
            if(!used[i]) {
                // 将nums[i]添加到p中
                p.add(nums[i]);
                used[i] = true;
                generatePermutation(nums, index + 1, p);
                // 递归函数传递参数自动会恢复到原样，但是有些其他设置的变量，如p和used，也必须恢复到原样（状态回溯）
                // 将p和used恢复到原样，回溯
                p.remove(p.size() - 1);
                used[i] = false;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        Solution solution = new Solution();
        System.out.println(solution.permute2(nums));
    }
}
