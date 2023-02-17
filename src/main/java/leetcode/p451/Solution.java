package leetcode.p451;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    // 先统计频次，再反转成频次和字符的映射
    // 再根据频次排序，然后再根据频次降序放置字符
    // （因为是根据频次进行降序排序，所以使用了最大堆，
    //   直接将频次和字符的映射加入到最大堆中(以频次作为排序字段)，
    //   根据最大堆的属性，堆顶元素是最大的，所以每次拿出来的映射的key都是最大频次的字符）
    public String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
        }

        // 最大堆
        PriorityQueue<Map.Entry<Integer, Character>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> {
                    int key1 = e1.getKey();
                    int key2 = e2.getKey();
                    return key2 - key1;
                }
        );

        for(Map.Entry<Character, Integer> e : freq.entrySet()) {
            char c = e.getKey();
            int f = e.getValue();

            maxHeap.offer(new Map.Entry<Integer, Character>() {
                private int k = f;
                private char v = c;

                @Override
                public Integer getKey() {
                    return k;
                }

                @Override
                public Character getValue() {
                    return v;
                }

                @Override
                public Character setValue(Character value) {
                    char temp = v;
                    v = value;
                    return temp;
                }
            });
        }

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Integer, Character> e = maxHeap.poll();
            int f = e.getKey();
            char v = e.getValue();

            for(int i = 0; i < f; i++) {
                sb.append(v);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "Aabb";
        Solution solution = new Solution();
        System.out.println(solution.frequencySort(s));
    }
}
