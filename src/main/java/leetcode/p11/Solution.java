package leetcode.p11;

public class Solution {
    /**
     * 我们需要移动一个指针。移动哪一个呢？直觉告诉我们，应该移动对应数字较小的那个指针。
     * 这是因为，由于容纳的水量是由 (两个指针指向的数字中较小值 * 指针之间的距离)决定的。
     * 如果我们移动数字较大的那个指针，那么前者「两个指针指向的数字中较小值」不会增加，后者「指针之间的距离」会减小，那么这个乘积会减小。
     * 因此，我们移动数字较大的那个指针是不合理的。因此，我们移动数字较小的那个指针
     * */
    public int maxArea(int[] height) {
        int rst = 0;

        int l = 0, r = height.length - 1;

        while (l < r) {
            if(height[l] < height[r]) {
                rst = Math.max(height[l] * (r - l), rst);
                l++;
            } else { // height[l] >= height[r]
                rst = Math.max(height[r] * (r - l), rst);
                r--;
            }
        }

        return rst;
    }
}
