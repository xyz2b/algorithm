package leetcode.p236;

import leetcode.p144.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = findPath(root, p);
        List<TreeNode> qPath = findPath(root, q);

        // 遍历从roo到p的路径和从root到q的路径，第一个不相同的节点前一个节点就是他俩路径的分叉点
        for(int i = 1; i < Math.min(pPath.size(), qPath.size()); i++) {
            if(pPath.get(i) != qPath.get(i)) {
                return pPath.get(i - 1);
            }
        }

        // 到这里说明短的路径和长的路径前半部分完全相等，那么短的路径的最后一个节点就是分叉点
        return qPath.get(Math.min(pPath.size(), qPath.size()) - 1);
    }

    // 在二叉树中找到一个元素的路径
    // 返回以node为根的二叉树中找到target的路径
    private List<TreeNode> findPath(TreeNode node, TreeNode target) {
        List<TreeNode> path = new LinkedList<>();
        if(node == null) {
            return path;
        }

        if(node.val == target.val) {
            path.add(node);
            return path;
        }

        List<TreeNode> left = findPath(node.left, target);
        if(left.size() > 0) {   // 长度不为0，说明找到了target，长度为0说明没找到target
            left.add(0, node);
            return left;
        }

        List<TreeNode> right = findPath(node.right, target);
        if(right.size() > 0) {
            right.add(0, node);
            return right;
        }

        return path;
    }

    /**
     * 1.node的左子树包含p，而node的右子树包含q，则node就是最近公共祖先
     * 2.node的右子树包含p，而node的左子树包含q，则node就是最近公共祖先
     *
     * 3.node等于p，而且node的左或右子树包含q，则node就是最近公共祖先
     * 4.node等于q，而且node的左或右子树包含p，则node就是最近公共祖先
     * 5.node等于p又等于q，则node就是最近公共祖先
     *
     * 6.p和q都在node左子树中，则在左子树中寻找
     * 7.p和q都在node右子树中，则在右子树中寻找
     *
     * 综上所述，只要p和q不在node的同一边，则node就是最近公共祖先
     * */
    private TreeNode rst;
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        bfs(root, p, q);
        return rst;
    }

    // 以node为根的二叉树中是否包含p或q（同时包含，或者只包含一个）
    private boolean bfs(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null) return false;

        boolean left = bfs(node.left, p, q);
        boolean right = bfs(node.right, p, q);

        if(left && right) { // 1 和 2
            rst = node;
        }

        if(node.val == p.val && (left || right)) { // 3
            rst = node;
        }

        if(node.val == q.val && (left || right)) { // 4
            rst = node;
        }

        if(node.val == p.val && node.val == q.val) { // 5
            rst = node;
        }


        if(left || right) { // 以node为根的二叉树的右子树或者左子树中，包含p或q
            return true;
        }

        if(node.val == p.val || node.val == q.val) {    // node节点本身等于p或q
            return true;
        }

        // 以node为根的二叉树中不包含p或q
        return false;
    }

    public static void main(String[] args) {

    }
}
