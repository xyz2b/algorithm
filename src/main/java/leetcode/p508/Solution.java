package leetcode.p508;


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
    public int[] findFrequentTreeSum(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();
        // key为元素和，value为这个元素和出现了几次
        Map<Integer, Integer> sum = new HashMap<Integer, Integer>();

        treeSum(root, sum);

        int maxCount = 0;
        for(Map.Entry<Integer, Integer> entry: sum.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                rst.clear();
                rst.add(entry.getKey());
            } else if (entry.getValue() == maxCount) {
                rst.add(entry.getKey());
            }
        }

        int[] rstA = new int[rst.size()];
        for (int i=0; i < rst.size(); i++)         {
            rstA[i] = rst.get(i);
        }

        return rstA;
    }

    // 计算以node为根的树的所有元素和
    public int treeSum(TreeNode node, Map<Integer, Integer> sum) {
        if (node == null) {
            return 0;
        }

        int rightTreeSum = treeSum(node.right, sum);
        int leftTreeSum = treeSum(node.left, sum);
        int treeSum = rightTreeSum + leftTreeSum + node.val;

        if (sum.containsKey(treeSum)) {
            sum.put(treeSum, sum.get(treeSum) + 1);
        } else {
            sum.put(treeSum, 1);
        }

        return treeSum;
    }
}
