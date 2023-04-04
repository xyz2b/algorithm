package leetcode.p450;

import leetcode.p144.TreeNode;

public class Solution {
    // 找到待删除节点的，前驱或后继，替换掉当前要删除的元素
    // 前驱左子树的右子树的右子树一直到叶子节点（就是左子树中的最大节点）
    // 后继右子树的左子树左子树一直到叶子节点（就是右子树中的最小节点）


    // 待删除的节点份三种情况
    // 1.待删除的节点只有右子树，没有左子树，那么删除以该节点为根的二叉树，直接返回右子树即可
    // 2.待删除的节点只有左子树，没有右子树，那么删除以该节点为根的二叉树，直接返回左子树即可
    // 3.待删除的节点左右子树都存在，此时就需要找左子树的最大值(前驱)或者右子树的最小值(后继)来替换掉当前节点
    // 返回以root为根的二叉树中，删除val为key的node以后新的树的根
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }

        if(root.val == key) {
            if(root.left == null) {
                TreeNode rightNode = root.right;
                root.right = null;
                return rightNode;
            } else if (root.right == null) {
                TreeNode leftNode = root.left;
                root.left = null;
                return leftNode;
            } else { // node.left != null && node.right != null
                // 找到后继，替换掉当前root节点
                // 当前节点的后继: 右子树的左子树左子树一直到叶子节点（就是右子树中的最小节点）
                TreeNode successor = findMin(root.right);
                successor.right = delMin(root.right);
                successor.left = root.left;
                root.left = null;
                root.right = null;
                return successor;
            }
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else { // key > root.val
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    // 返回以node为根的二叉树中的最小值
    private TreeNode findMin(TreeNode node) {
        if(node.left == null) {
            return node;
        }

        return findMin(node.left);
    }

    // 删除以node为根的二叉树中的最小值，然后返回新的根
    private TreeNode delMin(TreeNode node) {
        if(node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;
            return rightNode;
        }

         node.left = delMin(node.left);
        return node;
    }
}
