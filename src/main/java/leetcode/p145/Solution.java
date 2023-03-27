package leetcode.p145;

import leetcode.p144.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        post(root, rst);
        return rst;
    }

    private void post(TreeNode node, List<Integer> rst) {
        if(node == null) return;

        post(node.left, rst);
        post(node.right, rst);
        rst.add(node.val);
    }
}
