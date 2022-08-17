package leetcode.p1302;

import java.util.ArrayDeque;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Solution {
    // 广度优先遍历
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int sum = 0;

        queue.offer(root);
        while (!queue.isEmpty()) {
            sum = 0;
            int size = queue.size();    // 每一层的元素数量
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }

        }
        return sum;
    }

    // 深度优先遍历
    int maxLevel = -1;
    int sum = 0;
    public int deepestLeavesSum1(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    // 以node为根的树，当前node所在的层数
    private void dfs(TreeNode node, int level) {
        if(node == null) {
            return;
        }

        // 当前遍历的node所在层数大于最大层数
        if(level > maxLevel) {
            maxLevel = level;
            sum = node.val;
        } else if(level == maxLevel) {  // 当前遍历的node所在层数等于最大层数
            sum += node.val;
        }
        // 向下一层遍历
        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }
}
