package leetcode.p111;

import leetcode.p144.TreeNode;

public class Solution {
    // 返回以root为根的二叉树中最小深度值
    public int minDepth(TreeNode root) {
        // 只有一个孩子节点的节点，此时这里会直接返回0，它的最小深度就是0，然而它并不是叶子节点
        if(root == null) {
            return 0;
        }

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        if(leftDepth == 0 && rightDepth == 0) { // 左右深度都是0，是叶子节点
            return 1;
        } else if(leftDepth == 0) { // 不是叶子节点
            return rightDepth + 1;
        } else if(rightDepth == 0) { // 不是叶子节点
            return leftDepth + 1;
        } else {
            return Math.min(leftDepth, rightDepth) + 1;
        }
    }

    // 返回以root为根的二叉树中最小深度值
    public int minDepth2(TreeNode root) {
        // 只有一个孩子节点的节点，此时这里会直接返回0，它的最小深度就是0，然而它并不是叶子节点
        if(root == null) {  // 这个逻辑只有初始传入的root==null才会走，递归过程中保证了传入了root不会为null，所以这里不是真正的递归结束条件
            return 0;
        }

        if(root.left == null && root.right == null) {   // 叶子节点，最小深度为1，这里才是真正的递归结束条件
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;
        if(root.left != null) {
            minDepth = Math.min(minDepth, minDepth2(root.left));
        }

        if(root.right != null) {
            minDepth = Math.min(minDepth, minDepth2(root.right));
        }

        return minDepth + 1;
    }
}
