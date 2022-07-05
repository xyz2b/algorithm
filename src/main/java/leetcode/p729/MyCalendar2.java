package leetcode.p729;

import java.util.TreeSet;

// TreeSet，二分搜索树
class MyCalendar2 {
    // 保证加进来的区间有序
    private TreeSet<int[]> booked;

    public MyCalendar2() {
        booked = new TreeSet<int[]>((a, b) -> a[0] - b[0]);
    }

    public boolean book(int start, int end) {
        if (booked.isEmpty()) {
            booked.add(new int[] {start, end});
            return true;
        }

        // 找到已有区间的起点比end大中的最小值，就是end的ceil
        // end < ceil
        int[] temp = new int[]{end, 0};
        int[] arr = booked.ceiling(temp);
        // 找到end的ceil的前驱
        // 如果end没有ceil，即没有比end还大的，那就将前驱置为最后一个区间
        int[] prev = arr == null ? booked.last() : booked.lower(arr);

        // 如果end没有ceil，即区间起点没有比end还大的，新加入的区间可能在最后，只需要比较最后一个区间的终点是否小于等于start即可
        if (arr == null) {
            if (booked.last()[1] <= start) {
                booked.add(new int[] {start, end});
                return true;
            } else {
                return false;
            }
        }

        // 如果end的ceil是第一个区间，说明第一个区间的起点都比end大，新加入的区间在最开始，这种是满足条件的，直接加入结果集
        if (arr == booked.first()) {
            if (booked.last()[0] >= start) {
                booked.add(new int[] {start, end});
                return true;
            } else {
                return false;
            }
        }

        // 新插入的区间在中间的情况，只需要满足end的ceil的前驱的终点小于等于start，并且end的ceil的起点大于等于end即可
        if (prev[1] <= start && arr[0] >= end) {
            booked.add(new int[] {start, end});
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        MyCalendar2 obj = new MyCalendar2();
        System.out.println(obj.book(10, 20));
        System.out.println(obj.book(15, 25));
        System.out.println(obj.book(20, 30));
    }
}