package leetcode.p226;

import leetcode.p144.TreeNode;

public class Solution {
    // 左右孩子对调
    // 返回以root根的二叉树左右孩子对调之后的新的二叉树的根
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;

        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);

        root.right = leftNode;
        root.left = rightNode;
        return root;
    }
}
