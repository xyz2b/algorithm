package leetcode.p404;

import leetcode.p144.TreeNode;

public class Solution {
    // 返回以root为根的树的，左叶子节点的和
    // 注意递归结束条件是到了左叶子节点
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;  // 不是真正的递归结束条件，是初始root为null的情况

        int leftNode = 0;
        if(root.left != null && root.left.left == null && root.left.right == null) { // 当前节点的左叶子节点
            leftNode = root.left.val;
        }

        int left = sumOfLeftLeaves(root.left);      // 以root.left为根的左子树中，左叶子节点的和
        int right = sumOfLeftLeaves(root.right);    // 以root.right为根的右子树中，左叶子节点的和

        return leftNode + left + right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        Solution solution = new Solution();
        System.out.println(solution.sumOfLeftLeaves(root));
    }
}
