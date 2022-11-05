package leetcode.p384;

import java.util.Arrays;
import java.util.Random;

// 洗牌算法
public class Solution {
    private Random rnd;
    private int[] nums;

    public Solution(int[] nums) {
        this.nums = nums.clone();
        this.rnd = new Random();
    }

    public int[] reset() {
        return nums.clone();
    }

    public int[] shuffle() {
        int[] rst = Arrays.copyOf(nums, nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            // 注意random是[0, i+1)，所以这里是i+1而不是i
            int choseIndex = rnd.nextInt(i + 1);
            swap(rst, choseIndex, i);
        }
        return rst;
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
