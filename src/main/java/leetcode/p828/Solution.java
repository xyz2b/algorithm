package leetcode.p828;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * 对于 i 位置的字符c_i，记同字符上一次出现的位置为 j，下一次出现的位置为 k，那么包含c_i字符且没有重复的c_i字符的字符串就一共有(i-j)*(k-i)个
     * */
    public int uniqueLetterString(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c, new ArrayList<>());
                map.get(c).add(-1);
            }
            map.get(c).add(i);
        }

        int rst = 0;
        for(Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            List<Integer> arr = entry.getValue();
            arr.add(s.length());
            // 对于当前遍历的字符，包含它且没有和它重复的子字符串的数量
            for(int i = 1; i < arr.size() - 1; i++) {
                rst += (arr.get(i) - arr.get(i - 1)) * (arr.get(i + 1) - arr.get(i));
            }
        }

        return rst;
    }
}
