package leetcode.p144;

import java.util.ArrayList;
import java.util.List;

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
}
