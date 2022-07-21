package leetcode.p814;

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

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        // 深度优先遍历（后序遍历，先处理左右子树，然后再处理当前元素）
        boolean n = dfs(root);
        if (!n) {   // 如果以root为根的树中没有包含1，则直接返回空即可
            return null;
        }
        return root;
    }

    // 以node为根的树中是否包含1，返回false表示不包含，返回true表示包含
    private boolean dfs(TreeNode node) {
        // 节点为空直接返回false，既不包含1也不包含0
        if (node == null) {
            return false;
        }

        // 当前遍历节点node的左子树中是否含有1
        boolean left = dfs(node.left);
        // 当前遍历节点node的右子树中是否含有1
        boolean right = dfs(node.right);

        // 如果当前遍历节点的左子树中不包含1，就将左子树删除
        if (!left) {
            node.left = null;
        }
        // 如果当前遍历节点的右子树中不包含1，就将右子树删除
        if(!right) {
            node.right = null;
        }

        // 返回以当前遍历的node节点为根的树中是否包含1
        return node.val == 1 || left || right;
    }

    public void preOder(TreeNode node) {
        if (node == null) {
            return;
        }
        preOder(node.left);
        System.out.println(node.val);
        preOder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(0, new TreeNode(0, null, null), new TreeNode(1, null, null)));
        Solution solution = new Solution();
        TreeNode n = solution.pruneTree(root);

        solution.preOder(n);
    }
}