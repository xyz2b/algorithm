package leetcode.p768;

public class Solution {
    public String mergeAlternately(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();

        StringBuilder stringBuilder = new StringBuilder();
        int word1Index = 0;
        int word2Index = 0;
        while (word1Index < word1Length || word2Index < word2Length) {
            if(word1Index < word1Length) {
                stringBuilder.append(word1.charAt(word1Index));
                word1Index++;
            }

            if(word2Index < word2Length) {
                stringBuilder.append(word2.charAt(word2Index));
                word2Index++;
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String word1 = "abcd";
        String word2 = "pq";
        Solution solution = new Solution();
        System.out.println(solution.mergeAlternately(word1, word2));
    }
}
