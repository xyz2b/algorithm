package leetcode.p715;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class RangeModule {
    // 有序映射（树形数据结构，二分搜索树的一种，有序）
    // key是left, value是right
    TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        intervals = new TreeMap<>();
    }

    /**
     * [x1, y1) [x2, y2) [x3, y3)
     * 插入[left, right)
     * 1.left < x1  (left没有前驱)
     * 2.x1 < left < right < y1 (left前驱是x1)
     * 3.x1 < left < y1 < right (left前驱是x1)
     * */
    public void addRange(int left, int right) {
        // 获取left的前驱
        Map.Entry<Integer, Integer> predecessor = intervals.lowerEntry(left);
        if (predecessor != null) {  // left前驱是x1
            if (predecessor.getValue() >= right) {     //  x1 < left < right <= y1，不需要处理
                return;
            }

            if (predecessor.getValue() >= left) {       // x1 < left <= y1 < right（y1到right中间可能跨度有几个区间）
                left = predecessor.getKey();            // [left=x1, right)
                intervals.remove(predecessor.getKey());
            }
        }  // else: left前驱为null，意思是left<=x1，没有前驱，直接合并即可，左边界就是left


        // 合并
        // 如果此时[left, right)中间跨度了几个已存在的区间，要进行合并
        // 从第一个entry(left前驱为空)或者predecessor的后继开始遍历，直到遍历区间的左边界大于right了，就没有需要合并的了，说明后面的区间都比[left,right)这个区间大
        Map.Entry<Integer, Integer> entry;
        if(predecessor == null) {   // 如果left的前驱为空，就从第一个entry开始遍历
            entry = intervals.firstEntry();
        } else {    // 如果left的前不为空，就从left前驱的后继开始遍历，因为left的前驱上面已经特殊处理过了
            entry = intervals.higherEntry(predecessor.getKey());
        }
        while (entry != null && entry.getKey() <= right) {      // x <= right
            right = Math.max(entry.getValue(), right);  // 合并时候，只需要 y和right 之间取最大值即可实现合并操作。当y <= right(right)，此时直接把老区间合并进来即可；当right < y(right = y)，right需要扩充老区间的右边界
            intervals.remove(entry.getKey());

            entry = intervals.higherEntry(entry.getKey());
        }

        intervals.put(left, right);
    }

    public void removeRange(int left, int right) {
        // 获取left的前驱
        Map.Entry<Integer, Integer> predecessor = intervals.lowerEntry(left);
        if (predecessor != null) {  // left前驱是x1
            if (predecessor.getValue() >= right) {     //  x1 < left < right <= y1
                intervals.remove(predecessor.getKey());
                if (predecessor.getValue() == right) {      //  x1 < left < right = y1
                    intervals.put(predecessor.getKey(), left);
                } else {    //  x1 < left < right < y1
                    intervals.put(predecessor.getKey(), left);
                    intervals.put(right, predecessor.getValue());
                }
                return;
            }

            if (predecessor.getValue() > left) {       // x1 < left < y1 < right（y1到right中间可能跨度有几个区间）
                intervals.remove(predecessor.getKey());
                intervals.put(predecessor.getKey(), left);
            }
        }  // else: left前驱为null，意思是left<=x1，没有前驱，直接合并即可，左边界就是left

        // 合并
        // 如果此时[left, right)中间跨度了几个已存在的区间，要进行合并
        // 从第一个entry或者predecessor的后继开始遍历，直到遍历区间的左边界大于right了，就没有需要合并的了，说明后面的区间都比[left,right)这个区间大
        Map.Entry<Integer, Integer> entry;
        if(predecessor == null) {   // 如果left的前驱为空，就从第一个entry开始遍历
            entry = intervals.firstEntry();
        } else {    // 如果left的前不为空，就从left前驱的后继开始遍历，因为left的前驱上面已经特殊处理过了
            entry = intervals.higherEntry(predecessor.getKey());
        }
        while (entry != null && entry.getKey() < right) {
            if (entry.getValue() <= right) {    // x < y <= right
                intervals.remove(entry.getKey());

                entry = intervals.higherEntry(entry.getKey());
            } else {    // x < right < y
                intervals.remove(entry.getKey());
                intervals.put(right, entry.getValue());
                break;
            }
        }
    }

    public boolean queryRange(int left, int right) {
        if (intervals.isEmpty()) {
            return false;
        }

        // 获取left的前驱
        Map.Entry<Integer, Integer> predecessor = intervals.lowerEntry(left);
        if (predecessor == null) {      // left前驱为空，两种情况，left<x1或left=x1
            predecessor = intervals.firstEntry();
            if (predecessor.getKey() != left) {
                return false;
            }
        }

        // 有前驱的情况
        if (predecessor.getKey() <= left && predecessor.getValue() >= right) {
            return true;
        } else { // 查询范围不在前驱的范围内，可能是当前查询范围的left == 前驱的后继left，比如{10=14, 16=20}，查询[16, 17)，16的前驱是10，10的后继是16
            if (intervals.higherEntry(predecessor.getKey()) == null) {      // 比如{10=14, 16=20}，查询[23, 29)
                return false;
            }

            if (intervals.higherEntry(predecessor.getKey()).getKey() == left && intervals.higherEntry(predecessor.getKey()).getValue() >= right) {
                return true;
            }

            return false;
        }
    }

    @Override
    public String toString() {
        return intervals.toString();
    }

    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(5, 8);
        System.out.println(rangeModule.toString());
        System.out.println(rangeModule.queryRange(3, 4));
        rangeModule.removeRange(5, 6);
        System.out.println(rangeModule.toString());
        rangeModule.removeRange(3, 6);
        System.out.println(rangeModule.toString());
        rangeModule.addRange(1, 3);
        System.out.println(rangeModule.toString());
        System.out.println(rangeModule.queryRange(2, 3));
        rangeModule.addRange(4, 8);
        System.out.println(rangeModule.toString());
        System.out.println(rangeModule.queryRange(2, 3));
        rangeModule.removeRange(4, 9);
        System.out.println(rangeModule.toString());
    }

    public int predecessor(List<Integer> nums, int v) {
        return predecessor(nums, v, 0, nums.size() - 1);
    }

    private int predecessor(List<Integer> nums, int target, int l, int r) {
        int medium = (r - l) / 2 + l;

        if (nums.get(medium) == target) {
            return medium - 1;
        }

        if (medium == 0 && nums.get(medium) > target) {
            return -1;
        } else if (medium == nums.size() - 1 && nums.get(medium) < target) {
            return nums.size() - 1;
        } else if (nums.get(medium) < target && nums.get(medium + 1) > target) {
            return medium;
        }

        if (nums.get(medium) > target) {
            return predecessor(nums, target, l, medium - 1);
        } else { // nums[medium] < target
            return predecessor(nums, target, medium + 1, r);
        }
    }

    public int successor(List<Integer> nums, int v) {
        return successor(nums, v, 0, nums.size() - 1);
    }

    private int successor(List<Integer> nums, int target, int l, int r) {
        int medium = (r - l) / 2 + l;

        if (nums.get(medium) == target) {
            return medium + 1;
        }

        if (medium == 0 && nums.get(medium) > target) {
            return 0;
        } else if (medium == nums.size() - 1 && nums.get(medium) < target) {
            return nums.size();
        } else if (nums.get(medium) < target && nums.get(medium + 1) > target) {
            return medium + 1;
        }

        if (nums.get(medium) > target) {
            return successor(nums, target, l, medium - 1);
        } else { // nums[medium] < target
            return successor(nums, target, medium + 1, r);
        }
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
