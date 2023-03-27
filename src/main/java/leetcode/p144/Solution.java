package leetcode.p144;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        pre(root, rst);
        return rst;
    }

    private void pre(TreeNode node, List<Integer> rst) {
        if(node == null) return;

        rst.add(node.val);
        pre(node.left, rst);
        pre(node.right, rst);
    }

    // 非递归
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if(root == null) return rst;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            rst.add(node.val);
            // 反向
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }

        return rst;
    }
}
