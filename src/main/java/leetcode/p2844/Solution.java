package leetcode.p2844;

class Solution {
    // 贪心
    /**
     * 一个数字要想被 25 整除，它需要满足以下条件：
     *     如果它的位数为 1，则它必须是 0。
     *     如果它的位数为 2，则它必须是 25，50 或者 75。
     *     如果它的位数为大于等于 3，则它必须以 00，25，50 或者 75结尾。
     * 我们需要将字符串 num 进行最少步数的操作，使其满足以上条件之一。题目又规定了 num 不包含前导 0，因此不可能存在经过最少步数的操作，使得 num 变为 00，所以这一特殊情况不在以上条件的讨论范围里。
     *
     * 我们从右至左遍历 num，
     *     如果遇到 0 或 5：
     *         如果在这之前遇到过 0，则将这之前的 0，当前的数字，以及当前的数字以左的数字都保留，其他的数字删除。记 num 长度为 n，当前下标为 i，最少操作数即为 n−i−2。
     *         如果在这之前没有遇到过 0，则标记一下状态，表示遇到了 0 或 5。
     *     如果遇到 2 或 7：
     *         如果在这之前遇到过 5，则将这之前的 5，当前的数字，以及当前的数字以左的数字都保留，其他的数字删除。记 num 长度为 n，当前下标为 i，最少操作数即为 n−i−2。
     * 如果遍历完都没有找到最少操作数，那么说明我们不可能通过操作来使得 num 变得以 00，25，50 或者 75结尾。
     *     如果我们在遍历中遇到了 0，那么我们就删除其他所有数字，只保留这个 0，返回 n−1。
     *     否则，我们将所有数字删除，返回 n。
     * */
    public int minimumOperations(String num) {
        int n = num.length();

        int seeZero = 0;
        int seeFive = 0;

        for(int i = n - 1; i >= 0; i--) {
            if(num.charAt(i) == '0' || num.charAt(i) == '5') {
                if(seeZero == 1) {
                    return n - i - 2;
                } else {
                    if(num.charAt(i) == '0') {
                        seeZero = 1;
                    }
                    if(num.charAt(i) == '5') {
                        seeFive = 1;
                    }
                }
            } else if(num.charAt(i) == '2' || num.charAt(i) == '7') {
                if(seeFive == 1) {
                    return n - i - 2;
                }
            }
        }

        if(seeZero == 1) {
            return n - 1;
        }
        return n;
    }
}