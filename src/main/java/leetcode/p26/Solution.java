package leetcode.p26;

class Solution {
    public int removeDuplicates(int[] nums) {
        // 循环不变量：[0, k) 不重复字符顺序排列
        int k = 0;
        for(int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            // 跳过所有相同的数字
            while(i + 1 < nums.length && nums[i+1] == temp) {
                i++;
            }
            // 自己和自己不需要处理
            if(k != i) {
                nums[k] = temp;
                k++;
            } else {
                k++;
            }
        }
        return k;
    }
}
