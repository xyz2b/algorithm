package leetcode.p110;

import leetcode.p144.TreeNode;

public class Solution {
    // 返回以root为根的二叉树高度差是不是小于等于1
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode node) {
        if(node == null) return 0;

        int left = height(node.left);
        int right = height(node.right);

        return Math.max(left, right) + 1;
    }
}
