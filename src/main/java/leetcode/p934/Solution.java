package leetcode.p934;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /**
     * 广度优先搜索
     * 通过遍历找到数组 grid 中的 1 后进行广度优先搜索，此时可以得到第一座岛的位置集合，记为 island，并将其位置全部标记为 −1。
     * 随后我们从 island 中的所有位置开始进行广度优先搜索，当它们到达了任意的 1 时，即表示搜索到了第二个岛，搜索的层数就是答案。
     */
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        // 广度优先搜索的方向（向四周搜索：左(x-1)，右(x+1)，上(y-1)，下(y+1)）
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> isLand = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 找到第一个land，将其坐标加入到isLand中
                // 先找到第一个为1的，然后广度优先搜索，上下左右搜索，如果为1将坐标加入isLand中，同时也要加入queue中继续搜索1的上下左右，直到1的上下左右没有再为1为止
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    // 已经遍历过的坐标直接置为-1即可，不需要一个单独的visited列表来表示
                    grid[i][j] = -1;
                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int x = cell[0], y = cell[1];
                        isLand.add(new int[]{x, y});
                        // 从一个坐标(x, y)向四周搜索
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dirs[k][0];
                            int ny = y + dirs[k][1];
                            // 搜索的坐标没有超出边界
                            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                                if (grid[nx][ny] == 1) {
                                    queue.offer(new int[]{nx, ny});
                                    // 已经遍历过的坐标直接置为-1即可，不需要一个单独的visited列表来表示
                                    grid[nx][ny] = -1;
                                }
                            }
                        }
                    }

                    // 将第一块陆地的坐标，都加入到队列中，然后从第一块陆地开始搜索直到找到第二块大陆
                    for (int[] cell : isLand) {
                        queue.offer(cell);
                    }
                    int step = 0;
                    while (!queue.isEmpty()) {
                        int sz = queue.size();
                        // 从第一块大陆所有坐标开始向四周搜索，直到搜索到一个为1的坐标
                        // 广度优先遍历相当于一圈一圈的搜索，从搜索坐标开始慢慢往外圈扩散
                        for (int k = 0; k < sz; k++) {
                            int[] cell = queue.poll();
                            int x = cell[0], y = cell[1];
                            // 从一个坐标(x, y)向四周搜索
                            for (int d = 0; d < 4; d++) {
                                int nx = x + dirs[d][0];
                                int ny = y + dirs[d][1];
                                // 搜索的坐标没有超出边界
                                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                                    if (grid[nx][ny] == 0) {
                                        queue.offer(new int[]{nx, ny});
                                        // 已经遍历过的坐标直接置为-1即可，不需要一个单独的visited列表来表示
                                        grid[nx][ny] = -1;
                                    } else if (grid[nx][ny] == 1) { // 搜索到一个为1的坐标说明遇到了其他陆地，可以直接返回搜索的层数(圈数)
                                        return step;
                                    }
                                }
                            }
                        }
                        step++;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 深度优先搜索 + 广度优先搜索
     * 通过遍历找到数组 grid 中的 1 后进行深度优先搜索，此时可以得到第一座岛的位置集合，记为 island，并将其位置全部标记为 −1。
     * 随后我们从 island 中的所有位置开始进行广度优先搜索，当它们到达了任意的 1 时，即表示搜索到了第二个岛，搜索的层数就是答案。
     */
    public int shortestBridge2(int[][] grid) {
        int n = grid.length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Queue<int[]> queue = new ArrayDeque<int[]>();
                    // 深度优先搜索，找到第一座岛的所有坐标
                    dfs(i, j, grid, queue);
                    // 此时，queue是第一座岛的所有坐标，开始广度优先搜索，与上面解法一样
                    int step = 0;
                    while (!queue.isEmpty()) {
                        int sz = queue.size();
                        for (int k = 0; k < sz; k++) {
                            int[] cell = queue.poll();
                            int x = cell[0], y = cell[1];
                            for (int d = 0; d < 4; d++) {
                                int nx = x + dirs[d][0];
                                int ny = y + dirs[d][1];
                                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                                    if (grid[nx][ny] == 0) {
                                        queue.offer(new int[]{nx, ny});
                                        grid[nx][ny] = -1;
                                    } else if (grid[nx][ny] == 1) {
                                        return step;
                                    }
                                }
                            }
                        }
                        step++;
                    }
                }
            }
        }
        return 0;
    }

    // 深度优先搜索
    public void dfs(int x, int y, int[][] grid, Queue<int[]> queue) {
        // 判断是否越界 以及 是否为1
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1) {
            return;
        }
        // 为1的才加入队列
        queue.offer(new int[]{x, y});
        grid[x][y] = -1;
        // 向四周深度优先搜索
        // 左
        dfs(x - 1, y, grid, queue);
        // 右
        dfs(x + 1, y, grid, queue);
        // 下
        dfs(x, y - 1, grid, queue);
        // 上
        dfs(x, y + 1, grid, queue);
    }
}