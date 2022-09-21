package leetcode.p854;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
    // 广度优先搜索
    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        // key是还未遍历字符的字符串，value是遍历到该字符串的哪个索引位置，默认从整个字符串的索引0开始
        Queue<Pair<String, Integer>> queue = new ArrayDeque<Pair<String, Integer>>();
        Set<String> visit = new HashSet<String>();
        queue.offer(new Pair<String, Integer>(s1, 0));
        visit.add(s1);
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Pair<String, Integer> pair = queue.poll();
                String cur = pair.getKey();
                int pos = pair.getValue();
                if (cur.equals(s2)) {   // 如果当前遍历字符串等于待比较字符串，就不用交换
                    return step;
                }
                // 如果当前遍历字符串的当前遍历位置的字符等于待比较字符串同样位置的字符，就跳过
                while (pos < n && cur.charAt(pos) == s2.charAt(pos)) {
                    pos++;
                }
                // cur.charAt(pos) != s2.charAt(pos)
                for (int j = pos + 1; j < n; j++) {
                    if (s2.charAt(j) == cur.charAt(j)) {    // 如果s2和cur，在pos之后的对应索引元素都相同，可以直接跳过，直到找到一个不相同的元素
                        continue;
                    }
                    if (s2.charAt(pos) == cur.charAt(j)) {  // 如果s2 pos位置的字符 和 cur pos位置后面的j位置的字符相同，就交换cur pos和j的位置，然后生成一个新的字符串放入队列，以便下次继续遍历搜索，然后下次遍历位置是pos+1
                        String next = swap(cur, pos, j);
                        if (!visit.contains(next)) {    // 有可能之前已经遍历过该字符串了，直接跳过即可
                            visit.add(next);
                            queue.offer(new Pair<String, Integer>(next, pos + 1));
                        }
                    }
                }
            }
            step++;
        }
        return step;
    }

    public String swap(String cur, int i, int j) {
        char[] arr = cur.toCharArray();
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
        return new String(arr);
    }
}