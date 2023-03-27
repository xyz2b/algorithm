package leetcode.p94;

import leetcode.p144.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        inorder(root, rst);
        return rst;
    }

    private void inorder(TreeNode node, List<Integer> rst) {
        if(node == null) return;

        inorder(node.left, rst);
        rst.add(node.val);
        inorder(node.right, rst);
    }
}
