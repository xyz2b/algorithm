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

    // 使用数组记录每种字符是否出现仍然需要 O(C) 的空间复杂度。由于字符集仅有 26 个，
    // 我们可以使用一个长度为 26 的二进制数字来表示字符集合，这个二进制数字使用 32 位带符号整型变量即可。
    public boolean checkIfPangram2(String sentence) {
        int letter = 0;

        for(int i = 0 ; i < sentence.length(); i++) {
            int c = sentence.charAt(i) - 'a';
            // 将对应索引位置上的二进制置为1，如果26个位置上都是1，则表明为全字母
            letter |= (1 << c);
        }

        // 26位全1: (1 << 26) - 1
        return letter == (1 << 26) - 1;
    }

    public static void main(String[] args) {
        String sentence = "leetcode";
        Solution solution = new Solution();
        System.out.println(solution.checkIfPangram(sentence));
    }
}
