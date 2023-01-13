package leetcode.p215;

import java.util.PriorityQueue;
import java.util.Random;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        for(int i = k; i < nums.length; i++) {
            if(!pq.isEmpty() && pq.peek() < nums[i]) {
                // 如果是自实现的堆，可以使用replace方法，将堆的根节点(最小值)替换成nums[i]的值
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        return pq.peek();
    }


    private final Random random = new Random();
    // 快排
    public int findKthLargest2(int[] nums, int k) {
        int index = sort(nums, 0, nums.length - 1, k);
        return nums[index];
    }

    private int sort(int[] nums, int l, int r, int k) {
        // 随机选取标定点
        int index = random.nextInt(r - l + 1) + l;
        swap(nums, l, index);

        int p = partition2(nums, l, r);

        if(p == nums.length - k) {
            return p;
        } else if(p < nums.length - k){
            return sort(nums, p + 1, r, k);
        } else { // p > nums.length - k
            return sort(nums, l, p - 1, k);
        }
    }

    private int partition(int[] nums, int l, int r) {
        // [l + 1, k] < nums[0]
        // [k + 1, i - 1] >= nums[0]
        int k = l;
        for(int i = l + 1; i <= r; i++) {
            if(nums[i] < nums[l]) {
                k++;
                swap(nums, i, k);
            }
        }

        swap(nums, l, k);
        return k;
    }

    private int partition2(int[] nums, int l, int r) {
        // [l + 1, i) <= nums[l]
        int i = l + 1;
        // (j, r] >= nums[l]
        int j = r;

        while (true){
            while (i <= j && nums[i] < nums[l]) {
                i++;
            }

            while (i <= j && nums[j] > nums[l]) {
                j--;
            }

            if(i >= j) {
                break;
            }

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
        int[] nums = new int[] {3,2,1,5,6,4};
        int k = 2;
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest2(nums, k));
    }
}
