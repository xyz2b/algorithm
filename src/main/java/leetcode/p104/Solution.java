package leetcode.p104;

import leetcode.p144.TreeNode;

public class Solution {
    // 返回以root为根的二叉树的最大高度
    // 就是以root节点左右孩子节点为根的二叉树的最大高度值加上1
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // 返回本节点以及其所有子节点的最大高度值
        // 就是本节点左右孩子节点中最大高度值加上1(本节点的高度值1)
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode((7))));
        Solution solution = new Solution();
        System.out.println(solution.maxDepth(root));
    }
}
