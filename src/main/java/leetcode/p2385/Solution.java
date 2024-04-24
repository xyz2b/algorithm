package leetcode.p2385;

import com.sun.jmx.snmp.SnmpNull;
import leetcode.p206.LinkedList;

import java.io.LineNumberReader;
import java.lang.reflect.Array;
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

// 深度优先搜索建图 + 广度优先搜索计算感染时间
class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        dfs(root, graph);

        // 广度优先遍历计算时间
        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        queue.add(new int[]{start, 0});
        int time = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int node = arr[0];
            time = arr[1];
            for(int next : graph.get(node)) {
                if(!visited.contains(next)) {
                    visited.add(next);
                    queue.add(new int[]{next, time + 1});
                }
            }
        }
        return time;
    }

    // 深度优先搜索建图，因为树中的节点值是不重复的，所以可以用直接节点值作为索引
    public void dfs(TreeNode node, Map<Integer, List<Integer>> graph) {
        graph.putIfAbsent(node.val, new ArrayList<>());
        if(node.left != null) {
            graph.get(node.val).add(node.left.val);
            graph.putIfAbsent(node.left.val, new ArrayList<>());
            graph.get(node.left.val).add(node.val);
            dfs(node.left, graph);
        }

        if(node.right != null) {
            graph.get(node.val).add(node.right.val);
            graph.putIfAbsent(node.right.val, new ArrayList<>());
            graph.get(node.right.val).add(node.val);
            dfs(node.right, graph);
        }
    }
}
