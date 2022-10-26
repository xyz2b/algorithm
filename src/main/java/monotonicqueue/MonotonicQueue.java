package monotonicqueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonotonicQueue {
    Deque<Integer> queue = new ArrayDeque<Integer>();

    // 删除 queue 中所有大于等于 nums[i] 的元素，这样每次加进去的元素都会是 queue 中的唯一最大值，
    // 使得 queue 中的元素是按照添加顺序严格单调递增的，我们知道单调队列是满足这样的性质的。
    public void add(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peekLast() >= nums[i]) {
                queue.pollLast();
            }
            queue.offerFirst(nums[i]);
        }
    }

    // 删除 queue 中所有大于等于 e 的元素，这样每次加进去的元素都会是 queue 中的唯一最大值，
    // 使得 queue 中的元素是按照添加顺序严格单调递增的，我们知道单调队列是满足这样的性质的。
    public void add(int e) {
        while (!queue.isEmpty() && queue.peekLast() >= e) {
            queue.pollLast();
        }
        queue.offerFirst(e);
    }

    public int poll() {
        if(queue.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("queue is empty");
        }
        return queue.pollFirst();
    }

    public int peek() {
        if(queue.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("queue is empty");
        }
        return queue.peekFirst();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
