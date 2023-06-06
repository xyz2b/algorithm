package dynamicprogramming.p139;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        // f(i) =  f(i - word.length()) + word.length() 条件：f(i-word.length()) != -1 && s.startsWith(word, i - word.length())
        // i为s从头开始待匹配的子串的长度
        // f(s)为s从头开始匹配到的子串的长度
        // 待匹配长度为0时，匹配到的长度也为0，初始值
        dp[0] = 0;

        for(int i = 1; i <= s.length(); i++) {
            for(String word : wordDict) {
                if(i - word.length() >= 0) { // 提前结束，如果i还没有待匹配的字符那么长，那么就不需要进行判断
                    if(dp[i - word.length()] != -1 && s.startsWith(word, i - word.length())) {  // s[0, i-word.length()-1]的子串已经被匹配到了，并且s[i-word.length(), i-1]子串匹配当前的word
                        // 则到目前为止，即到待匹配长度为i时，匹配到的子串长度为 s[0, i-word.length()-1] 已经匹配到的子串长度 + 当前匹配的word的长度（其实就是匹配到的长度就是i）
                        dp[i] = dp[i - word.length()] + word.length();
                        if(dp[i - word.length()] + word.length() != i) {
                            throw new RuntimeException("exception");
                        }
                    }
                }
            }
        }

        return dp[s.length()] == s.length();
    }

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        Solution solution = new Solution();
        System.out.println(solution.wordBreak(s, wordDict));
    }
}
