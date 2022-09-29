package leetcode.interview0109;

public class Solution {
    // 对于这种 一个字符串 能否 由另一个字符串循环移位得到 的问题， 都可以将某一个字符串拼接到自身后面，然后再判断另一个字符串是否是它的子串即可
    public boolean isFlipedString(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        if(s1.length() == 0 && s2.length() == 0) {
            return true;
        }

        String newString = s1 + s1;
        // 只需要遍历到newString还剩余有s2那么长的字符串时即可，不需要遍历到尾，之后的都不符合要求
        for(int i = 0; i <= newString.length() - s2.length(); i++) {
            // 找到newString中和s2中第一个字符相同的位置（和s2第一个字符不相同的位置都可以略过了）
            if(newString.charAt(i) != s2.charAt(0)) {
                i++;
                while (i < newString.length() && newString.charAt(i) != s2.charAt(0)) {
                    i++;
                }
            }

            // 如果此时得到相同的位置后面的字符数量，不足s2的长度，不符合要求
            if(i > newString.length() - s2.length()) {
                return false;
            }

            // 保存第一个字符相同的位置
            int first = i;

            // 判断newString后面的字符和s2后面的字符相不相同
            first++;
            boolean flag  = true;
            for(int j = 1; j < s2.length(); j++) {
                if(newString.charAt(first) != s2.charAt(j)) {
                    flag = false;
                    break;
                }
                first++;
            }
            if(flag) {
                return true;
            }
        }

        return false;

//        return newString.contains(s2);
//        return indexOf(newString.toCharArray(), 0, newString.length(), s2.toCharArray(), 0, s2.length(), 0) > -1;
    }

    // JDK contains的底层代码
    static int indexOf(char[] source, int sourceOffset, int sourceCount,
                       char[] target, int targetOffset, int targetCount,
                       int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }

    public boolean isFlipedString2(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        if(s1.length() == 0 && s2.length() == 0) {
            return true;
        }

        for(int i = 0; i < s1.length(); i++) {
            boolean flag = true;
            for(int j = 0; j < s2.length(); j++) {
                if(s1.charAt((i + j) % s2.length()) != s2.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println(solution.isFlipedString(s1, s2));
    }
}
