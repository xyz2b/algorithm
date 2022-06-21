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
     * 重点：保证每个区间不相互重叠
     * 前一个区间的right不会大于后一个区间的left
     * 所以 增加或删除区间时，有重叠的区间需要合并
     *
     * 新增区间分为两种情况：
     * 1.新加入的区间 left存在前驱
     * 说明新加入的区间left 大于前驱的left 小于等于前驱的后继区间left
     * 新加入区间有两种可能的
     * a.前驱left < 新加入区间的left < 新加入区间的right <= 前驱的right 这种新加入区间在已有区间之内的情况不需要处理 直接返回
     * b.前驱left < 新加入区间的left <= 前驱的right < 新加入区间的right 这种情况需要把新加入区间的left扩展到前驱的left，说明新加入区间和其前驱存在重叠，需要进行合并
     *
     * 2.新加入区间不存在前驱，新加入区间的left小于等于最小区间的left
     *
     * 上面两种情况 最后都需要进行合并 因为新加入区间中间可能跨度几个已有区间
     * 第一种就从新加入区间的前驱的后继开始 前驱已经处理过了
     * 第二种就从第一个区间开始
     *
     * 遍历到新加入区间的right小于遍历区间的left时停止 说明后面的区间都比新加入区间大 不存在重叠 无需合并
     *
     * 合并时 只需要去新加入区间right和当前遍历区间right最大值即可
     * （这里也分两种情况，1.新加入区间right大于等于遍历区间的right，2.新加入区间right小于遍历区间right，说明新加入区间的right在遍历区间之间，不过这两种情况都可以取最大值处理即可）
     *
     * 删除区间
     * 1.删除的区间left存在前驱
     * 说明删除区间left 大于前驱的left 小于等于前驱的后继区间left
     * 删除区间有两种可能的
     * a.前驱left < 删除区间的left < 删除区间的right<=前驱的right，这种删除区间在已有区间之内的，直接进行处理完返回即可，不需要进行区间合并
     *      删除时这里也分为两种情况
     *      - 如果删除区间的right < 前驱的right，这种就相当于在已有区间中间挖个洞，删除原有区间，新增两个区间
     *      - 如果删除区间的right = 前驱的right，这种就删除原有区间，新增一个区间
     * b.前驱left < 删除区间left < 前驱的right < 删除区间right
     *      说明删除区间和前驱有重合部分，此时需要对这个前驱进行处理，就是把前驱的区间删掉重合的部分，之后还需要进行后续区间的删除合并，因为删除的区间中间可能有多个已存在区间
     *
     * 2.删除区间不存在前驱，删除区间的left小于等于最小区间的left
     *
     * 上面两种情况 最后都需要进行合并 因为删除区间中间可能跨度几个已有区间
     * 第一种就从删除区间的前驱的后继开始 前驱已经处理过了
     * 第二种就从第一个区间开始
     *
     * 遍历到新加入区间的right小于遍历区间的left时停止 说明后面的区间都比新加入区间大 不存在重叠 无需合并
     * 1.如果删除区间right大于等于遍历区间的right，删除区间完全包含遍历区间，直接删除遍历区间，然后继续往后继遍历即可
     * 2.如果删除区间right小于遍历区间的right，删除区间和遍历区间有重叠部分，删除掉重叠部分，然后直接返回即可
     * （为什么直接返回，因为 x1 < right < y1 < x2 < y2，当前遍历区间的后继的left x2一定大于删除区间的right，后面的区间和删除区间没关系了，已经处理到最后一个有重叠的区间了，直接返回即可）
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

    // 返回的是前驱的索引，为-1，表示没有找到前驱
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

    // 返回的是后驱的索引，为数组长度，表示没有找到后驱
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
