package leetcode.p2244;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int minimumRounds(int[] tasks) {
        int ret = 0;

        Map<Integer, Integer> taskCounts = new HashMap<>();

        for(int i = 0; i < tasks.length; i++) {
            taskCounts.put(tasks[i], taskCounts.getOrDefault(tasks[i], 0) + 1);
        }


        for(int count : taskCounts.values()) {
            if(count % 3 == 0) {    // 按照最大可能性去做，最大一次可以做三个，如果任务数是三的倍数，就每次都做三个
                ret += (count / 3);
            } else if(count % 3 == 2) { // 如果最后剩下两个任务，那最后一次做两个任务即可
                ret += (count / 3 + 1);
            } else if (count % 3 == 1) {    // 如果最后剩下一个任务
                int step = d(count);
                if(step == -1) {
                    return -1;
                } else {
                    ret += step;
                }
            }
        }

        return ret;
    }

    // 记忆化搜索，本题可以记住之前所有遍历过的count计算出来的结果以及中间结果，以便后面计算别的count的时候使用
    // 爬台阶，一次只能爬2级或3级
    // f(n) = Math.min(f(n-2), f(n-3)) + 1
    Map<Integer, Integer> memo = new HashMap<>();

    // 自顶而下
    public int d(int count) {
        if(count == 0) {
            return 0;
        } else if(count == 1) {
            return -1;
        } else if(count < 0) {
            return -1;
        }

        if(memo.containsKey(count)) {
            return memo.get(count);
        }

        int ret = 0;
        if(d(count - 2) == -1 && d(count - 3) == -1) {
            ret = -1;
        } else if(d(count - 2) == -1) {
            ret = d(count - 3) + 1;
        } else if(d(count - 3) == -1) {
            ret = d(count - 2) + 1;
        } else {
            ret = Math.min(d(count - 2), d(count - 3)) + 1;
        }

        memo.put(count, ret);
        return ret;
    }

    // 贪心算法，贪心算法就是动态规划里的一个特例，得用数学逻辑证明它可以才行。不过贪心算法，次一点用动态规划也能解决（加记忆化搜索），就是次一点
    /*
    * 首先统计不同难度级别的任务各自出现的频率，然后对频率（≥1）进行分类讨论：
        频率是 1，说明这种任务无法完成。
        频率是 3×k，k 为 ≥1 的整数。每次完成 3 个，k 轮完成。
        频率是 3×k+2，k 为 ≥0 的整数。其中 3×k 个任务需要 k 轮完成，剩下 2 个任务需要 1 轮完成。
        频率是 3×k+1，k 为 ≥1 的整数。其中 3×(k−1) 个任务需要 (k−1) 轮完成，剩下 4 个任务需要 2 轮完成。
        对这些情况进行求和即可。
    * */
    public int minimumRounds2(int[] tasks) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int task : tasks) {
            cnt.put(task, cnt.getOrDefault(task, 0) + 1);
        }
        int res = 0;
        for (int v : cnt.values()) {
            if (v == 1) {
                return -1;
            }
            if (v % 3 == 0) {
                res += v / 3;
            } else {
                res += 1 + v / 3;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] tasks = new int[] {66,66,63,61,63,63,64,66,66,65,66,65,61,67,68,66,62,67,61,64,66,60,69,66,65,68,63,60,67,62,68,60,66,64,60,60,60,62,66,64,63,65,60,69,63,68,68,69,68,61};
        Solution solution = new Solution();
        System.out.println(solution.minimumRounds(tasks));
    }
}
