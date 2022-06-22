package leetcode.p513;

public class Solution3 {
    int curHeight = 0;
    int rst = 0;

    // 深度优先搜索
    // 先遍历左子树，再遍历右子树，所以对于同一层的元素，最先访问的就是最左侧的元素
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);

        return rst;
    }

    public void dfs(TreeNode node, int height) {
        if (node == null) {
            return;
        }

        height++;       // 遍历的是第几层
        dfs(node.left, height);
        dfs(node.right, height);
        // node.left和node.right都为null时，才会走到这里，即遍历到叶子节点才会走到这里
        if (height > curHeight) {   // 遍历到了下一层
            curHeight = height; // 修改当前层数
            rst = node.val; // 将当前层第一个遍历的元素记录下来（因为是深度优先遍历，所以对于同一层的元素来说，最先访问的就是最左侧的元素），它就是当前层最左侧的元素
        }
    }
}
