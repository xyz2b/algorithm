package leetcode.p2306;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public long distinctNames(String[] ideas) {
        // 首字母和剩下字符的映射

        // preA+sufA preB+sufB --> preA+sufB preB+sufA
        // sufB 不在 preA 对应的sufA的集合中，即sufB与sufA的差集，即sufB不在sufA中的那部分元素
        // sufA 不在 preB 对应的sufB的集合中，即sufA与sufB的差集，即sufA不在sufB中的那部分元素

        // sufB与sufA的差集中的元素数量 -> sufB集合元素数量 - sufA和subB交集的数量
        // sufA与sufB的差集中的元素数量 -> sufA集合元素数量 - sufA和subB交集的数量

        Map<Character, Set<String>> subSuf = new HashMap<>();
        for(String s : ideas) {
            subSuf.putIfAbsent(s.charAt(0), new HashSet<>());
            subSuf.get(s.charAt(0)).add(s.substring(1));
        }

        long ret = 0;
        for(Map.Entry<Character, Set<String>> entryA : subSuf.entrySet()) {
            Character preA = entryA.getKey();
            Set<String> sufA = entryA.getValue();
            for(Map.Entry<Character, Set<String>> entryB : subSuf.entrySet()) {
                Character preB = entryB.getKey();
                Set<String> sufB = entryB.getValue();
                if(preA == preB) {
                    continue;
                }

                int intersect = getIntersectSize(sufA, sufB);
                ret += ((long) (sufB.size() - intersect) * (sufA.size() - intersect));
            }
        }
        return ret;
    }

    private int getIntersectSize(Set<String> a, Set<String> b) {
        int ret = 0;
        for(String s : a) {
            if(b.contains(s)) {
                ret++;
            }
        }
        return ret;
    }
}
