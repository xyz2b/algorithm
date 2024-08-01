package leetcode.lcp40;

import java.util.Arrays;

public class Solution {

    /**
     * 将 cards 从大到小排序后，先贪心的将后 cnt 个数字加起来，若此时 sum 为偶数，直接返回即可。
     * 若此时答案为奇数，有两种方案:
     *  在数组前面找到一个最大的奇数与后 cnt 个数中最小的偶数进行替换；
     *  在数组前面找到一个最大的偶数与后 cnt 个数中最小的奇数进行替换。
     * 两种方案选最大值即可。
     *
     * 优化：注意到 cards[i] 的取值范围为 [1,1000]，因此可以开辟一个 val 数组，val[k] 表示值为 k 的元素个数。将遍历 cards 改为遍历 val 即可，其他思路一致
     * */
    public int maxmiumScore(int[] cards, int cnt) {
        // 从大到小排序，尽量选取较大的数
        // 偶数+偶数==偶数 奇数+奇数==偶数，偶数+奇数==奇数

        // 排序
        Arrays.sort(cards);

        int ret = 0;

        int lastMinO = -1;
        int lastMinJ = -1;
        int k = cards.length - 1;
        for(; k >= 0 & cnt > 0; k--, cnt--) {
            if(cards[k] % 2 == 0) {
                lastMinO = k;
            } else {
                lastMinJ = k;
            }
            ret += cards[k];
        }

        if(ret % 2 != 0) {
            int beforeMaxO = -1;
            int beforeMaxJ = -1;
            for(int i = 0; i <= k; i++) {
                if(cards[i] % 2 == 0) {
                    beforeMaxO = i;
                } else {
                    beforeMaxJ = i;
                }
            }

            int tmp1 = 0;
            if(beforeMaxO != -1 && lastMinJ != -1) {
                tmp1 = ret - cards[lastMinJ] + cards[beforeMaxO];
            }
            int tmp2 = 0;
            if(beforeMaxJ != -1 && lastMinO != -1) {
                tmp2 = ret - cards[lastMinO] + cards[beforeMaxJ];
            }
            ret = Math.max(tmp1, tmp2);
        }

        return ret;
    }

    public int maxmiumScore2(int[] cards, int cnt) {
        // 计数排序
        int[] val = new int[1001];
        for(int i = 0; i < cards.length; i++) {
            val[cards[i]]++;
        }

        int ret = 0;

        int lastMinO = -1;
        int lastMinJ = -1;
        int k = val.length - 1;
        for(; k >= 1 & cnt > 0;) {
            if(val[k] == 0) {
                k--;
                continue;
            }

            if(k % 2 == 0) {
                lastMinO = k;
            } else {
                lastMinJ = k;
            }

            int cntK = val[k];
            if(cnt - cntK >= 0) {
                ret += (k * cntK);
                k--;    // 只有这个数全部取完了，下次才开始取下一个数，否则下次继续取这个数
            } else {
                ret += (k * cnt);
            }

            cnt = cnt - cntK;
        }

        if(ret % 2 != 0) {
            int beforeMaxO = -1;
            int beforeMaxJ = -1;
            for(int i = 1; i <= k; i++) {
                if(val[i] == 0) {
                    continue;
                }

                if(i % 2 == 0) {
                    beforeMaxO = i;
                } else {
                    beforeMaxJ = i;
                }
            }

            int tmp1 = 0;
            if(beforeMaxO != -1 && lastMinJ != -1) {
                tmp1 = ret - lastMinJ + beforeMaxO;
            }
            int tmp2 = 0;
            if(beforeMaxJ != -1 && lastMinO != -1) {
                tmp2 = ret - lastMinO + beforeMaxJ;
            }
            ret = Math.max(tmp1, tmp2);
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] cards = {1};
        int cnt = 1;
        Solution solution = new Solution();
        System.out.println(solution.maxmiumScore2(cards,cnt));
    }
}
