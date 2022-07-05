package leetcode.p729;

import java.util.Map;
import java.util.TreeMap;

// treeMap，二分搜索树，前驱后继
class MyCalendar {
    // 保证加进来的区间有序
    private TreeMap<Integer, Integer> booked;

    public MyCalendar() {
        booked = new TreeMap<Integer, Integer>();
    }

    public boolean book(int start, int end) {
        if(booked.containsKey(start)) {
            return false;
        }

        if(booked.isEmpty()) {
            booked.put(start, end);
            return true;
        }

        Map.Entry<Integer, Integer> lowerEntry = booked.lowerEntry(start);
        Map.Entry<Integer, Integer> higherEntry = booked.higherEntry(start);
        if(lowerEntry != null && higherEntry != null) {
            int lowerEntryEnd = lowerEntry.getValue();
            int higherEntryStart = higherEntry.getKey();

            if(start >= lowerEntryEnd && end <= higherEntryStart) {
                booked.put(start, end);
                return true;
            } else {
                return false;
            }
        } else if (lowerEntry != null) { // start大于所有的key
            int lowerEntryEnd = lowerEntry.getValue();
            if(start >= lowerEntryEnd) {
                booked.put(start, end);
                return true;
            } else {
                return false;
            }
        } else { // higherEntry != null): start小于所有key. higherEntry和lowerEntry都为空的情况是treeMap为空，上面处理过了，这里无需处理
            int higherEntryStart = higherEntry.getKey();

            if(end <= higherEntryStart) {
                booked.put(start, end);
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        MyCalendar obj = new MyCalendar();
        System.out.println(obj.book(10, 20));
        System.out.println(obj.book(15, 25));
        System.out.println(obj.book(20, 30));
    }
}