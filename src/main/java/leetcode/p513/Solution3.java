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
        dfs(node.left, height); // 对于每一颗子树，都优先遍历其左子树，直到叶子节点
        dfs(node.right, height);
        // node.left和node.right都为null时，才会走到这里，即遍历到叶子节点才会走到这里
        // 只有之后遍历的叶子节点比之前遍历的叶子节点层次更深，才会走入这个判断逻辑
        // 之后遍历同一层其他叶子节点也不会走到这个逻辑，因为层数相同
        // 所以每一层最先进入该逻辑的就是这一层的最左侧的叶子节点，因为是深度优先遍历，先遍历完左子树
        if (height > curHeight) {
            curHeight = height; // 修改当前已知的最大层数
            rst = node.val; // 将当前层第一个遍历的元素记录下来（因为是深度优先遍历，所以对于同一层的元素来说，最先访问的就是最左侧的元素），它就是当前层最左侧的元素
        }
    }
}
