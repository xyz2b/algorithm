package leetcode.p102;

import leetcode.p144.TreeNode;

import java.util.*;

public class Solution {
    // 层序遍历，队列
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if(root == null) return rst;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int curLevelNodeNums = 1;
        int nextLevelNodeNums = 0;
        List<Integer> levelNode = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelNode.add(node.val);
            curLevelNodeNums--;

            if(node.left != null) {
                queue.offer(node.left);
                nextLevelNodeNums++;
            }

            if(node.right != null) {
                queue.offer(node.right);
                nextLevelNodeNums++;
            }

            if(curLevelNodeNums == 0) { // 本层遍历完了
                curLevelNodeNums = nextLevelNodeNums;
                nextLevelNodeNums = 0;
                rst.add(levelNode);
                levelNode = new ArrayList<>();
            }
        }

        return rst;
    }


    static class Pair<F, S> {
        public F first;
        public S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }
    // 层序遍历，队列
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if(root == null) return rst;

        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        // 节点和层数的数据对，root属于第0层
        queue.offer(new Pair<TreeNode, Integer>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            TreeNode node = p.first;
            int level = p.second;

            // 默认rst为空，size为0，root在第0层，创建存储这一层元素的list
            // 当遇到每一层第一个节点时，都会创建存储该层元素的list，而除去每一层第一个节点其他节点都不会创建，因为每一层首节点创建本层的list之后，rst.size会加1，本层的层数就不等于rst.size了
            if(level == rst.size()) {
                rst.add(new ArrayList<>());
            }

            rst.get(level).add(node.val);

            if(node.left != null) {
                queue.offer(new Pair<TreeNode, Integer>(node.left, level + 1));
            }

            if(node.right != null) {
                queue.offer(new Pair<TreeNode, Integer>(node.right, level + 1));
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        Solution solution = new Solution();
        List<List<Integer>> rst = solution.levelOrder(root);
        for(List<Integer> l : rst) {
            for(Integer i : l) {
                System.out.println(i);
            }
            System.out.println();
        }
    }
}
