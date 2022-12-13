package leetcode.p1832;

public class Solution {
    public boolean checkIfPangram(String sentence) {
        int[] letter = new int[26];

        for(int i = 0 ; i < sentence.length(); i++) {
            int c = sentence.charAt(i) - 'a';
            letter[c]++;
        }

        for(int i = 0; i < letter.length; i++) {
            if(letter[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String sentence = "leetcode";
        Solution solution = new Solution();
        System.out.println(solution.checkIfPangram(sentence));
    }
}
