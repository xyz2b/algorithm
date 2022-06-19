package leetcode.p508;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int[] findFrequentTreeSum(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();
        // key为元素和，value为这个元素和出现了几次
        Map<Integer, Integer> sum = new HashMap<Integer, Integer>();

        treeSum(root, sum);

        // 元素和 出现的最大次数
        int maxCount = 0;
        // 得到每个元素和出现的次数之后，再去判断元素和出现的最大次数
        for(Map.Entry<Integer, Integer> entry: sum.entrySet()) {
            int k = entry.getKey(), v = entry.getValue();

            if (v > maxCount) {
                maxCount = v;
                rst.clear();
                rst.add(k);
            } else if (v == maxCount) {
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
    public int treeSum(TreeNode node, Map<Integer, Integer> sum) {
        if (node == null) {
            return 0;
        }

        int leftTreeSum = treeSum(node.left, sum);
        int rightTreeSum = treeSum(node.right, sum);
        int treeSum = rightTreeSum + leftTreeSum + node.val;

        if (sum.containsKey(treeSum)) {
            sum.put(treeSum, sum.get(treeSum) + 1);
        } else {
            sum.put(treeSum, 1);
        }

        return treeSum;
    }
}
