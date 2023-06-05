package dynamicprogramming.p474;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 最大子集长度
     * f(i, m, n) = max{f(i-1, m, n), f(i, m-zeros(strs), n-ones(strs))}
     *
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int strsLength = strs.length;

        int[][][] memo = new int[strsLength][m + 1][n + 1];

        Map<Integer, int[]> counts = new HashMap<>();
        for(int i = 0; i < strsLength; i++) {
            int zeros = 0;
            int ones = 0;
            String s = strs[i];
            for(int j = 0; j < s.length(); j++) {
                if(s.charAt(j) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            counts.putIfAbsent(i, new int[] {zeros, ones});
        }

        for(int mi = 0; mi <= m; mi++) {
            for(int ni = 0; ni <= n; ni++) {
                int[] zeroIndex = counts.get(0);
                int zeroIndexZeros = zeroIndex[0];
                int zeroIndexOnes = zeroIndex[1];
                if(mi >= zeroIndexZeros && ni >= zeroIndexOnes) {
                    memo[0][mi][ni] = 1;
                }
            }
        }

        for(int i = 1; i < strsLength; i++) {
            int[] zeroIndex = counts.get(i);
            int zeroIndexZeros = zeroIndex[0];
            int zeroIndexOnes = zeroIndex[1];
            for(int mi = 0; mi <= m; mi++) {
                for (int ni = 0; ni <= n; ni++) {
                    memo[i][mi][ni] = memo[i-1][mi][ni];
                    if(mi >= zeroIndexZeros && ni >= zeroIndexOnes) {
                        memo[i][mi][ni] = Math.max(memo[i][mi][ni], memo[i-1][mi-zeroIndexZeros][ni-zeroIndexOnes] + 1);
                    }
                }
            }
        }

        return memo[strsLength-1][m][n];
    }

    public static void main(String[] args) {
        String[] strs = {"10", "0", "1"};
        int m = 1;
        int n = 1;

        Solution solution = new Solution();
        System.out.println(solution.findMaxForm(strs, m, n));
    }
}
