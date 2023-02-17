package leetcode.p205;

public class Solution {
    // 双向映射
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        int[] sTt = new int[257];
        int[] tTs = new  int[257];
        for(int i = 0; i < s.length(); i++) {
            int sc = s.charAt(i) + 1;
            int tc = t.charAt(i) + 1;

            if(sTt[sc] != 0) {
                if(sTt[sc] != tc) {
                    return false;
                }

                if(tTs[tc] == 0) {
                    tTs[tc] = sc;
                }
            } else {
                if(tTs[tc] == 0) {
                    sTt[sc] = tc;
                    tTs[tc] = sc;
                    continue;
                }
                if(tTs[tc] != sc) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "foo";
        String t = "bar";
        Solution solution = new Solution();
        System.out.println(solution.isIsomorphic(s, t));
    }
}
