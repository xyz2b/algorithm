package leetcode.p773;

import java.lang.reflect.Array;
import java.util.*;

// 6位数字压缩状态
public class Solution {
    private HashMap<Integer, Integer> distances = new HashMap<>();
    private HashMap<Integer, Integer> pre = new HashMap<>();
    private int t;

    // 四联通
    private int[][] dirs = new int[][] {{1,0}, {0,1},{-1,0},{0,-1}};

    private int R;
    private int C;
    private int s;
    public int slidingPuzzle(int[][] board) {
        R = board.length;
        C = board[0].length;
        s = boardToInt(board);
        t = 123450;

        if(s == t) return 0;

        bfs(s);

        return distances.getOrDefault(t, -1);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        distances.put(s, 0);
        pre.put(s, s);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            ArrayList<Integer> nextvs = getNexts(v);

            for(int nextv : nextvs) {
                if(!distances.containsKey(nextv)) {
                    queue.add(nextv);
                    distances.put(nextv, distances.get(v) + 1);
                    pre.put(nextv, v);

                    if (nextv == t) {
                        return;
                    }
                }
            }
        }
    }

    private ArrayList<Integer> getNexts(int v) {
        int[][] cur = intToBoard(v);
        int zero ;
        for(zero = 0; zero < R * C; zero++) {
            if(cur[zero / 3][zero % 3] == 0) {
                break;
            }
        }

        int zerox = zero / 3;
        int zeroy = zero % 3;
        ArrayList<Integer> nextvs = new ArrayList<>();
        for(int d = 0; d < 4; d++) {
            int nextx = zerox + dirs[d][0];
            int nexty = zeroy + dirs[d][1];

            if(inArea(nextx, nexty)) {
                swap(cur, nextx, nexty, zerox, zeroy);
                int nextv = boardToInt(cur);
                nextvs.add(nextv);
                // 由于下一轮循环还是需要原始的board，所以需要再还原回去
                swap(cur, nextx, nexty, zerox, zeroy);
            }
        }

        return nextvs;
    }

    private int boardToInt(int[][] board) {
        int n = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                n = n * 10 + board[i][j];
            }
        }
        return n;
    }

    private int[][] intToBoard(int n) {
        int[][] board = new int[R][C];
        for(int i = R - 1; i >=0; i--) {
            for (int j = C - 1; j >= 0; j--) {
                board[i][j] = n % 10;
                n = n / 10;
            }
        }
        return board;
    }

    private void swap(int[][] arr, int x1, int y1, int x2, int y2) {
        int temp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = temp;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public boolean canSolved() {
        return distances.get(t) != null;
    }

    public ArrayList<int[][]> path() {
        ArrayList<int[][]> path = new ArrayList();
        if(!canSolved()) return path;

        int cur = t;
        while (cur != s) {
            path.add(intToBoard(cur));
            cur = pre.get(cur);
        }
        path.add(intToBoard(s));

        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][] {{4,1,2},{5,0,3}};
        Solution solution = new Solution();
        System.out.println(solution.slidingPuzzle(nums));

        StringBuilder sb = new StringBuilder();
        for(int[][] v : solution.path()) {
            sb.append("[");
            for(int i = 0; i < v.length; i++) {
                sb.append("[");
                for(int j = 0; j < v[0].length; j++) {
                    sb.append(v[i][j]);
                    if(j != v[0].length - 1) {
                        sb.append(",");
                    }
                }
                sb.append("]");

            }
            sb.append("]\n");
        }
        System.out.println(sb);
    }
}