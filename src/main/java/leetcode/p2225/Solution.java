package leetcode.p2225;

import java.util.*;

public class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> ret = new ArrayList<>(2);
        for(int i = 0; i < 2; i++) {
            ret.add(new ArrayList<>());
        }

        HashMap<Integer, Integer> losers = new HashMap<>();
        for(int i = 0; i < matches.length; i++) {
            int winner = matches[i][0];
            int loser = matches[i][1];

            losers.put(winner, losers.getOrDefault(winner, 0));
            losers.put(loser, losers.getOrDefault(loser, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : losers.entrySet()) {
            if(entry.getValue() < 2) {
                ret.get(entry.getValue()).add(entry.getKey());
            }
        }

        Collections.sort(ret.get(0));
        Collections.sort(ret.get(1));

        return ret;
    }
}
