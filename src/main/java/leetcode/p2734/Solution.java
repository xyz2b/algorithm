package leetcode.p2734;

public class Solution {
    public String smallestString(String s) {
        StringBuilder sb = new StringBuilder();

        // 遇到第一个不是字符串开头的a，即其后面的字符都不需要变换
        boolean firstMeetNotStartA = false;
        boolean isAllA = true;
        int i = 0;
        while (i < s.length()) {
            if(i == 0 && s.charAt(i) == 'a') {
                sb.append(s.charAt(i));
                i++;
                // 略过开头所有的a
                while (i < s.length() && s.charAt(i) == 'a') {
                    sb.append(s.charAt(i));
                    i++;
                }
                continue;
            }

            if(s.charAt(i) != 'a') {
                if(!firstMeetNotStartA) {
                    sb.append((char)(s.charAt(i) - 1));
                } else {
                    sb.append(s.charAt(i));
                }
                isAllA = false;
            }

            if(s.charAt(i) == 'a') {
                firstMeetNotStartA = true;
                sb.append(s.charAt(i));
            }

            i++;
        }

        // 如果全是A
        if(isAllA) {
            sb.replace(sb.length() - 1, sb.length(), "z");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "leetcode";
        Solution solution = new Solution();
        System.out.println(solution.smallestString(s));
    }
}
