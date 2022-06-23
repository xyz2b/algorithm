package leetcode.p515;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    // 层序遍历
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();

        if (root == null) {
            return rst;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        // 当前层未遍历元素个数
        int curLevelNodeNum = 0;
        // 下一层未遍历元素个数
        int nextLevelNodeNum = 0;

        // 将根节点推入队列
        queue.add(root);
        // 当前层未遍历元素加一
        curLevelNodeNum++;

        int levelMaxNum = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node.val > levelMaxNum) {       // 当前元素大于当前层的最大元素，记录下来
                levelMaxNum = node.val;
            }

            curLevelNodeNum--;      // 当前层未遍历的元素减一

            // 将左右孩子，如果有的话，推入队列，同时下一层未遍历元素加一
            if (node.left != null) {
                queue.add(node.left);
                nextLevelNodeNum++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevelNodeNum++;
            }

            // 如果当前层未遍历元素为0了，说明当前层遍历完了，下面遍历的就是下一层，需要做一些初始化工作
            if (curLevelNodeNum == 0) {
                curLevelNodeNum = nextLevelNodeNum;            // 将下一层未遍历元素个数赋值给当前层未遍历元素个数
                nextLevelNodeNum = 0;                       // 将下一层未遍历元素个数置0

                rst.add(levelMaxNum);                   // 当前层遍历完了，记录下当前层的最大值
                levelMaxNum = Integer.MIN_VALUE;        // 将最大元素的变量初始化为INT最小值，以便收集下一层元素的最大值
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

    }

}
