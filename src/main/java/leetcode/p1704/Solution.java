package leetcode.p1704;

public class Solution {
    public boolean halvesAreAlike(String s) {
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        int[] vowelIndex = new int[256];
        // 使用数组，做一个元音字母的映射，因为有大写字母所以直接开了ascii编码256个空间，方便后面查找一个字符是不是元音字母
        for(int i = 0; i < vowels.length; i++) {
            vowelIndex[vowels[i] - 'A'] = 1;
        }

        int mid = s.length() / 2 - 1;
        int vowelNums = 0;
        for(int i = 0; i < s.length(); i++) {
            if(i <= mid) {  // 统计前半部分的元音字母
                if(vowelIndex[s.charAt(i) - 'A'] != 0) {
                    vowelNums++;
                }
            } else {    // 后半部分出现元音字母，就把前半部分元音字母减去1
                if(vowelIndex[s.charAt(i) - 'A'] != 0) {
                    vowelNums--;
                }
            }
        }

        // 如果vowelNums结果为0，说明两边元音字母相同，>0说明左边大于右边，<0说明右边大于左边
        return vowelNums == 0;
    }

    public static void main(String[] args) {
        String s = "textbook";
        Solution solution = new Solution();
        System.out.println(solution.halvesAreAlike(s));
    }
}
