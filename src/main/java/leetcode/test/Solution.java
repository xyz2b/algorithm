package leetcode.test;

import leetcode.p206.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Solution {
    private Random random = new Random();

    // 双路快排
    public int findKthLargest(int[] nums, int k) {
        int index = sort(nums, 0, nums.length - 1, k);
        return nums[index];
    }

    private int sort(int[] nums, int l, int r, int k) {
        // 随机选取标定点
        int i = random.nextInt(r - l + 1) + l;
        swap(nums, l, i);

        int p = partition2(nums, l, r);
        if(p == nums.length - k) {
            return p;
        } else if(p < nums.length - k) {
            return sort(nums, p + 1, r, k);
        } else {  // p > nums.length - k
            return sort(nums, l, p - 1, k);
        }
    }

    private int partition2(int[] nums, int l, int r) {
        // 循环不变量
        // [l+1, j) <= nums[l]
        int i = l + 1;
        // (k, r] >= nums[l]
        int j = r;

        while (true) {
            while (i <= j && nums[i] < nums[l]) {
                i++;
            }

            while (i <= j && nums[j] > nums[l]) {
                j--;
            }

            if(i >= j) break;

            swap(nums, i, j);
            i++;
            j--;
        }

        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest(nums, k));

        // 5, 4
    }
}
