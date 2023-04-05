package leetcode.p108;

import leetcode.p144.TreeNode;

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // 找到数组的中位点，然后在左半部分再找中位点，在右半部分再找中位点，循环往复
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    // 返回以nums数组[l,r]位置元素组成的二叉树的根，根是[l,r]中位点的元素
    private TreeNode sortedArrayToBST(int[] nums, int l, int r) {
        if(l > r) {
            return null;
        }

        int middle = (r - l) / 2 + l;
        TreeNode node = new TreeNode(nums[middle]);

        node.left = sortedArrayToBST(nums, l, middle - 1);
        node.right = sortedArrayToBST(nums, middle + 1, r);

        return node;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-10, -3, 0, 5, 9};
        Solution solution = new Solution();
        solution.sortedArrayToBST(nums);
    }
}
