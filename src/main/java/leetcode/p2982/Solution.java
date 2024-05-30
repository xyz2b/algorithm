package leetcode.p2982;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        // 统计
        List<Integer>[] chs = new List[26];
        for(int i = 0; i < 26; i++) {
            // PriorityQueue默认最小堆，最大堆 (i1, i2) -> i2 - i1
            chs[i] = new ArrayList<>(4);
        }

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            cnt++;

            if(i + 1 == n || s.charAt(i) != s.charAt(i+1)) {
                int ch = s.charAt(i) - 'a';
                chs[ch].add(cnt);
                cnt = 0;

                // 将新加入的元素排序
                for(int j = chs[ch].size() - 1; j > 0; j--) {
                    if(chs[ch].get(j) > chs[ch].get(j - 1)) {
                        Collections.swap(chs[ch], j, j - 1);
                    }
                }

                // 只保留前三大
                if(chs[ch].size() > 3) {
                    chs[ch].remove(chs[ch].size() - 1);
                }
            }
        }

        // 计算结果
        int ret = -1;
        for(int i = 0; i < 26; i++) {
            if(chs[i].size() > 0 && chs[i].get(0) > 2) {
                ret = Math.max(ret, chs[i].get(0) - 2);
            }
            if(chs[i].size() > 1 && chs[i].get(0) > 1) {
                if(chs[i].get(0) == chs[i].get(1)) {
                    ret = Math.max(ret, chs[i].get(0) - 1);
                } else {
                    ret = Math.max(ret, chs[i].get(1));
                }
            }
            if(chs[i].size() > 2) {
                ret = Math.max(ret, chs[i].get(2));
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = "abcaba";
        Solution solution = new Solution();
        System.out.println(solution.maximumLength(s));
    }
}
