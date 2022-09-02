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


// 两个节点中间同值路径的最大长度
public class Solution {

    int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return res;
    }

    // 返回以node为根的树中，从node根节点出发到树中其他节点 最大同值路径的长度(边长)，要么存在于左子树，要么存在于右子树
    // 为啥返回某个子树中的最大同值路径长度，因为从子树根节点出发的到树中其他节点之间的最大同值路径，要么存在于左子树中，要么存在于右子树中
    /**
     *                        5
     *                   2          5
     *               4          5       5
     *           1      1    1     5   5    5
     *         1   2   1 1  1 1   3 4 5 8  9  5
     *
     *         从根节点出发的到树中其他节点之间最大同值路径，有两条：从根节点到叶子节点的两个5 都是最长的同值路径，所以在每颗子树中，都只会选择最长同值路径的一边的子树（左子树同值路径大就选择左子树，右子树同值路径大就选择而右子树）
     * */
    private int dfs(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        int left1 = 0, right1 = 0;
        // 左孩子不为空，并且左孩子和当前节点相同，那么以当前node为根，左子树上的最大同值路径长度要加1（包括当前节点）
        if(node.left != null && node.left.val == node.val) {
            left1 = left + 1;
        }
        // 右孩子不为空，并且右孩子和当前节点相同，那么以当前node为根，右子树上的最大同值路径长度要加1（包括当前节点）
        if(node.right != null && node.right.val == node.val) {
            right1 = right + 1;
        }

        // 以当前节点为根的两个节点中间同值路径的最大长度，就是当前节点左子树和右子树同值路径长度之和
        res = Math.max(res, left1 + right1);
        // 返回从当前遍历的node根节点出发到树中其他节点，最大同值路径的长度，要么存在于左子树，要么存在于右子树
        return Math.max(left1, right1);
    }
}
