package greedy.p392;

public class Solution {
    public boolean isSubsequence(String s, String t) {
        int si = 0;
        int ti = 0;
        while (si < s.length() && ti < t.length()) {
            if(s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
            } else {
                ti++;
            }
        }

        return si == s.length();
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        Solution solution = new Solution();
        System.out.println(solution.isSubsequence(s, t));
    }
}
