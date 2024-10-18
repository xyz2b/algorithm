package leetcode.p3191;

class Solution {
    public int minOperations(int[] nums) {
        // 通过不断的把数组中起始为0的三个元素进行翻转，从而实现1往左移，0往右移
        // 每次都找到起始为0的位置，然后翻转其后面的三个元素（包含这个0），然后再从该位置往后找起始为0的位置（这个位置为0已经翻转成了1，所以不可能为0）
        // 1 0 0 1 1 1 0 1 1 1
        //   |
        // 1 1 1 0 1 1 0 1 1 1
        //       |
        // 1 1 1 1 0 0 0 1 1 1
        //         |
        // 1 1 1 1 1 1 1 1 1 1

        int ret = 0;

        int index = 0;
        while (index < nums.length - 3) {
            if(nums[index] == 0) {
                for(int i = 0; i < 3; i++) {
                    nums[index + i] = (nums[index + i] == 0 ? 1 : 0);
                }
                ret++;
            }
            index++;
        }

        // 1.最后三个元素全为1，已经得到结果
        // 2.最后三个元素全为0，还要经过一次翻转，结果+1
        // 3.不全为0也不全为1，得不到结果
        int zeroCount = 0;
        for(int i = 0; i < 3; i++) {
            if(nums[index + i] == 0) {
                zeroCount++;
            }
        }

        if(zeroCount == 3) {
            return ret + 1;
        } else if(zeroCount == 0) {
            return ret;
        } else {
            return -1;
        }
    }
}
