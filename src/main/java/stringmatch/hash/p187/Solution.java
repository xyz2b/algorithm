package stringmatch.hash.p187;

import java.util.*;

public class Solution {
    // 滚动hash
    // 第一个长度为10的字符串和第二个长度为10的字符串只有第一个字符(第一个字符串)和最后一个字符(第二个字符串)有差异
    // 每次去掉第一个字符，然后加上最后一个字符即可
    // 又由于字符只取4个值 A C G T，可以给它们编码，A->1 C->2 G->3 T->4，然后10个字符，就转换成10位的数值
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length() < 10) {
            return new ArrayList<>();
        }
        Map<Character, Integer> code = new HashMap<>();
        code.put('A', 1);
        code.put('C', 2);
        code.put('G', 3);
        code.put('T', 4);

        Set<String> ret = new HashSet<>();
        Set<Long> set = new HashSet<>();

        long hash = 0, ten9 = (long) 1e9;

        for(int i = 0; i < 9; i++) {
            hash = hash * 10 + code.get(s.charAt(i));
        }

        for(int i = 9; i < s.length(); i++) {
            hash = hash * 10 + code.get(s.charAt(i));

            if(set.contains(hash)) {
                ret.add(s.substring(i - 9, i + 1));
            } else {
                set.add(hash);
            }

            hash = hash - code.get(s.charAt(i - 9)) * ten9;
        }

        return new ArrayList<>(ret);
    }
}
