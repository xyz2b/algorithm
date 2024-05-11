package leetcode.p2391;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        // 统计每个房子中每种垃圾的数量
        int[][] garbages = new int[garbage.length][3];

        // 最后一个有对应类型垃圾的房子的索引
        int[] last = new int[3];
        Arrays.fill(last, - 1);

        for(int i = 0; i < garbage.length; i++) {
            String house = garbage[i];
            for(int j = 0; j < house.length(); j++) {
                char c = house.charAt(j);
                if(c == 'M') {
                    garbages[i][0]++;
                    last[0] = i;
                } else if(c == 'P') {
                    garbages[i][1]++;
                    last[1] = i;
                } else {    // 'G'
                    garbages[i][2]++;
                    last[2] = i;
                }
            }
        }

        int ret = 0;
        for(int i = 0; i < 3; i++) {
            if(last[i] == -1) { // 所有房子中都不存在该种垃圾
                continue;
            }

            for(int j = 0; j <= last[i]; j++) {
                if(j != 0) {
                   ret += travel[j-1];
                }
                ret += garbages[j][i];
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        String[] garbage = new String[]{"MMM","PGM","GP"};
        int[] travel = new int[]{3, 10};
        Solution solution = new Solution();
        System.out.println(solution.garbageCollection(garbage, travel));
    }
}
