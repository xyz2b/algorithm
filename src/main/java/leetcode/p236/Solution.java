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

    public static void main(String[] args) {

    }
}
