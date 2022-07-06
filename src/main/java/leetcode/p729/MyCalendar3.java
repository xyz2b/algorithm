package leetcode.p729;

import java.util.Map;
import java.util.TreeMap;

// 线段树（区间固定，静态数据结构，使用数组实现树形结构（一个索引的左孩子是2*i+1，右孩子是2*i+2，i从0开始））
// 动态开点（不真正创建一个完整的线段树包含所有区间，而只是在线段树中增加已有区间的节点，节点的值是区间的左右端点）（动态开点的线段树不能用数组实现，动态数据结构，只能用指针来实现了）
class MyCalendar3 {

    public MyCalendar3() {

    }

    public boolean book(int start, int end) {
        return false;
    }

    public static void main(String[] args) {
        MyCalendar3 obj = new MyCalendar3();
        System.out.println(obj.book(10, 20));
        System.out.println(obj.book(15, 25));
        System.out.println(obj.book(20, 30));
    }
}