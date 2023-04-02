package leetcode.p112;

import leetcode.p144.TreeNode;

public class Solution {
    // 返回以node为根的二叉树，是否存在到某个叶子节点的路径的和为sum
    // 自顶向下的递归，通过参数把值传递给下层遍历的节点
    // 传入的sum是targetSum减去当前节点，还剩余的值，即以node为根的子二叉树，要存在到某个叶子节点的路径和要为sum才是满足题目要求
    // 向下层传递的sum是逐层递减的，因为在上层时候是把targetSum-node.val，然后传递给下层的
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;  // 给的二叉树就是空，那就直接返回false。这里只是判断初始值为null的情况，并不是真正的递归终止条件

        if(root.left == null && root.right == null) return root.val == targetSum;  // 到达叶子节点，剩余的sum值应该为当前叶子节点的值，这样路径总和才是targetSum。这才是真正的递归终止条件

        if(hasPathSum(root.left, targetSum - root.val)) {
            return true;
        }
        if(hasPathSum(root.right,targetSum - root.val)) {
            return true;
        }

        return false;
    }

    // 返回以node为根的二叉树，是否存在到某个叶子节点的路径的和为sum
    // 自顶向下的递归，通过参数把值传递给下层遍历的节点
    // 传入的sum是targetSum减去当前节点，还剩余的值，即以node为根的子二叉树，要存在到某个叶子节点的路径和要为sum才是满足题目要求
    // 向下层传递的sum是逐层递减的，因为在上层时候是把targetSum-node.val，然后传递给下层的
    public boolean sum(TreeNode node, int sum) {
        if(node == null) return false;  // 给的二叉树就是空，那就直接返回false。这里只是判断初始值为null的情况，并不是真正的递归终止条件

        if(node.right == null && node.left == null) {   // 到达叶子节点，剩余的sum值应该为当前叶子节点的值，这样路径总和才是targetSum。这才是真正的递归终止条件
            if(sum == node.val) {
                return true;
            }
        }

        int sum1 = sum - node.val;

        boolean left = sum(node.left, sum1);
        boolean right = sum(node.right, sum1);

        return left || right;
    }

    public static void main(String[] args) {

    }
}
