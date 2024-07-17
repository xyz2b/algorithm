package leetcode.p2959;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int ret = 0;

        int[] opened = new int[n];
        int[][] d = new int[n][n];

        // 使用mask来表示开放哪些分部的集合，mask每一位表示一个分部的开启关闭状态，1为开启，0为关闭
        for(int mask = 0; mask < (1 << n); mask++) {
            for(int i = 0 ; i < n; i++) {
                opened[i] = mask & (1 << i);
            }

            for(int i = 0; i < n; i++) {
                Arrays.fill(d[i], Integer.MAX_VALUE);
                d[i][i] = 0;
            }

            // 构造去除关闭分部之后的图
            for(int[] road : roads) {
                int u = road[0];
                int v = road[1];
                int w = road[2];

                if(opened[u] > 0 && opened[v] > 0) {
                    d[u][v] = Math.min(d[u][v], w);
                    d[v][u] = Math.min(d[v][u], w);
                }
            }

            // Floyd 算法，求解所有点对的最短路径
            /**
             * 初始，如果v-a有边，d[v][a] = va，d[v][v] = 0；否则 d[v][a] = 正无穷，表示v-a不可达
             * v-a 的最短路径 + a-b 的最短路径 小于 v-b 的最短路径，说明 v-a-b 的路径比直接由 v-b 的路径短，此时更新 v-b 的最短路径
             * if(d[v][a] + d[a][b] < d[v][b]) {
             *     d[v][b] = d[b][v] = d[v][a] + d[a][b];
             * }
             * 内两层循环一直在找v-a的最短路径，最外层循环b是为了：v-a是不是可以从b多绕一个弯，v-a经过b能够得到一个更短的路径
             * */
            for(int b = 0; b < n; b++) {
                if(opened[b] > 0) {
                    for(int v = 0; v < n; v++) {
                        if(opened[v] > 0) {
                            for(int a = 0 ; a < n; a++) {
                                if(opened[a] > 0) {
                                    // v-b和b-a不能为Integer.MAX_VALUE，因为之后的加法运算会溢出，导致结果为负。
                                    //  从逻辑上讲两点之间距离为正无穷，即两点之间不连通，所以需要过滤掉
                                    // v-b不连通，v就不能通过b走到a。同样，b-a不连通，v也不能通过b走到a
                                    if(d[v][b] != Integer.MAX_VALUE && d[b][a] != Integer.MAX_VALUE && d[v][b] + d[b][a] < d[v][a]) {
                                        d[v][a] = d[a][v] = d[v][b] + d[b][a];
                                    }
                                }
                            }
                        }
                    }
                }
            }


            // 验证结果是否符合条件
            // 验证所有开放的分部之间的最短距离有没有超过maxDistance，超过就不满足条件
            int ok = 1;
            for(int i = 0; i < n; i++) {
                if(opened[i] > 0) {
                    for(int j = 0; j < n; j++) {
                        if(opened[j] > 0) {
                            if(d[i][j] > maxDistance) {
                                ok = 0;
                                break;
                            }
                        }
                    }
                    if(ok == 0) {
                        break;
                    }
                }
            }

            ret += ok;
        }
        return ret;
    }

    public static void main(String[] args) {
        int n = 5;
        int maxDistance = 20;
        int[][] roads = {{1,0,27},{2,0,8},{2,1,3},{1,0,41},{4,3,36},{4,2,36},{4,1,8},{1,0,31},{3,1,12}};

        Solution solution = new Solution();
        System.out.println(solution.numberOfSets(n, maxDistance, roads));
    }
}
