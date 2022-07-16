package leetcode.offer041;

import java.math.BigDecimal;

public class MovingAverage {
    // 循环列表
    private int[] data;
    // 循环列表的元素个数
    private int dataLength;
    // 循环列表的头指针
    private int start;
    // 循环列表的尾指针
    private int end;

    private int winSize;
    // 缓存上一次窗口内数值的和
    private int sum;

    public MovingAverage(int size) {
        data = new int[size];
        dataLength = 0;
        start = end = 0;

        winSize = size;
        sum = 0;
    }

    public double next(int val) {
        if(dataLength >= winSize) {
            sum -= data[start];

            start = (start + 1) % winSize;
            dataLength--;
        }

        data[end] = val;
        end = (end + 1) % winSize;
        dataLength++;
        sum += val;

        BigDecimal bd = BigDecimal.valueOf((double) sum / (double) dataLength);
        return bd.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1)); // 返回 1.0 = 1 / 1
        System.out.println(movingAverage.next(10)); // 返回 5.5 = (1 + 10) / 2
        System.out.println(movingAverage.next(3));  // 返回 4.66667 = (1 + 10 + 3) / 3
        System.out.println(movingAverage.next(5));  // 返回 6.0 = (10 + 3 + 5) / 3
    }
}
