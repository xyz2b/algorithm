package leetcode.p508;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    // key为元素和，value为这个元素和出现了几次
    Map<Integer, Integer> sum = new HashMap<Integer, Integer>();
    // 元素和 出现的最大次数
    int maxCount = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();

        treeSum(root);

        for(Map.Entry<Integer, Integer> entry: sum.entrySet()) {
            int k = entry.getKey(), v = entry.getValue();
            if (v == maxCount) {
                rst.add(k);
            }
        }

        int[] rstA = new int[rst.size()];
        for (int i=0; i < rst.size(); i++) {
            rstA[i] = rst.get(i);
        }

        return rstA;
    }

    // 计算以node为根的树的所有元素和
    public int treeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftTreeSum = treeSum(node.left);
        int rightTreeSum = treeSum(node.right);
        int treeSum = rightTreeSum + leftTreeSum + node.val;

        if (sum.containsKey(treeSum)) {
            sum.put(treeSum, sum.get(treeSum) + 1);
        } else {
            sum.put(treeSum, 1);
        }

        // 在递归计算每个树的元素和时，同时计算出 元素和 出现的最大次数
        maxCount = Math.max(maxCount, sum.get(treeSum));

        return treeSum;
    }

    public String toString(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(data[i]).append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(-3);

        TreeNode root = new TreeNode(5, left, right);

        Solution2 solution = new Solution2();
        int[] rst = solution.findFrequentTreeSum(root);
        System.out.println(solution.toString(rst));
    }
}
