package leetcode.p1773;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        Map<String, Integer> map = new HashMap<>();

        // index == 0 -> type, index == 1 -> color, index == 2 -> name
        int index = -1;
        if(ruleKey.equals("type")) {
            index = 0;
        } else if (ruleKey.equals("color")) {
            index = 1;
        } else {    // name
            index = 2;
        }

        for(int i = 0; i < items.size(); i++) {
            String key = items.get(i).get(index);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return map.getOrDefault(ruleValue, 0);
    }

    public int countMatches2(List<List<String>> items, String ruleKey, String ruleValue) {
        // index == 0 -> type, index == 1 -> color, index == 2 -> name
        int index = -1;
        if(ruleKey.equals("type")) {
            index = 0;
        } else if (ruleKey.equals("color")) {
            index = 1;
        } else {    // name
            index = 2;
        }

        int rst = 0;
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).get(index).equals(ruleValue)) {
                rst++;
            }
        }
        return rst;
    }
}
