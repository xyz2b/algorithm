package leetcode.p100;

import leetcode.p144.TreeNode;

public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        } else if(p == null || q == null) {
            return false;
        }

        boolean childSame = false;
        if(isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
            childSame = true;
        }

        return p.val == q.val && childSame;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1, new TreeNode(2, new TreeNode(2), null), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(3), new TreeNode(2));
        Solution solution = new Solution();
        System.out.println(solution.isSameTree(p, q));
    }
}
