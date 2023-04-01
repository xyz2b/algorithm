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

    // 满二叉树的节点个数为 2 ^ h - 1，h为层数
    public int countNodes2(TreeNode root) {
        if(root == null){
            return 0;
        }

        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right) { // 左右子树的层数一样，即说明左子树是满二叉树，因为已经填充到右子树的节点上了，但是右子树不一定是满二叉树，因为可能最后一层只有部分节点
            return countNodes(root.right) + (1 << left);    // 左子树是满的所以是2 ^ left - 1，再加上当前的root节点1个，就是1 & left，即1 << left
        }else{ // 左右子树的层数不一样，说明左子树不是满二叉树，而右子树是比左子树少一层的满二叉树，因为已经填充到下一层的左子树了，所以上一层肯定是满的
            return countNodes2(root.left) + (1 << right);
        }
    }

    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }

}
