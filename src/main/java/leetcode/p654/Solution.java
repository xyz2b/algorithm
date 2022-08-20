package leetcode.p654;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    // 返回以nums数组中[l,r]中最大值为根的树
    public TreeNode constructMaximumBinaryTree(int[] nums, int l, int r) {
        if(l > r) {
            return null;
        }

        int maxIndex = max(nums, l, r);
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = constructMaximumBinaryTree(nums, l, maxIndex - 1);
        node.right = constructMaximumBinaryTree(nums, maxIndex + 1, r);
        return node;
    }

    private int max(int[] nums, int l, int r) {
        int maxIndex = 0;
        int max = Integer.MIN_VALUE;
        for(int i = l; i <= r; i++) {
            if(max < nums[i]) {
                maxIndex = i;
                max = nums[i];
            }
        }
        return maxIndex;
    }

    // 单调栈，没看懂
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        TreeNode[] tree = new TreeNode[n];
        for (int i = 0; i < n; ++i) {
            tree[i] = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                right[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        TreeNode root = null;
        for (int i = 0; i < n; ++i) {
            if (left[i] == -1 && right[i] == -1) {
                root = tree[i];
            } else if (right[i] == -1 || (left[i] != -1 && nums[left[i]] < nums[right[i]])) {
                tree[left[i]].right = tree[i];
            } else {
                tree[right[i]].left = tree[i];
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,2,1,6,0,5};
        Solution solution = new Solution();
        solution.constructMaximumBinaryTree(nums);
    }
}
