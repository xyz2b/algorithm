package leetcode.p80;

public class Solution {
    public int removeDuplicates(int[] nums) {
        // 循环不变量：[0, k) 只多重复1次的字符顺序排列
        int k = 0;
        for(int i = 0; i < nums.length; i++) {
            // temp指向重复数字的第一个
            int temp = i;
            // 跳过所有相同的数字，此时i指向重复数字的最后一个，如果一个数字没有重复，则此时temp == i
            while(i + 1 < nums.length && nums[i + 1] == nums[temp]) {
                i++;
            }
            // 自己和自己不需要处理
            if(k != i) {
                if(temp != i) {  // 有出现大于1次的数字，保留2个
                    nums[k] = nums[temp];
                    k++;
                    nums[k] = nums[temp];
                    k++;
                } else {    // 只出现一次的数字，保留1个即可
                    nums[k] = nums[temp];
                    k++;
                }
            } else {
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 2, 2, 2, 3, 3};
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates(nums));
    }
}
