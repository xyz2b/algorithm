package leetcode.p2024;

public class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        // 滑动窗口
        // 不断移动右端点，统计区间内另一种答案的个数sum，如果超过k，就移动左端点，直到sum<=k，然后再次移动右端点
        return Math.max(maxConsecutiveAnswersByTF(answerKey, k, 'T'), maxConsecutiveAnswersByTF(answerKey, k, 'F'));
    }

    private int maxConsecutiveAnswersByTF(String answerKey, int k, char tf) {
        int ret = 0;

        int left = 0;
        int right = 0;

        int sum = 0;
        while (right < answerKey.length()) {
            if(answerKey.charAt(right) == tf) {
                sum++;
            }

            while (sum > k && left <= right) {
                if(answerKey.charAt(left) == tf) {
                    sum--;
                }
                left++;
            }

            ret = Math.max(right - left + 1, ret);
            right++;
        }

        return ret;
    }
}
