package leetcode.p773;

import java.lang.reflect.Array;
import java.util.*;

// 6位数字压缩状态
public class Solution {
    private HashMap<Integer, Integer> distances = new HashMap<>();
    private int t;

    private int[][] dirs = new int[][] {{1,0}, {0,1},{-1,0},{0,-1}};

    private int R;
    private int C;
    public int slidingPuzzle(int[][] board) {
        R = board.length;
        C = board[0].length;
        int s = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                s = s * 10 + board[i][j];
            }
        }
        t = 123450;

        bfs(s);

        return distances.getOrDefault(t, -1);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        distances.put(s, 0);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            int tmpv = v;
            int zerox = 0;
            int zeroy = 0;
            int[][] tmp = new int[R][C];
            for(int i = R - 1; i >=0; i--) {
                for (int j = C - 1; j >= 0; j--) {
                    tmp[i][j] = tmpv % 10;
                    tmpv = tmpv / 10;
                    if(tmp[i][j] == 0) {
                        zerox = i;
                        zeroy = j;
                    }
                }
            }

            ArrayList<Integer> nextvs = new ArrayList<>();
            for(int d = 0; d < 4; d++) {
                int nextx = zerox + dirs[d][0];
                int nexty = zeroy + dirs[d][1];

                if(inArea(nextx, nexty)) {
                    int[][] tempArr = new int[R][C];
                    for(int i = 0; i < R; i++) {
                        tempArr[i] = Arrays.copyOf(tmp[i], tmp[i].length);
                    }
                    swap(tempArr, nextx, nexty, zerox, zeroy);

                    int nextv = 0;
                    for(int i = 0; i < R; i++) {
                        for(int j = 0; j < C; j++) {
                            nextv = nextv * 10 + tempArr[i][j];
                        }
                    }
                    nextvs.add(nextv);
                }
            }

            for(int nextv : nextvs) {
                if(!distances.containsKey(nextv)) {
                    queue.add(nextv);
                    distances.put(nextv, distances.get(v) + 1);

                    if (nextv == t) {
                        return;
                    }
                }
            }
        }
    }

    private void swap(int[][] arr, int x1, int y1, int x2, int y2) {
        int temp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = temp;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][] {{4,1,2},{5,0,3}};
        Solution solution = new Solution();
        System.out.println(solution.slidingPuzzle(nums));
    }
}