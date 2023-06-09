package dynamicprogramming.p376;

// 相比于p300，除了最长子序列的长度，还需要多记录一个值，即和前面的数字是升序还是降序
// 降序是0，升序是1，-1表示就该元素自己，后面的元素升序降序都可以
public class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length == 0) return 0;

        int[][] memo = new int[nums.length][2];
        for(int i = 0; i < nums.length; i++) {
            memo[i] = new int[] {1, -1};
        }

        for(int i = 1; i < nums.length; i++) {
            for(int j = i - 1; j >= 0; j--) {
                int[] m = memo[j];
                int length = m[0];
                int desc = m[1];

                if(desc == -1) {
                    if(nums[i] > nums[j]) {
                        if(length + 1 > memo[i][0]) {
                            memo[i] = new int[] {length + 1, 1};
                        }
                    } else if (nums[i] < nums[j]) {
                        if(length + 1 > memo[i][0]) {
                            memo[i] = new int[] {length + 1, 0};
                        }
                    }
                } else if(desc == 0) { // j和它前面一个元素是降序排列，i与j必须是升序排列
                    if(nums[i] > nums[j]) {
                        if(length + 1 > memo[i][0]) {
                            memo[i] = new int[] {length + 1, 1};
                        }
                    }
                } else {    // j和它前面一个元素是升序排列，i与j必须是降序排列
                    if(nums[i] < nums[j]) {
                        if(length + 1 > memo[i][0]) {
                            memo[i] = new int[] {length + 1, 0};
                        }
                    }
                }
            }
        }

        return memo[nums.length-1][0];
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,2,5};
        Solution solution = new Solution();
        System.out.println(solution.wiggleMaxLength(nums));
    }
}
