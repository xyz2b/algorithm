package leetcode.p513;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    // 层序遍历，广度优先搜素
    // 先将元素的右孩子加入队列，再将元素的左孩子加入队列
    // 正常层序遍历是先加左边孩子，再加右孩子，这样最后遍历的那个元素是最后一层最右侧的元素
    // 转换一下思路，先加右孩子，再加左孩子，这样最后遍历的那个元素一定就是最后一层最左侧的元素
    public int findBottomLeftValue(TreeNode root) {
        int ret = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.remove();

            if(node.right != null) {
                queue.add(node.right);
            }

            if(node.left != null) {
                queue.add(node.left);
            }

            ret = node.val;
        }
        return ret;
    }
}
