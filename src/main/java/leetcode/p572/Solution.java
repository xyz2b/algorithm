package leetcode.p572;

import com.sun.jmx.snmp.SnmpNull;

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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if(isSameTree(node, subRoot)) {
                return true;
            }

            if(node.left != null) {
                queue.offer(node.left);
            }

            if(node.right != null) {
                queue.offer(node.right);
            }
        }

        return false;
    }

    private boolean isSameTree(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        } else if(node1 == null && node2 != null) {
            return false;
        } else if(node1 != null && node2 == null) {
            return false;
        }

        if(node1.val != node2.val) {
            return false;
        }

        boolean left = isSameTree(node1.left, node2.left);
        boolean right = isSameTree(node1.right, node2.right);

        return left & right;
    }


    // 深度优先遍历，然后转成子串匹配问题，判断subRoot是不是root的子串即可(kmp)
    // 但是这里存在一个问题：假设 s 由两个点组成，1 是根，2 是 1 的左孩子；t 也由两个点组成，1 是根，2 是 1 的右孩子。
    // 这样一来 s 和 t 的深度优先搜索序列相同，可是 t 并不是 s 的某一棵子树。
    // 为了解决这个问题，我们可以引入两个空值 lNull 和 rNull，当一个节点的左孩子或者右孩子为空的时候，就插入这两个空值，这样深度优先搜索序列就唯一对应一棵树。
    // 处理完之后，就可以通过判断「s 的深度优先搜索序列包含 t 的深度优先搜索序列」来判断答案。
    private int lNum, rNum;
    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        lNum = Integer.MIN_VALUE;
        rNum = Integer.MAX_VALUE;

        List<Integer> rQ = new ArrayList<>();
        List<Integer> sQ = new ArrayList<>();

        dfs(root, rQ);
        dfs(subRoot, sQ);


    }

    private boolean kmp(List<Integer> rQ, List<Integer> sQ) {

    }

    private List<Integer> getLps(List<Integer> sQ) {
        List<Integer> lps = new ArrayList<>();
        // lps[0] = 0
        lps.add(0);
        for(int i = 1; i < sQ.size(); i++) {
            int a = lps.get(i-1);
            if(sQ.get(i) != sQ.get(a)) {
                lps.add(a+1);
            } else {
                while (a > 0 && sQ.get(i) != sQ.get(a)) {
                    a = lps.get(a - 1);
                }
                if(sQ.get(i) == sQ.get(a)) lps.add(a + 1);
            }
        }

        return lps;
    }

    private void dfs(TreeNode node, List<Integer> q) {
        if(node == null) {
            return;
        }

        q.add(node.val);
        if(node.left != null) {
            dfs(node.left, q);
        } else {
            q.add(lNum);
        }

        if(node.right != null) {
            dfs(node.right, q);
        } else {
            q.add(rNum);
        }
    }
}
