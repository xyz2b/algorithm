package leetcode.p107;

import leetcode.p144.TreeNode;

import java.util.*;

public class Solution {
    static class Pair<F, S> {
        public F first;
        public S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    // 层序遍历，队列
    // 逆序输出
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
        // 逆序
        Collections.reverse(rst);
        return rst;
    }
}
