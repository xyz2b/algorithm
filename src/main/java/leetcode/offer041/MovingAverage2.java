package leetcode.offer041;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Queue;

public class MovingAverage2 {
    // 队列
    private Queue<Integer> queue;

    private int winSize;
    // 缓存上一次窗口内数值的和
    private int sum;

    public MovingAverage2(int size) {
        queue = new ArrayDeque<>(size);
        winSize = size;
        sum = 0;
    }

    public double next(int val) {
        if(queue.size() >= winSize) {
            int v = queue.poll();
            sum -= v;
        }

        queue.offer(val);
        sum += val;

        BigDecimal bd = BigDecimal.valueOf((double) sum / (double) queue.size());
        return bd.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        MovingAverage2 movingAverage = new MovingAverage2(3);
        System.out.println(movingAverage.next(1)); // 返回 1.0 = 1 / 1
        System.out.println(movingAverage.next(10)); // 返回 5.5 = (1 + 10) / 2
        System.out.println(movingAverage.next(3));  // 返回 4.66667 = (1 + 10 + 3) / 3
        System.out.println(movingAverage.next(5));  // 返回 6.0 = (10 + 3 + 5) / 3
    }
}
