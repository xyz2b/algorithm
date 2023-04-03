package leetcode.p129;

import com.sun.jmx.snmp.SnmpNull;
import leetcode.p144.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    private int sum = 0;
    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);
        return sum;
    }

    // 自顶向下的遍历
    // 传入的path是遍历当前node节点之前走过的路径
    public void sumNumbers(TreeNode node, int path) {
        if(node == null) return;

        if(node.left == null && node.right == null) { // 叶子节点
            sum += path * 10 + node.val;
            return;
        }

        int pathNew = path * 10 + node.val;
        sumNumbers(node.left, pathNew);
        sumNumbers(node.right, pathNew);
    }

    public int sumNumbers2(TreeNode root) {
        List<List<Integer>> paths = sum(root);
        int sum = 0;
        for(List<Integer> path : paths) {
            int pathSum = 0;
            for(int i = 0; i < path.size(); i++) {
                pathSum = pathSum * 10 + path.get(i);
            }
            sum += pathSum;
        }
        return sum;
    }

    // 返回以root为根的二叉树，从根到叶子节点的所有路径
    public List<List<Integer>> sum(TreeNode node) {
        List<List<Integer>> rst = new ArrayList<>();

        if(node == null) return rst;

        if(node.left == null && node.right == null) {
            List<Integer> path = new LinkedList<>();
            path.add(node.val);
            rst.add(path);
            return rst;
        }

        List<List<Integer>> pathsL = sum(node.left);
        for(List<Integer> path : pathsL) {
            path.add(0, node.val);
            rst.add(path);
        }

        List<List<Integer>> pathsR = sum(node.right);
        for(List<Integer> path : pathsR) {
            path.add(0, node.val);
            rst.add(path);
        }

        return rst;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        Solution solution = new Solution();
        System.out.println(solution.sumNumbers2(root));
    }
}
