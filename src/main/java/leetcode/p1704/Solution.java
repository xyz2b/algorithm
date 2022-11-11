package leetcode.p1704;

public class Solution {
    public boolean halvesAreAlike(String s) {
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        int[] vowelIndex = new int[256];
        for(int i = 0; i < vowels.length; i++) {
            vowelIndex[vowels[i] - 'A'] = 1;
        }

        int mid = s.length() / 2 - 1;
        int vowelNums = 0;
        for(int i = 0; i < s.length(); i++) {
            if(i <= mid) {
                if(vowelIndex[s.charAt(i) - 'A'] != 0) {
                    vowelNums++;
                }
            } else {
                if(vowelIndex[s.charAt(i) - 'A'] != 0) {
                    vowelNums--;
                }
            }
        }

        return vowelNums == 0;
    }

    public static void main(String[] args) {
        String s = "textbook";
        Solution solution = new Solution();
        System.out.println(solution.halvesAreAlike(s));
    }
}
