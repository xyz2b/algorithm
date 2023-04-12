package leetcode.p401;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> rst = new ArrayList<>();
    // 前四个元素为小时，后六个元素为分钟，每一位表示一个灯
    private static int[] binary = new int[] {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};

    public List<String> readBinaryWatch(int turnedOn) {
        readBinaryWatch(turnedOn, 0, 0, 0, 0);
        return rst;
    }

    // 目前已经亮了num个灯，这些灯所表示的小时数为h，分钟数为m
    // 继续从start开始搜寻下一个亮的灯(最终使得亮灯数等于turnedOn)，然后计算所有亮着的灯所表示的时间
    private void readBinaryWatch(int turnedOn, int start, int num, int h, int m) {
        // 因为小时在0-11之间，分钟在0-59之间，递归终止条件一
        if(h > 11 || m > 59) {
            return;
        }

        // 递归终止条件二，因为上面的判断条件，所以逻辑走到这里，小时和分钟一定是符合的，同时亮灯数也等于turnedOn，满足题意
        if(num == turnedOn) {
            StringBuilder sb = new StringBuilder();
            sb.append(h).append(":");
            if(m < 10) {
                sb.append(0).append(m);
            } else {
                sb.append(m);
            }
            rst.add(sb.toString());
            return;
        }



        for(int i = start; i < binary.length; i++) {
            if(i <= 3) {
                h += binary[i];
            } else {
                m += binary[i];
            }

            readBinaryWatch(turnedOn, i + 1, num + 1, h, m);

            // 回溯
            if(i <= 3) {
                h -= binary[i];
            } else {
                m -= binary[i];
            }

            // 因为小时在0-11之间，分钟在0-59之间
            // 所以如果遇到小时数大于11，就不需要继续往下遍历小时了，但是可以继续遍历分钟
            // 如果遇到分钟数大于59，就不需要继续往下遍历分钟了，即也不需要继续遍历binary了，直接退出即可
            if(i <= 3) {
                if(h + binary[i] > 11) {
                    i = 3;
                    continue;
                }
            } else {
                if(m + binary[i] > 59) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        int turnedOn = 8;
        Solution solution = new Solution();
        System.out.println(solution.readBinaryWatch(turnedOn));
    }
}
