package leetcode.p199;

import leetcode.p144.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
    // 层序遍历，队列
    // 结果是 每一层最右边的节点，每一层从左向右遍历的最后一个节点
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if(root == null) return rst;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int curLevelNodeNums = 1;
        int nextLevelNodeNums = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevelNodeNums--;

            if(node.left != null) {
                queue.offer(node.left);
                nextLevelNodeNums++;
            }

            if(node.right != null) {
                queue.offer(node.right);
                nextLevelNodeNums++;
            }

            if(curLevelNodeNums == 0) { // 本层遍历完了，此时就遍历到了本层的最后一个节点
                curLevelNodeNums = nextLevelNodeNums;
                nextLevelNodeNums = 0;
                rst.add(node.val);
            }
        }

        return rst;
    }
}
