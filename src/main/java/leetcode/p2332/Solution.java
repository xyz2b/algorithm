package leetcode.p2332;

import java.util.Arrays;

class Solution {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int pos = 0;
        int space = 0;

        // 计算最后一个bus剩余的空位数
        for (int arrive : buses) {
            space = capacity;
            while (space > 0 && pos < passengers.length && passengers[pos] <= arrive) {
                space--;
                pos++;
            }
        }

        pos--;
        // 如果最后一个bus没有剩余空位，这意味着我们最后一个上车的乘客上车以后载客已满，此时我们从最后一个上车乘客的到达时间往前找到一个没有乘客到达的时刻即可，如果到达时间晚于最后一个上车的乘客的到达时间，则一定无法乘车。
        // 如果最后一个bus上还有剩余空位，这意味着我们最晚可以在最后一班公交发车时刻到站即可，由于不能跟别的乘客同时刻到达，此时从最后一班发车时刻 buses[n−1] 开始向前找到一个没有乘客到达的时刻即可。
        int lastCatchTime = space > 0 ? buses[buses.length - 1] : passengers[pos];
        while (pos >= 0 && passengers[pos] == lastCatchTime) {
            pos--;
            lastCatchTime--;
        }

        return lastCatchTime;
    }
}

