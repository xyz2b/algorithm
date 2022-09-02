package leetcode.p687;

import com.sun.tools.internal.xjc.outline.EnumOutline;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return res;
    }

    // 返回以node为根的树中最大同值路径的长度(边长)
    private int dfs(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        int left1 = 0, right1 = 0;
        if(node.left != null && node.left.val == node.val) {
            left1 = left + 1;
        }
        if(node.right != null && node.right.val == node.val) {
            right1 = right + 1;
        }

        res = Math.max(res, left1 + right1);
        return Math.max(left1, right1);
    }
}
