package leetcode.p731;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// 直接遍历
class MyCalendarTwo {
    // 预订的所有区间
    List<int[]> booked;
    // 已经遇到两次的区间
    List<int[]> overlaps;

    public MyCalendarTwo() {
        booked = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for(int[] overlap : overlaps) {
            int l = overlap[0];
            int r = overlap[1];
            if(end > l && start < r) {  // 包含了三种情况：start < l < end < r、l < start < end < r、l < start < r < end，这三种情况都是存在重叠的情况，其他是不存在重叠的情况
                return false;
            }
        }

        for(int[] book : booked) {
            int l = book[0];
            int r = book[1];
            if (end > l && start < r) {
                // 将重叠的部分加入预定过两次区间的列表
                // start < l < end < r、l < start < end < r、l < start < r < end 这三种有重叠的情况，重叠区间的左端点就是l和start的最大值，重叠区间的右端点就是r和end的最小值
                overlaps.add(new int[]{Math.max(l, start), Math.min(r, end)});
            }
        }
        booked.add(new int[]{start, end});
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo obj = new MyCalendarTwo();
        System.out.println(obj.book(10, 20));
        System.out.println(obj.book(50, 60));
        System.out.println(obj.book(10, 40));
        System.out.println(obj.book(5, 15));
        System.out.println(obj.book(5, 10));
        System.out.println(obj.book(25, 55));
    }
}