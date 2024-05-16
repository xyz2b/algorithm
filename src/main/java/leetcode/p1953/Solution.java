package leetcode.p1953;


/**
 * 贪心算法的最大难点在于怎么证明可以使用贪心算法
 * 根据 提示 1，我们首先计算出耗时最长的工作所需周数 longest 与剩余工作所需周数 rest，并比较两者大小。根据比较的结果不同会有两种情况：
 * longest≤rest+1，此时根据 提示 1，所有工作都可以完成，我们返回所有工作的总耗时 longest+rest 作为答案。
 * longest>rest+1，此时我们无法完成耗时最长的工作。根据 提示 1 的证明过程，耗时最长的工作最多可以完成 rest+1 周，因此最大的工作周数即为 2×rest+1，我们返回该数作为答案。
 * 最后，由于 rest 可能超过 32 位整数的范围，我们需要使用 64 位整数进行相应的计算与比较。
 * */
class Solution {
    public long numberOfWeeks(int[] milestones) {
        int longest = milestones[0]; // 耗时最长工作所需周数
        long rest = 0;
        for (int count : milestones) {
            longest = Math.max(longest, count);
            rest += count;
        }
        // 其余工作共计所需周数
        rest -= longest;
        if (longest > rest + 1) {
            // 此时无法完成所耗时最长的工作
            return rest * 2 + 1;
        } else {
            // 此时可以完成所有工作
            return longest + rest;
        }
    }
}
