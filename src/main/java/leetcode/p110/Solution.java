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


    private boolean isBalanced = true;
    public boolean isBalanced2(TreeNode root) {
        height2(root);
        return isBalanced;
    }

    // 自底向上的递归
    // 返回以node为根的二叉树的高度
    // 即 左子树高度和右子树高度的最大值+1(1是node节点本身)
    private int height2(TreeNode node) {
        if(node == null) return 0;

        int left = height2(node.left);
        int right = height2(node.right);

        if(Math.abs(left - right) >= 2) {   // 在自底向上的遍历过程中，如果发现左右子树的高度差大于1，那就置位
            isBalanced = false;
        }

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        Solution solution = new Solution();
        System.out.println(solution.isBalanced2(root));
    }
}
