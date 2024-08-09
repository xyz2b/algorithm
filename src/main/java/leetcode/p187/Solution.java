package leetcode.p187;

import java.util.*;

public class Solution {
    // 暴力
    // hash表，遍历所有10长度的字符串，放到hash表，如果一个字符串出现了超过1次
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> retSet = new HashSet<>();
        Set<String> set = new HashSet<>();
        for(int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i+10);
            if(set.contains(sub)) {
                retSet.add(sub);
            } else {
                set.add(sub);
            }
        }

        return new ArrayList<>(retSet);
    }

    // 滚动hash
    // 第一个长度为10的字符串和第二个长度为10的字符串只有第一个字符(第一个字符串)和最后一个字符(第二个字符串)有差异
    // 每次去掉第一个字符，然后加上最后一个字符即可
    // 又由于字符只取4个值 A C G T，可以给它们编码，A->1 C->2 G->3 T->4，然后10个字符，就转换成10位的数值
    public List<String> findRepeatedDnaSequences2(String s) {
        if(s.length() < 10) {
            return new ArrayList<>();
        }
        Map<Character, Long> code = new HashMap<>();
        code.put('A', 1L);
        code.put('C', 2L);
        code.put('G', 3L);
        code.put('T', 4L);

        Set<String> ret = new HashSet<>();
        Set<Long> set = new HashSet<>();
        long curHash = 0;
        for(int i = 0; i < 10; i++) {
            curHash = curHash * 10 + code.get(s.charAt(i));
        }
        set.add(curHash);

        long ten9 = (long) 1e9;

        for(int i = 1; i <= s.length() - 10; i++) {
            curHash = (curHash - code.get(s.charAt(i-1)) * ten9) * 10 + code.get(s.charAt(i + 9));
            if(set.contains(curHash)) {
                ret.add(s.substring(i, i+10));
            } else {
                set.add(curHash);
            }
        }

        return new ArrayList<>(ret);
    }

    public static void main(String[] args) {
        String s = "GAGAGAGAGAGA";
        Solution solution = new Solution();
        System.out.println(solution.findRepeatedDnaSequences2(s));
    }
}
