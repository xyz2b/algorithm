package bucketsort;

// 通用的桶排序方法
// 将数据分到多个桶内，需要保证每个桶内的数据数量足够小才能更高效
// 每个桶存储一段数据范围的数据，从小到大依次递增
// 桶之间的顺序是排好的，桶内的数据是无序的，可以使用O(n^2)的排序算法进行排序

import java.util.Collections;
import java.util.LinkedList;

/**
 * 每个桶放c个元素，假设3个
 * nums: [2, 86, 32, 4, 3, 0, 33, 64, 58, 89, 22, 33, 6]
 * nums.length = 13
 * min: 0
 * max: 89
 * range: max - min + 1
 * 需要多少个桶: range / c + (range % c > 0 ? 1 : 0) = 30
 * 某个元素应该放到第几个桶中：(num - min) / c
 *
 * 桶1范围：0 - 2
 * 2 0
 * 桶2范围：3 - 5
 * 4 3
 * 桶3范围：6 - 8
 * 6
 * ......
 * */
public class BucketSort {

    // c是每个桶的大小
    public void sort(Integer[] nums, int c) {
        if(c <= 0) {
            throw new IllegalArgumentException("c must be > 0");
        }

        // 首先，需要计算 arr 中的数据范围
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        // arr中的数据范围
        int range = max - min + 1;

        // 根据数据范围 range 和每个桶中最多可容纳的元素，计算桶的个数 B
        int B = range / c + (range % c > 0 ? 1 : 0);    // 根据数据范围决定桶的个数

        // 创建桶
        LinkedList<Integer>[] buckets = new LinkedList[B];
        for(int i = 0; i < B; i++) {
            buckets[i] = new LinkedList<>();
        }

        // 将nums元素放到对应桶中
        for(int num : nums) {
            buckets[(num - min) / c].add(num);
        }

        // 对每个桶进行排序
        for(int i = 0; i < B; i++) {
            Collections.sort(buckets[i]);
        }

        // 将桶中的元素依次放回nums中
        int index = 0;
        for(int i = 0; i < B; i++) {
            for(int e : buckets[i]) {
                nums[index++] = e;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[] {2, 86, 32, 4, 3, 0, 33, 64, 58, 89, 22, 33, 6};
        int c = 3;
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort(nums, c);
        for(int e : nums) {
            System.out.println(e);
        }
    }
}
