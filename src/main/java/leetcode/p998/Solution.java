package leetcode.p998;

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
    // 因为是在数组的末尾添加一个值，分为两种情况
    // 1.如果这个值是当前树的最大值，那么当前树就是其左子树，因为其他值都在它的左边
    // 2.如果这个值不是当前树的最大值，那么它一定存在于当前树的右子树当中，因为它在最大值的右边
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        return insert(root, val);
    }

    // 返回以node为根的树中插入val之后的树的根
    private TreeNode insert(TreeNode node, int val) {
        if(node == null) {
            return new TreeNode(val);
        }

        if(val > node.val) {    // 如果插入元素大于当前树根，则表明插入元素在当前树中是最大的，则将当前树作为新插入元素的左子树，因为插入元素是在数组的最右边，当前树的元素都在它左边
            return new TreeNode(val, node, null);
        } else { // val < node.val，如果插入元素小于当前树根，当前树树根是最大的，则表明插入元素存在于当前树的右子树中，因为新插入元素是在数组的最右边，即在当前树最大元素的右边
            node.right = insert(node.right, val);
        }

        return node;
    }
}
