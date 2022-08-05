package leetcode.p623;


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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }

        // 广度优先，层序遍历
        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.offer(root);
        // 当前遍历的深度
        int curDepth = 1;
        // 当前层未遍历的节点数量
        int curLevelNodes = 1;
        // 下一层的节点数量
        int nextLevelNodes = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevelNodes--;

            if(curDepth == depth - 1) { // 遍历达到了需要插入的层的前一层，就进行插入，并且不再往下一层遍历，即不把下一层的节点加入到队列中
                node.left = new TreeNode(val, node.left, null);
                node.right = new TreeNode(val, null, node.right);
            } else {    // 没有达到需要插入的层的前一层，就继续往下层遍历，把下一层的节点加入到队列中
                if(node.left != null) {
                    queue.offer(node.left);
                    nextLevelNodes++;
                }
                if(node.right != null) {
                    queue.offer(node.right);
                    nextLevelNodes++;
                }
            }

            if(curLevelNodes == 0) {    // 当前层的节点遍历完了，下面遍历的就是下一层的节点
                // 更新当前层未遍历的节点数量为下一层节点总数
                curLevelNodes = nextLevelNodes;
                nextLevelNodes = 0;

                curDepth++; // 当前遍历的层数加1
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(3, null, null), new TreeNode(1, null, null)), new TreeNode(6, new TreeNode(3, null, null), null));

    }
}
