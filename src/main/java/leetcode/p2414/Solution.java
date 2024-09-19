package leetcode.p2414;

public class Solution {
    public int longestContinuousSubstring(String s) {
        if(s.length() == 1) return 1;

        // 双指针
        int ret = 1;

        int l = 0;  // 连续字符串起始位置
        int r = 1;  // 连续字符串结束位置的后一个位置

        while (r < s.length()) {
            if(s.charAt(r) - s.charAt(r-1) == 1) {  // 连续，将r往前移动
                r++;
            } else {    // 不连续，统计之前连续的长度。然后从新开始，找连续的字符，即将l设置为当前不连续的那个字符的位置即r，然后将r往前移动
                ret = Math.max(ret, r - l);
                l = r;
                r++;
            }
        }

        ret = Math.max(ret, r - l);
        return ret;
    }

    public static void main(String[] args) {
        String s = "abcde";
        Solution solution = new Solution();
        System.out.println(solution.longestContinuousSubstring(s));
    }
}
