package leetcode.p2207;

public class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        // 统计字符串中的pattern[0]和pattern[1]
        // 最大值就以下两种情况
        // 1.在开头插入pattern[0]
        // 2.在结尾插入pattern[1]

        int p0c = 0;
        int p1c = 0;
        char p0 = pattern.charAt(0);
        char p1 = pattern.charAt(1);
        // 还要统计原有字符中有多少个符合pattern的，遇到p1时候，前面有多少个p0就有多少个符合的，然后把多个p1的结果累加即可
        long beforeInsert = 0;
        for(char c : text.toCharArray()) {
            // 统计时，先匹配p1，在匹配p2，不然遇到"rr"这种重复字符的会有问题。如果反过来第一个字符时就会导致beforeInsert+1，而此时字符只是出现过一次，还没出现第二次，不算符合条件
            if(c == p1) {
                beforeInsert += p0c;
                p1c++;
            }

            if(c == p0) {
                p0c++;
            }
        }

        // 在结尾插入pattern[1]可以获得p0c个符合条件的
        // 在开头插入pattern[0]可以获得p1c个符合条件的
        long insert = Math.max(p0c, p1c);

        // 在加上原本字符串中符合条件的
        return insert + beforeInsert;
    }

    public static void main(String[] args) {
        String text = "fwymvreuftzgrcrxczjacqovduqaiig";
        String pattern = "yy";
        Solution solution = new Solution();
        System.out.println(solution.maximumSubsequenceCount(text, pattern));
    }
}
