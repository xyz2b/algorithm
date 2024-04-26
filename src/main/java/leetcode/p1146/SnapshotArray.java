package leetcode.p1146;

import java.util.*;

public class SnapshotArray {
    private List<TreeMap<Integer, Integer>> snapshotArray;
    private int snap_id;

    public SnapshotArray(int length) {
        snap_id = 0;
        snapshotArray = new ArrayList<>(length);
        for(int i = 0; i < length; i++) {
            snapshotArray.add(new TreeMap<>());
        }
    }

    public void set(int index, int val) {
        TreeMap<Integer, Integer> element = snapshotArray.get(index);
        element.put(snap_id, val);
    }

    public int snap() {
        int ret = snap_id;
        snap_id++;
        return ret;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> element = snapshotArray.get(index);
        // [floor_snap_id, snap_id] 之间的数据是没有变化了
        // 数据有变化就会在TreeMap中用最新的snap_id生成一条数据，TreeMap中两个相邻的snap_id之间的数据是没有变化的
        Integer key = element.floorKey(snap_id);
        if(key == null)
            return 0;
        return snapshotArray.get(index).getOrDefault(key, 0);
    }

    public static void main(String[] args) {
        int length = 1;
        SnapshotArray obj = new SnapshotArray(length);
        obj.set(0,15);
        int param_2 = obj.snap();
        System.out.println(param_2);
        param_2 = obj.snap();
        System.out.println(param_2);
        param_2 = obj.snap();
        System.out.println(param_2);

        int param_3 = obj.get(0,2);
        System.out.println(param_3);
        param_2 = obj.snap();
        System.out.println(param_2);
        param_2 = obj.snap();
        System.out.println(param_2);

        param_3 = obj.get(0,0);
        System.out.println(param_3);
    }
}
