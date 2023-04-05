package leetcode.p230;

import leetcode.p144.TreeNode;

public class Solution {
    // 二分搜索树中序遍历的有序性
    // 时间复杂度: 最差O(N)，平均O(logN)
    // 第几次遍历到中间
    private int c;
    // 返回以root为根的二叉树中第k小的元素，如果不存在返回Integer.MAX_VALUE（比如二叉树只有2个节点，要返回第三小的元素，就是不存在）
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return Integer.MAX_VALUE;

        int left = kthSmallest(root.left, k);
        if(left != Integer.MAX_VALUE) return left;
        c++;
        if(c == k) {            // 第k次遍历到该位置，就是第k小
            return root.val;
        }
        int right = kthSmallest(root.right, k);
        if(right != Integer.MAX_VALUE) return right;

        // 不存在
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));

        Solution solution = new Solution();
        System.out.println(solution.kthSmallest(root, 2));
    }
}
