package predecessor;

public class Predecessor {
    // 返回v在nums中前驱节点的值（前驱：小于v的最大值）
    public int search(int[] nums, int v) {
        // 前驱节点的索引，如果为1，说明前驱就是自己
        int index = search(nums, v, 0, nums.length - 1);
        if (index == -1) {
            return v;
        } else {
            return nums[index];
        }
    }

    private int search(int[] nums, int target, int l, int r) {
        int medium = (r - l) / 2 + l;

        if(nums[medium] == target) {
            return medium - 1;
        }

        if (medium == 0 && nums[medium] > target) {
            return -1;
        } else if (medium == nums.length - 1 && nums[medium] < target) {
            return nums.length - 1;
        } else if (nums[medium] < target && nums[medium + 1] > target) {
            return medium;
        }

        if (nums[medium] > target) {
            return search(nums, target, l, medium - 1);
        } else { // nums[medium] < target
            return search(nums, target, medium + 1, r);
        }
    }

    public static void main(String[] args) {
        Predecessor predecessor = new Predecessor();
        int[] nums = new int[] {1, 5, 10, 15, 20, 30};
        System.out.println(predecessor.search(nums, 30));
    }
}
