package leetcode.p113;

import leetcode.p144.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    private List<List<Integer>> rst = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathSum(root, targetSum, new ArrayList<>());
        return rst;
    }

    // path为遍历到当前节点，所走过的路径，targetSum为以node为根的二叉树，从根到叶子节点的和
    public void pathSum(TreeNode node, int targetSum, List<Integer> path) {
        if(node == null) return;

        if(node.left == null && node.right == null) { // 叶子节点
            if(node.val == targetSum) {
                path.add(node.val);
                rst.add(path);
                return;
            }
        }

        List<Integer> pathL = new ArrayList<>(path);
        pathL.add(node.val);
        pathSum(node.left, targetSum - node.val, pathL);

        List<Integer> pathR = new ArrayList<>(path);
        pathR.add(node.val);
        pathSum(node.right, targetSum - node.val, pathR);
    }

    // 返回以root为根的二叉树中，从根到叶子节点的和为targetSum的所有路径，targetSum是逐层递减的
    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;

        if (root.left == null && root.right == null) { // 叶子节点
            if(root.val == targetSum) {
                List<Integer> path = new LinkedList<>();
                path.add(root.val);
                rst.add(path);
                return rst;
            }
        }

        List<List<Integer>> pathsL = pathSum2(root.left, targetSum - root.val);
        for(List<Integer> path : pathsL) {
            path.add(0, root.val);
            rst.add(path);
        }

        List<List<Integer>> pathsR = pathSum2(root.right, targetSum - root.val);
        for(List<Integer> path : pathsR) {
            path.add(0, root.val);
            rst.add(path);
        }
        return rst;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null), new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(5), new TreeNode(1))));
        Solution solution = new Solution();
        for (List<Integer> l : solution.pathSum2(root, 22)) {
            StringBuilder sb = new StringBuilder();
            for(int i : l) {
                sb.append(i).append("->");
            }
            System.out.println(sb.toString());
        }
    }
}

