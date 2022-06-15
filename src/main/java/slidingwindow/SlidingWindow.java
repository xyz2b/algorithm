package slidingwindow;

import java.util.Arrays;

public class SlidingWindow {
    /**
     * 3            6       8       10      12      16      不构成数对
     * left(right)
     *
     * 3            6       8       10      12      16      6和3构成距离小于等于5的数对，找到数对(3,6)，计数+1
     * left        right
     *
     * 3            6       8       10      12      16      8和3、6构成距离小于等于5的数对，找到数对(3,8)，(6,8)，计数+2
     * left                right
     *
     * 3            6       8       10      12      16      10和6、8构成距离小于等于5的数对，找到数对(6,10)，(8,10)，计数+2
     *              left           right
     *
     * 3            6       8       10      12      16      12和8、10构成距离小于等于5的数对，找到数对(8,12)，(10,12)，计数+2
     *                     left           right
     *
     * 3            6       8       10      12      16      16和12构成距离小于等于5的数对，找到数对(12,16)，计数+1
     *                                     left    right
     * */
    // 使用滑动窗口的方法计算nums中的数对差值小于等于target的数对个数
    public int smallerDistancePair(int[] nums, int threshold) {
        Arrays.sort(nums);

        int rst = 0;

        // left, right 为滑动窗口的左右边界
        // 如果 一个整数区间 里，「最小数」和「最大数」的差值为 k，那么这个整数区间里的 所有 数对的差值都小于等于 k。
        // nums已经有序，[left, right]中的 数对差值的 最大值 就是nums[right] - nums[left]，其他在[left, right]中的数对差值都小于等于nums[right] - nums[left]
        // 所以如果nums[right] - nums[left]已经大于target（需要小于等于target的数对），说明窗口需要滑动了，left需要自增1
        for (int left = 0, right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > threshold) {  // 窗口右边界和左边界的差值超出target，移动left
                left++;
            }

            rst += right - left;
        }

        return rst;
    }


    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        int[] data = {3, 6, 8, 10, 12, 16};
        int threshold = 5;
        System.out.println(slidingWindow.smallerDistancePair(data, threshold));
    }
}
