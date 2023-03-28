package leetcode.p103;

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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
                // 对上一层如果是奇数层进行逆序
                if(level > 0 && (level - 1) % 2 == 1) {
                    Collections.reverse(rst.get(level - 1));
                }
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
        // 最后一层的索引是奇数，即有偶数层，最后一层需要反转
        if(rst.size() % 2 == 0) {
            Collections.reverse(rst.get(rst.size() - 1));
        }
        return rst;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        Solution solution = new Solution();
        List<List<Integer>> rst = solution.zigzagLevelOrder(root);
        for(List<Integer> l : rst) {
            for(Integer i : l) {
                System.out.println(i);
            }
            System.out.println();
        }
    }
}
