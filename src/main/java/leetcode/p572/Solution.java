package leetcode.p572;

import com.sun.jmx.snmp.SnmpNull;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if(isSameTree(node, subRoot)) {
                return true;
            }

            if(node.left != null) {
                queue.offer(node.left);
            }

            if(node.right != null) {
                queue.offer(node.right);
            }
        }

        return false;
    }

    private boolean isSameTree(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        } else if(node1 == null && node2 != null) {
            return false;
        } else if(node1 != null && node2 == null) {
            return false;
        }

        if(node1.val != node2.val) {
            return false;
        }

        boolean left = isSameTree(node1.left, node2.left);
        boolean right = isSameTree(node1.right, node2.right);

        return left & right;
    }
}
