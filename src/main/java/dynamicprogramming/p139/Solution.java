package dynamicprogramming.p139;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        // f(i) =  f(i - word.length()) + word.length() 条件：f(i-word.length()) != -1 && s.startsWith(word, i - word.length())
        // i为s的长度
        // f(s)为匹配到的长度
        dp[0] = 0;

        for(int i = 1; i <= s.length(); i++) {
            for(String word : wordDict) {
                if(i - word.length() >= 0) { // 提前结束，如果i还没有待匹配的字符那么长，那么就不需要进行判断
                    if(dp[i - word.length()] != -1 && s.startsWith(word, i - word.length())) {
                        dp[i] = dp[i - word.length()] + word.length();
                    }
                }
            }
        }

        return dp[s.length()] == s.length();
    }

    public static void main(String[] args) {
        String s = "dogs";
        List<String> wordDict = new ArrayList<>(Arrays.asList("dog", "s", "gs"));
        Solution solution = new Solution();
        System.out.println(solution.wordBreak(s, wordDict));
    }
}
