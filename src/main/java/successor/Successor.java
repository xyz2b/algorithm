package successor;

public class Successor {
    // 返回v在nums中后驱节点的值（前驱：大于v的最小值）
    public int search(int[] nums, int v) {
        // 后驱节点的索引，如果为nums.length，说明前驱就是自己
        int index = search(nums, v, 0, nums.length - 1);
        if (index == nums.length) {
            return v;
        } else {
            return nums[index];
        }
    }

    private int search(int[] nums, int target, int l, int r) {
        int medium = (r - l) / 2 + l;

        if(nums[medium] == target) {
            return medium + 1;
        }

        if (medium == 0 && nums[medium] > target) {
            return 0;
        } else if (medium == nums.length - 1 && nums[medium] < target) {
            return nums.length;
        } else if (nums[medium] < target && nums[medium + 1] > target) {
            return medium + 1;
        }

        if (nums[medium] > target) {
            return search(nums, target, l, medium - 1);
        } else { // nums[medium] < target
            return search(nums, target, medium + 1, r);
        }
    }

    public static void main(String[] args) {
        Successor successor = new Successor();
        int[] nums = new int[] {1, 5, 10, 15, 20, 30};
        System.out.println(successor.search(nums, 0));
    }
}
