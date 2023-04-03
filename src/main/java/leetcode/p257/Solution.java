package leetcode.p257;

import leetcode.p144.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> rst = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        binaryTreePath(root, new StringBuilder());
        return rst;
    }

    // 自顶向下的递归
    // 传入的参数sb为遍历到当前node之前所走过的路径
    private void binaryTreePath(TreeNode node, StringBuilder sb) {
        if(node == null) return;

        if(node.left == null && node.right == null) { // 叶子节点
            sb.append(node.val);
            rst.add(sb.toString());
            return;
        }

        binaryTreePath(node.left, new StringBuilder(sb).append(node.val).append("->"));
        binaryTreePath(node.right, new StringBuilder(sb).append(node.val).append("->"));
    }

    // 返回以root为根节点的二叉树，从根节点到叶子节点的所有路径
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> rst = new ArrayList<>();
        if(root == null) return rst;

        if(root.left == null && root.right == null) { // 叶子节点
            rst.add(String.valueOf(root.val));
            return rst;
        }

        List<String> leftP = binaryTreePaths2(root.left);   // 返回以root.left为根的二叉树中的所有路径，此时在左子树所有路径开头加上本节点的值，就是以本节点为根的，到左子树中所有路径的集合
        for(String s : leftP) {
            StringBuilder sb = new StringBuilder(s);
            sb.insert(0, "->").insert(0, String.valueOf(root.val));
            rst.add(sb.toString());
        }
        List<String> rightP = binaryTreePaths2(root.right); // 返回以root.right为根的二叉树中的所有路径，此时在右子树所有路径开头加上本节点的值，就是以本节点为根的，到右子树中所有路径的集合
        for(String s : rightP) {
            StringBuilder sb = new StringBuilder(s);
            sb.insert(0, "->").insert(0, String.valueOf(root.val));
            rst.add(sb.toString());
        }
        return rst;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        Solution solution = new Solution();
        for(String s : solution.binaryTreePaths(root)) {
            System.out.println(s);
        }
    }

}
