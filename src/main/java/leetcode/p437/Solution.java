package leetcode.p437;

import leetcode.p144.TreeNode;

public class Solution {
    // 返回以root为根节点的二叉树中，存在节点和等于targetSum的路径数量，root不一定在路径中
    public int pathSum(TreeNode root, int targetSum) {
        int pSum = 0;
        if(root == null) return pSum;

        pSum += findPath(root, targetSum);  // 以root为根的二叉树中，从根节点开始往下遍历，存在节点和等于targetSum的路径数量，路径中包含root
        // 如果findPath为0，说明root节点不在满足条件的路径中，就继续向其left和right节点看

        // 递归调用，以二叉树中每个节点为根节点，判断这样的子二叉树中存在节点和等于targetSum的路径数量，root.left或root.right不一定在路径中
        pSum += pathSum(root.left, targetSum);
        pSum += pathSum(root.right, targetSum);

        return pSum;
    }

    // 返回以node为根的二叉树中，从根节点开始往下遍历，存在节点和等于targetSum的路径数量
    // 此时node必在路径中
    public int findPath(TreeNode node, long targetSum) {
        int pathCount = 0;
        if(node == null) return pathCount;

        if(node.val == targetSum) { // 不能直接返回，还需要继续往下递归，可能下面还有节点和等于0的
            pathCount += 1;
        }

        int left = findPath(node.left, targetSum - node.val);
        pathCount += left;

        int right = findPath(node.right, targetSum - node.val);
        pathCount += right;

        return pathCount;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5)))));
        Solution solution = new Solution();
        System.out.println(solution.pathSum(root, 3));
    }
}
