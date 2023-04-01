package leetcode.p101;

import leetcode.p144.TreeNode;

public class Solution {
    // 对称，即左右孩子对称
    public boolean isSymmetric(TreeNode root) {
        return isSame(root.left, root.right);
    }

    // 返回以p q为根的二叉树，是否是对称
    // 即p的左孩子是否和q的右孩子相等 并且 p的右孩子是否和q的左孩子相等
    // p.left == q.right && p.right == q.left
    private boolean isSame(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        } else if (p == null || q == null) {    // 只有一方为null，结构不同，肯定不对称
            return false;
        }

        boolean isSame = false;
        if(isSame(p.left, q.right) && isSame(p.right, q.left)) {  // p.left == q.right && p.right == q.left
            isSame = true;
        }

        return p.val == q.val && isSame;    // 本身p和q的val要相等，同时p的左孩子和q的右孩子要相等，同时p的右孩子和q的左孩子要相等
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        Solution solution = new Solution();
        System.out.println(solution.isSymmetric(root));
    }
}
