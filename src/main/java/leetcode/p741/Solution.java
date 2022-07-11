package leetcode.p741;

import java.util.HashSet;

public class Solution {
    class Position implements Comparable<Position> {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Position p) {
            if(this.x == p.x && this.y == p.y) {
                return 0;
            }

            if (this.x >= p.x && this.y >= p.y) {
                return 1;
            }

            return -1;
        }
    }

    public int cherryPickup(int[][] grid) {
        int n = grid.length;

        // 存放走过的位置，尽量选择未走过的位置
        HashSet<Position> map = new HashSet<>();

        int x = 0, y = 0;
        while (x <= n && y <= n) {
            // 向右走
            if(grid[x][y+1] != -1) {

            }

            // 向下走
            if(grid[x+1][y] != -1) {

            }
        }

        return 0;
    }
}
