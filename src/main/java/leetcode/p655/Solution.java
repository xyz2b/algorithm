package leetcode.p655;

import java.util.*;


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
    public List<List<String>> printTree(TreeNode root) {
        // 广度优先遍历先计算出高度
        int nextLevelNodes = 0;
        int curLevelNodes = 0;
        int height = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        curLevelNodes++;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevelNodes--;

            if(node.left != null) {
                queue.offer(node.left);
                nextLevelNodes++;
            }

            if(node.right != null) {
                queue.offer(node.right);
                nextLevelNodes++;
            }

            if(curLevelNodes == 0) {
                curLevelNodes = nextLevelNodes;
                nextLevelNodes = 0;

                height++;
            }
        }

        height = height - 1;

        int m = height + 1;
        int n = (int) Math.pow(2, height + 1) - 1;

        List<List<String>> rst = new ArrayList<>(m);
        for(int i = 0; i < m; i++) {
            List<String> l = new ArrayList<>(n);
            for(int j = 0; j < n; j++) {
                l.add("");
            }
            rst.add(l);
        }

        // 深度优先遍历填充二维数组
        dfs(root, 0, (n - 1) / 2, height, rst);

        return rst;
    }

    // 以node为根的节点，在二维数组的索引为[r,c]，height为树的高度，rst为二维数组
    private void dfs(TreeNode node, int r, int c, int height, List<List<String>> rst) {
        if(node == null) {
            return;
        }

        rst.get(r).set(c, String.valueOf(node.val));
        dfs(node.left, r + 1, c - (int) Math.pow(2, height - r - 1), height, rst);
        dfs(node.right, r + 1, c + (int) Math.pow(2, height - r - 1), height, rst);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3));
        Solution solution = new Solution();
        System.out.println(solution.printTree(root));
    }
}
