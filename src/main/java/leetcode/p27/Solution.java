package leetcode.p27;

public class Solution {
    // 同p283，283是将0移动到数组末尾，这里是将val移动到数组末尾
    public int removeElement(int[] nums, int val) {
        // 在nums中，[0, k)是非val元素
        int k = 0;
        // 遍历到第i个元素后，保证[0...i]中所有非val元素都按照顺序排列在[0, k)中，同时[k...i]都为val
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                if(i != k) {
                    swap(nums, k, i);
                    k++;
                } else {
                    k++;
                }
            }
        }

        return k;
    }

    // 不保证顺序
    public int removeElement2(int[] nums, int val) {
        // 在nums中，(k, n-1]是val元素
        int k = nums.length - 1;
        // (i, k]都为非val元素
        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] == val) {
                if(i != k) {
                    swap(nums, k, i);
                    k--;
                } else {
                    k--;
                }
            }
        }

        return k + 1;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
