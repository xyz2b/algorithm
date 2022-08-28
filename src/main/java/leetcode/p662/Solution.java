package leetcode.p662;

import javafx.util.Pair;

import java.util.*;

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
    // 存放每一层第一个元素的编号值，即每一层编号的最小值
    // 使用不存在再新增的方法
    Map<Integer, Integer> levelMin = new HashMap<Integer, Integer>();

    // 深度优先遍历
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return dfs(root, 1,1);
    }

    // 当前遍历的node所处的层数为depth，当前遍历的node在当前层的编号为index
    // 返回当前层最大编号的元素和最小编号元素的间隔，即最大间隔，最大宽度
    private int dfs(TreeNode node, int depth, int index) {
        if (node == null) {
            return 0;
        }

        levelMin.putIfAbsent(depth, index); // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值

        int leftMax = dfs(node.left, depth + 1, leftNodeIndex(index));
        int rightMax = dfs(node.right, depth + 1, rightNodeIndex(index));

        return Math.max(Math.max(leftMax, rightMax), index - levelMin.get(depth) + 1);
    }

    private int leftNodeIndex(int nodeIndex) {
        return 2 * nodeIndex - 1;
    }

    private int rightNodeIndex(int nodeIndex) {
        return 2 * nodeIndex;
    }


    // 广度优先遍历
    public int widthOfBinaryTree2(TreeNode root) {
        int res = 1;
        // 当前层的node和索引的列表
        List<Pair<TreeNode, Integer>> arr = new ArrayList<Pair<TreeNode, Integer>>();
        arr.add(new Pair<TreeNode, Integer>(root, 1));
        while (!arr.isEmpty()) {
            // 下一层node和索引的列表
            List<Pair<TreeNode, Integer>> tmp = new ArrayList<Pair<TreeNode, Integer>>();
            for (Pair<TreeNode, Integer> pair : arr) {
                TreeNode node = pair.getKey();
                int index = pair.getValue();
                if (node.left != null) {
                    tmp.add(new Pair<TreeNode, Integer>(node.left, leftNodeIndex(index)));
                }
                if (node.right != null) {
                    tmp.add(new Pair<TreeNode, Integer>(node.right, rightNodeIndex(index)));
                }
            }
            // 索引最大的node是最后才加入列表的(arr.size() - 1)，索引最小的node是最早加入列表的(0)
            res = Math.max(res, arr.get(arr.size() - 1).getValue() - arr.get(0).getValue() + 1);
            // 新的一层，将下一层的列表 变成 当前层的列表
            arr = tmp;
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
