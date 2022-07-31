package leetcode.p1161;

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

// 广度优先遍历，层序遍历
class Solution {
    public int maxLevelSum(TreeNode root) {
        // 当前层的累加和
        int curLevelSum = 0;
        // 最大累加和
        int maxLevelSum = Integer.MIN_VALUE;
        // 最大累加和的层数（如果最大累加和相同取最小的层号）
        int minMaxSumLevel = Integer.MAX_VALUE;
        // 当前层数
        int curLevel = 0;

        // 当前层的节点数
        int curLevelNodes = 0;
        // 下一层的节点数
        int nextLevelNodes = 0;
        // 是否是新的一层
        int newLevel = 0;

        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.offer(root);
        curLevelNodes++;
        curLevel = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevelSum += node.val;
            curLevelNodes--;

            if(node.left != null) {
                queue.offer(node.left);
                nextLevelNodes++;
            }
            if(node.right != null) {
                queue.offer(node.right);
                nextLevelNodes++;
            }

            // 遍历的下一个节点就是新的一层的节点
            if(curLevelNodes == 0) {
                // 重置
                curLevelNodes = nextLevelNodes;
                nextLevelNodes = 0;
                newLevel = 1;

                // 当前层的累加和 大于 之前最大累加和
                if(maxLevelSum < curLevelSum) {
                    maxLevelSum = curLevelSum;
                    minMaxSumLevel = curLevel;
                } else if (maxLevelSum == curLevelSum) {
                    // 其实这一步不需要也可以，因为层数是递增的，所以后面遍历的层数肯定比前面遍历的层数大
                    minMaxSumLevel = Math.min(minMaxSumLevel, curLevel);
                }

                curLevelSum = 0;
                curLevel++;
            }
        }

        return minMaxSumLevel;
    }
}
