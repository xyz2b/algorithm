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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(7, new TreeNode(6), new TreeNode(8)));
        Solution solution = new Solution();
        System.out.println(solution.isValidBST(root));
    }
}
