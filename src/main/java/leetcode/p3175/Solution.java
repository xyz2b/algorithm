package leetcode.p3175;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int findWinningPlayer(int[] skills, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] wins = new int[skills.length];

        for(int i = 0; i < skills.length; i++) {
            deque.addLast(i);
        }

        while (true) {
            int first = deque.pollFirst();
            int second = deque.pollFirst();

            if(skills[first] > skills[second]) {
                deque.addFirst(first);
                deque.addLast(second);
                wins[first]++;
                if(wins[first] >= k) {
                    return first;
                }
            } else {
                deque.addLast(first);
                deque.addFirst(second);
                wins[second]++;

                if(wins[second] >= k) {
                    return second;
                }
            }
        }
    }

    public int findWinningPlayer2(int[] skills, int k) {
        // 双指针，队列头i赢家，队列中第二位j，待对比的玩家，输了j指针不断往后挪，模拟输家移到对列尾的情况
        int n = skills.length;
        int i = 0;
        int last_i = 0;
        int cnt = 0;
        while (i < n) {
            int j = i + 1;
            // i赢的情况，队列头i位置不变，一直往后移动j，即模拟输家挪到队列尾，然后j依旧指向待对比的队列头中的第二位玩家
            while (j < n && skills[j] < skills[i] && cnt < k) {
                j++;
                cnt++;
            }

            if(cnt >= k) {
                return i;
            }
            // 输家是i的情况，j变成赢家，模拟挪到队列头
            cnt = 1;
            last_i = i;
            i = j;
        }
        return last_i;
    }
}
