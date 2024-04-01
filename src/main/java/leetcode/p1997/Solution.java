package leetcode.p1997;

public class Solution {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        double days = 0;

        int n = nextVisit.length;
        // 存放每个房间访问的次数
        int[] cnt = new int[n];
        // 是否访问了所有房间，每次第一次访问某个房间all++
        int all = 0;
        // 下一次应该访问哪个房间
        int nextRoom = 0;
        while (all != n) {
            if(cnt[nextRoom] == 0) {
                all++;
            }
            cnt[nextRoom]++;

            if(cnt[nextRoom] % 2 != 0) {
                nextRoom = nextVisit[nextRoom];
            } else {
                nextRoom = (nextRoom + 1) % n;
            }

            days++;
        }

        return (int) ((days-1) % (Math.pow(10, 9) + 7));
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Solution solution = new Solution();
        System.out.println(solution.firstDayBeenInAllRooms(nums));
    }
}
