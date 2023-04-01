package leetcode.p222;

import leetcode.p144.TreeNode;

public class Solution {
    // 返回以root为根的二叉树的节点个数
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        int leftChild = countNodes(root.left);
        int rightChild = countNodes(root.right);

        return leftChild + rightChild + 1;
    }
}
