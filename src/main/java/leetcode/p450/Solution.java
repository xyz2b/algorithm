package leetcode.p450;

import leetcode.p144.TreeNode;

public class Solution {
    // 找到待删除节点的，前驱或后继，替换掉当前要删除的元素
    // 前驱左子树的右子树的右子树一直到叶子节点
    // 后继右子树的左子树左子树一直到叶子节点

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return root;
        }

        if(root.val == key) {
            if(root.left == null) {

            }
        } else if (key < root.val) {
            return deleteNode(root.left, key);
        } else { // key > root.val
            return deleteNode(root.right, key);
        }
    }

    // 返回以root为根的二叉树中，删除val为key的node以后新的树的根
    public TreeNode delete(TreeNode node, int key) {
        if(node == null) {
            return null;
        }

        if(node.val == key) {
            if(node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                return rightNode;
            } else if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                return leftNode;
            } else { // node.left != null && node.right != null
                // 后继
                // 后继右子树的左子树左子树一直到叶子节点
                TreeNode successor = successor(node.right);

                successor.left = node.left;
                successor.right = node.right;
                node.left = null;
                node.right = null;
                return successor;
            }
        } else if (key < node.val) {
            return deleteNode(node.left, key);
        } else { // key > root.val
            return deleteNode(node.right, key);
        }
    }

    public TreeNode successor(TreeNode node) {
        if(node.left == null) {
            return node;
        }

        return successor(node.left);
    }
}
