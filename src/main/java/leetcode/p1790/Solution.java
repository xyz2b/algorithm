package leetcode.p1790;

public class Solution {
    // 两个字符串有且仅有两个字符不同并且这两个不同的字符和另一个字符串中的两个字符是相同的 或者 本身完全相同
    public boolean areAlmostEqual(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        int count = -1;
        int[] differentS1 = new int[2];
        int[] differentS2 = new int[2];
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                count++;
                // s1和s2不相等的字符数已经超过2个了，不满足要求
                if(count > 1) {
                    return false;
                }
                differentS1[count] = s1.charAt(i);
                differentS2[count] = s2.charAt(i);

                // 如果s1和s2不相等的字符数已经达到2个，交叉对比是否相等（因为到这里已经说明differentS1[0] != differentS2[0] && differentS1[1] != differentS2[1]）
                if(count == 1) {
                    if(differentS1[1] != differentS2[0] || differentS1[0] != differentS2[1]) {
                        return false;
                    }
                }
            }
        }

        // 全相等 和 两个不相等（一个不相等是不满足要求的）
        return count == -1 || count == 1;
    }

    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "dcba";
        Solution solution = new Solution();
        System.out.println(solution.areAlmostEqual(s1, s2));
    }
}
