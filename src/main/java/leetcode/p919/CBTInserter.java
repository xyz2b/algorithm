package leetcode.p919;

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

class CBTInserter {
    private TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
    }

    // 广度优先遍历，层序遍历
    public int insert(int val) {
        int rst = 0;

        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if(node.left == null) {
                node.left = new TreeNode(val);
                rst = node.val;
                break;
            } else {
                queue.offer(node.left);
            }

            if(node.right == null) {
                node.right = new TreeNode(val);
                rst = node.val;
                break;
            } else {
                queue.offer(node.right);
            }
        }
        return rst;
    }

    public TreeNode get_root() {
        return root;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1, new TreeNode(2), null);
        CBTInserter cbtInserter = new CBTInserter(node);
        System.out.println(cbtInserter.insert(3));
        System.out.println(cbtInserter.insert(4));

    }
}