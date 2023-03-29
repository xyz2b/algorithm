package leetcode.p127_graph;

import java.util.*;

public class Solution {
    static class Pair<F, S> {
        public F first;
        public S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    // 图的广度优先遍历
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<Pair<String, Integer>> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Pair<>(beginWord, 1));
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            Pair<String, Integer> pair = queue.poll();
            String s = pair.first;
            int step = pair.second;
            char[] sArray = s.toCharArray();

            // 变换，每一位都可以有26种变化，变换之后的字符串要在wordList中，并且之前没有访问过
            for(int i = 0; i < sArray.length; i++) {
                // 保存下需要修改的位
                char oldC = sArray[i];
                for(char c = 'a'; c <= 'z'; c++) {
                    sArray[i] = c;
                    String newS = new String(sArray);
                    if(newS.equals(endWord)) {
                        return step + 1;
                    }

                    if(wordSet.contains(newS) && !visited.contains(newS)) {
                        queue.add(new Pair<>(newS, step + 1));
                        visited.add(newS);
                    }

                    // 恢复修改的位，然后继续下一轮循环，找到下一个可转换的字符串
                    sArray[i] = oldC;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        Solution solution = new Solution();
        System.out.println(solution.ladderLength(beginWord, endWord, wordList));
    }
}
