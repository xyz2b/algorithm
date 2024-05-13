package leetcode.p994;

import java.util.*;

class Solution {
    // 多源广度优先搜索
    // 找到所有坏的橘子所在的位置作为源点，然后使用图的广度优先搜索
    // 统计整个途中的好的橘子的总数，遍历到一个好的橘子，该总数减一，如果遍历完整个图之后，还有剩余的好的橘子，那就返回-1
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] visited = new int[m][n];

        // 多源，而非单源
        List<int[]> starts = new ArrayList<>();
        int good = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2) {
                    starts.add(new int[]{i, j, 0});
                } else if(grid[i][j] == 1) {
                    good++;
                }
            }
        }

        if(good == 0) {
            return 0;
        }
        if(starts.isEmpty()) {
            return -1;
        }

        Deque<int[]> queue = new ArrayDeque<int[]>();
        for(int[] start : starts) {
            queue.offer(start);
            visited[start[0]][start[1]] = 1;
        }

        int time = -1;
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            if(grid[x][y] == 1) {
                good--;
            }
            time = node[2];

            for(int[] d : dirs) {
                int nextX = x + d[0];
                int nextY = y + d[1];

                if(inGrid(nextX, nextY, m, n)) {
                    if(grid[nextX][nextY] != 0 && visited[nextX][nextY] == 0) {
                        visited[nextX][nextY] = 1;
                        int[] nextNode = new int[] {nextX, nextY, time+1};
                        queue.offer(nextNode);
                    }
                }
            }
        }
        return good == 0 ? time : -1;
    }

    boolean inGrid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >=0 && y < n;
    }
}
