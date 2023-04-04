package leetcode.p98;

import com.sun.jmx.snmp.SnmpNull;
import leetcode.p144.TreeNode;

public class Solution {
    // 返回以root为根的二叉树是不是二分搜索树
    // 根节点root是否大于左子树所有的值，根节点root是否小于右子树所有的值
    // 然后遍历以每个子节点为根节点的子树(root.left, root.right)
    public boolean isValidBST(TreeNode root) {
        boolean isBst = true;

        if(root == null) return isBst;

        if(root.left != null) {
            isBst = isBst & less(root.left, root.val);
        }

        if(root.right != null) {
            isBst = isBst & greater(root.right, root.val);
        }

        isBst = isBst & isValidBST(root.left);
        isBst = isBst & isValidBST(root.right);

        return isBst;
    }

    // 以node为根的二叉树中节点是否都小于target
    public boolean less(TreeNode node, int target) {
        if(node == null) return true;

        if(node.val >= target) {
            return false;
        }

        return less(node.left, target) && less(node.right, target);
    }

    // 以node为根的二叉树中节点是否都大于target
    public boolean greater(TreeNode node, int target) {
        if(node == null) return true;

        if(node.val <= target) {
            return false;
        }

        return greater(node.left, target) && greater(node.right, target);
    }


    // 如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值；
    // 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
    // 它的左右子树也为二叉搜索树
    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 以node为根的二叉树中，所有节点的值是否都在lower和upper之间
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }

        // 左子树的值应该小于node的值，大于lower
        // 右子树的值应该大于node的值，小于upper
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(7, new TreeNode(6), new TreeNode(8)));
        Solution solution = new Solution();
        System.out.println(solution.isValidBST(root));
    }
}
