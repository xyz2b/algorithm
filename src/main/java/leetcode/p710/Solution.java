package leetcode.p710;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

// 将黑名单列表之外的白名单区间识别出来并存储到TreeMap中，白名单区间是有序且无重复的
// 从白名单区间列表中取出起点最小的一个区间，作为当前随机值的区间，如果这个区间用完了，再取出当前区间的后继区间，直到取不到后继区间为止，此时就再取起点最小的区间，循环往复
public class Solution {
    private int n;
    // 当前随机数的值（不一定是可以返回的值，需要判断是否在白名单区间内）
    private int nextInt = 0;
    // 当前取出的白名单区间的起点
    private int start = 0;
    // 当前取出的白名单区间的终点（不包含）
    private int end = 0;

    // key为一个白名单区间的起点，value为一个白名单区间的终点（不包含）
    // intervals中包含了所有白名单区间
    // 为什么要存到TreeMap，因为白名单区间需要有序，方便我们取出，取出最小区间之后，下一个区间直接取当前区间的后继节点即可
    TreeMap<Integer, Integer> intervals = new TreeMap<>();

    public Solution(int n, int[] blacklist) {
        // 先排个序，blacklist无重复
        Arrays.sort(blacklist);
        this.n = n;

        // 找出所有白名单区间，存到TreeMap中
        int next = 0;
        for (int i = 0; i < blacklist.length; i++) {
            if (blacklist[i] == next) { // 如果黑名单值连续，就需要把它们合并剔除，因为白名单需要有序且无重复
                next++;
            } else {
                intervals.put(next, blacklist[i]);
                next = blacklist[i] + 1;
            }
        }

        if(next < n) {
            intervals.put(next, n);
        }

        // 获取一个白名单区间，作为当前获取随机值的区间
        Map.Entry<Integer, Integer> entry = intervals.firstEntry();
        if (entry != null) {
            start = entry.getKey();
            end = entry.getValue();
        } else {    // 表示黑名单列表为空，就一个白名单区间，就是[0..n)
            start = 0;
            end = n;
        }
        // 下一个随机Int的值，就是当前白名单区间的起始值
        nextInt = start;
    }

    public int pick() {
        // 如果当前随机数的值没有超过当前白名单区间，就直接返回即可，然后自增1，作为下一次的随机数值
        if (nextInt < end) {
            return nextInt++;
        }

        // 如果当前随机数的值超过了当前白名单区间，就取下一个白名单区间，然后返回下一个区间的start，nextInt = start + 1
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(start);   // 因为白名单区间是连续，所以直接取当前区间的后继节点即可
        if (entry != null) {
            start = entry.getKey();
            end = entry.getValue();
        } else {    // 如果当前白名单区间没有后继节点了
            Map.Entry<Integer, Integer> entryFirst = intervals.firstEntry();
            if (entryFirst != null) {   // 第一种情况，白名单区间取完了，那就从最小的区间再重新开始取
                start = entryFirst.getKey();
                end = entryFirst.getValue();
            } else {    // 第二种情况，没有黑名单的情况，那就直接再从0开始取
                start = 0;
                end = n;
            }
        }
        nextInt = start + 1;

        return start;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(3, new int[] {0});
        System.out.println(solution.pick()); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，0、1、4和6的返回概率必须相等(即概率为1/4)。
        System.out.println(solution.pick()); // 返回 4
        System.out.println(solution.pick()); // 返回 1
        System.out.println(solution.pick()); // 返回 6
        System.out.println(solution.pick()); // 返回 1
        System.out.println(solution.pick()); // 返回 0
        System.out.println(solution.pick()); // 返回 4
    }
}
